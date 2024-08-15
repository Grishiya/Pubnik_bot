package com.wik.stuff.W.K_Stuff_assist.buttons;

import com.wik.stuff.W.K_Stuff_assist.models.Position;
import com.wik.stuff.W.K_Stuff_assist.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
public class RegistrationButtons {
    @Autowired
    private EmployeeService employeeService;

    public SendMessage createJobTitleButtons(long chatId, boolean hasManager) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Пожалуйста, выберите вашу должность:");


        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboardsForJob = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(createButton("Официант", "WAITER"));
        row1.add(createButton("Стажер официант", "TRAINEE_WAITER"));

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(createButton("Стажер - помощник официанта", " TRAINEE_ASSISTANT_WAITER"));
        row2.add(createButton("Помощник официанта", "ASSISTANT_WAITER"));

        List<InlineKeyboardButton> row3 = new ArrayList<>();
        if (!hasManager) {
            row3.add(createButton("Управляющий", "MANAGER"));
        }
        keyboardsForJob.add(row1);
        keyboardsForJob.add(row2);
        keyboardsForJob.add(row3);
        keyboardMarkup.setKeyboard(keyboardsForJob);
        message.setReplyMarkup(keyboardMarkup);
        return message;
    }

    private InlineKeyboardButton createButton(String text, String callBackData) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text);
        button.setCallbackData(callBackData);
        return button;
    }
}
