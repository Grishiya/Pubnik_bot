package com.wik.stuff.W.K_Stuff_assist.buttons;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class Buttons {
    public InlineKeyboardMarkup getRoleButtons(boolean managerExists) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        if (!managerExists) {
            rowsInline.add(createButtonsRow(("Manager"), "MANAGER"));
        }
        rowsInline.add(createButtonsRow("Waiter", "WAITER"));
        rowsInline.add(createButtonsRow("Runner", "RUNNER"));
        rowsInline.add(createButtonsRow("Trainee", "TRAINEE"));
        inlineKeyboardMarkup.setKeyboard(rowsInline);
        return inlineKeyboardMarkup;
    }

    private List<InlineKeyboardButton> createButtonsRow(String text, String callBackData) {
        List<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text);
        button.setCallbackData(callBackData);
        row.add(button);
        return row;
    }
}
