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

    public SendMessage createJobTitleButtons(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Пожалуйста, выберите вашу должность:");

        // Создаем кнопки и клавиатуру
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();

        // Первая строка кнопок
        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton("Официант"));
        row1.add(new KeyboardButton("Стажер - официант"));

        // Вторая строка кнопок
        KeyboardRow row2 = new KeyboardRow();
        row2.add(new KeyboardButton("Стажер - ранер."));
        row2.add(new KeyboardButton("Ранер."));

        KeyboardRow row3 = new KeyboardRow();
        if (!employeeService.existsManager()) {
            row3.add(new KeyboardButton("Менеджер"));
        }

        // Добавляем строки в клавиатуру
        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);
        // Настраиваем клавиатуру
        keyboardMarkup.setKeyboard(keyboard);
        keyboardMarkup.setResizeKeyboard(true); // Изменяет размер клавиатуры в зависимости от количества кнопок

        message.setReplyMarkup(keyboardMarkup);
        return message;
    }
}
