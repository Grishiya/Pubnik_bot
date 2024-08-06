package com.wik.stuff.W.K_Stuff_assist.buttons;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
public class RegistrationButtons {
    public List<KeyboardRow> getJobTitleButton() {
        List<KeyboardRow> keyboards = new ArrayList<>();
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();

        KeyboardRow row = new KeyboardRow();
        row.add(new KeyboardButton("Официант"));
        row.add(new KeyboardButton("Ранет"));

        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton("Стажер-помощник"));
        row1.add(new KeyboardButton("Стажер-официант"));

        KeyboardRow row2 = new KeyboardRow();
        row2.add(new KeyboardButton("Управляющий"));

        keyboardMarkup.setKeyboard(keyboards);
        keyboardMarkup.setResizeKeyboard(true);
        return keyboards;
    }
}