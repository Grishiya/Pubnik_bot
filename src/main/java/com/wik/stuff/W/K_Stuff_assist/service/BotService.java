package com.wik.stuff.W.K_Stuff_assist.service;

import com.wik.stuff.W.K_Stuff_assist.buttons.RegistrationButtons;
import com.wik.stuff.W.K_Stuff_assist.models.Employee;

import com.wik.stuff.W.K_Stuff_assist.models.Position;
import com.wik.stuff.W.K_Stuff_assist.models.UserState;
import com.wik.stuff.W.K_Stuff_assist.response.JobResponse;
import com.wik.stuff.W.K_Stuff_assist.response.TraineeAssistantResponse;
import com.wik.stuff.W.K_Stuff_assist.utils.RegistrationMessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.DelegatingServerHttpResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Service
public class BotService {
    private static final Logger logger = LoggerFactory.getLogger(BotService.class);
    private final EmployeeService employeeService;
    private final RegistrationMessageSender messageSender;
    private final RegistrationButtons registrationButtons;
    private final Map<Position, JobResponse> jobResponses = new HashMap<>();

    @Autowired
    public BotService(EmployeeService employeeService,
                      RegistrationMessageSender messageSender,
                      RegistrationButtons registrationButtons,
                      TraineeAssistantResponse traineeAssistantResponse) {
        this.employeeService = employeeService;
        this.messageSender = messageSender;
        this.registrationButtons = registrationButtons;
        this.jobResponses.put(Position.TRAINEE_ASSISTANT_WAITER, traineeAssistantResponse);
    }

    public void handleMessage(long chatId, String messageText) {
        try {
            Employee employee = employeeService.getEmployeeState(chatId);
            logger.info("Processing message: {} for chatId: {}", messageText, chatId);

            switch (employeeService.getUserState(chatId)) {
                case NEW:
                    processNewUser(chatId, messageText);
                    break;
                case FIRST_NAME:
                case LAST_NAME:
                case MIDDLE_NAME:
                case PHONE_NUMBER:
                    processEmployeeInput(chatId, messageText, employee);
                    break;
                case ROLE:
                    handleJobSelection(chatId, messageText);
                    break;
                default:
                    messageSender.sendUnknownCommandMessage(chatId);
                    break;
            }
        } catch (Exception e) {
            logger.error("Error processing message", e);
            messageSender.sendMessage(chatId, "Произошла ошибка. Пожалуйста, попробуйте снова.");
        }
    }
    private void processNewUser(long chatId, String messageText) {
        messageSender.sendWelcomeMessage(chatId);
        employeeService.updateUserState(chatId, UserState.FIRST_NAME);
    }
    private void processEmployeeInput(long chatId, String string, Employee employee) {
        switch (employeeService.getUserState(chatId)) {
            case NEW:
                messageSender.sendWelcomeMessage(chatId);
                employeeService.updateUserState(chatId, UserState.FIRST_NAME);
                break;
            case FIRST_NAME:
                employee.setFirstName(string);
                messageSender.askForLastName(chatId);
                employeeService.updateUserState(chatId, UserState.LAST_NAME);
                break;
            case LAST_NAME:
                employee.setLastName(string);
                messageSender.askForMiddleName(chatId);
                employeeService.updateUserState(chatId, UserState.MIDDLE_NAME);
                break;
            case MIDDLE_NAME:
                employee.setMiddleName(string);
                messageSender.askForPhoneNumber(chatId);
                employeeService.updateUserState(chatId, UserState.PHONE_NUMBER);
                break;
            case PHONE_NUMBER:
                if (isValidPhoneNumber(string)) {
                    employee.setNumberPhone(string);
                    messageSender.sendMessage(chatId, "Отлично ");
                    messageSender.sendJobSelectionMessage(chatId, registrationButtons.createJobTitleButtons(chatId));
                    employeeService.updateUserState(chatId, UserState.ROLE);
                } else {
                    messageSender.sendMessage(chatId, "Неверный формат номера телефона." +
                            " Попробуй еще раз,у тебя все получится");
                }
                break;
            default:
                messageSender.sendUnknownCommandMessage(chatId);
        }
    }

    private void handleJobSelection(long chatId, String positionString) {
        Position position = Position.valueOf(positionString.toUpperCase());
        Employee employee = employeeService.getEmployeeState(chatId);
        employee.setPosition(position);
        employeeService.saveEmployee(employee);
        messageSender.sendConfirmationMessage(chatId);
        employeeService.updateUserState(chatId, UserState.COMPLETED);

        JobResponse jobResponse = jobResponses.get(position);
        if (jobResponse != null) {
            messageSender.sendMessage(chatId, jobResponse.getWelcomeMessage());
            messageSender.sendMessage(chatId, jobResponse.getInstructions());

        }
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^7\\d{10}$");
    }
}