package com.wik.stuff.W.K_Stuff_assist;

import com.wik.stuff.W.K_Stuff_assist.buttons.Buttons;
import com.wik.stuff.W.K_Stuff_assist.config.TelegramBotConfiguration;
import com.wik.stuff.W.K_Stuff_assist.models.Employee;
import com.wik.stuff.W.K_Stuff_assist.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@Slf4j
public class TgBot extends TelegramLongPollingBot {
    Logger logger = LoggerFactory.getLogger(TgBot.class);
    @Value("${bot.token}")
    private String token;
    @Value("${bot.username}")
    private String botName;
    @Autowired
    UserRepository userRepository;
    @Autowired
    Buttons buttons;

    @Autowired
    TelegramBotConfiguration configuration;

    @Override
    public String getBotUsername() {
        return this.botName;
    }

    @Override
    public String getBotToken() {
        return this.token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (messageText) {
                case "/start":
                    startCommand(chatId, update.getMessage().getChat().getFirstName());
                    break;
                default:
                    sendStartMessage(chatId, "Извини,но такой команды нет.");
            }

            // Добавьте логику для обработки выбора роли и сохранения данных пользователя
        }
    }

    private void startCommand(long chatId, String name) {
        String answer = " Привет, " + name + ". Я твой персональный помощник в этом пабе. Используй кнопку /help " +
                "в панели меню, и я расскажу тебе,что умею делать.";

        logger.info("Replied to user " + name);

        sendStartMessage(chatId, answer);
        boolean managerExists = userRepository.existsByRole(Role.MANAGER);
        sendRolesSelectionMessage(chatId, managerExists);
    }

    private void sendRolesSelectionMessage(long chatId, boolean managerExists) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Пожалуйста, выбери свою должность:");
        sendMessage.setReplyMarkup(buttons.getRoleButtons(managerExists));
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            logger.error("Error sending role selection message", e);
        }
    }

    private void sendStartMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);
        // Добавьте кнопки для выбора роли
        try {
            execute(message);
        } catch (TelegramApiException e) {
            logger.error("Error occurred:  " + e.getMessage());
        }

    }

    private void handleCallbackQuery(Update update) {
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        String callbackData = update.getCallbackQuery().getData();

        Role selectionRole = Role.valueOf(callbackData);

        Employee employee = userRepository.findByChatId(chatId).orElseGet(() -> {
            Employee newEmployee = new Employee();
            newEmployee.setFirstName(update.getCallbackQuery().getMessage().getChat().getFirstName());
            return newEmployee;
        });
        employee.setRole(selectionRole);
        userRepository.save(employee);

        sendStartMessage(chatId, "Ваша должность успешно выбрана: " + selectionRole.name());
    }

    private void assignRoleToUser(long chatId, Role role,Update update) {
        Employee employee=userRepository.findByChatId(chatId).orElseGet(()->{
            Employee employee1 = new Employee();
            employee1.setFirstName(update.getCallbackQuery().getFrom().getFirstName());
            employee1.setChatId(chatId);
            return employee1;
        });
        employee.setRole(role);
        userRepository.save(employee);
    }
}