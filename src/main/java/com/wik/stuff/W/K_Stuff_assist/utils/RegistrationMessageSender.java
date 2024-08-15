package com.wik.stuff.W.K_Stuff_assist.utils;

import com.wik.stuff.W.K_Stuff_assist.TgBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;

import java.io.File;
@Component
public class RegistrationMessageSender {
    private final TgBot tgBot;
@Autowired
    public RegistrationMessageSender(TgBot tgBot) {
        this.tgBot = tgBot;
    }

    public void sendWelcomeMessage(long chatId) {
        sendMessage(chatId, "Приветствую тебя!" +
                " Меня зовут Пабник ! Я буду твоим гидом по миру Английского паба 'Уильям и Кейт'." +
                " Давай познакомимся, как тебя зовут?");
//        sendPhoto(chatId, "path_to_image_file"); // Замените на путь к вашему изображению
    }

    public void askForLastName(long chatId) {
        sendMessage(chatId, "Отлично,а теперь пожалуйста, введи свою фамилию.");
    }

    public void askForMiddleName(long chatId) {
        sendMessage(chatId, "Пожалуйста, введите ваше отчество.");
    }

    public void askForPhoneNumber(long chatId) {
        sendMessage(chatId, "Спасибо! Теперь введите ваш номер телефона. Номер телефона должен начинаться с 7" +
                "и содержать 11 цифр." +
                " Пример: 79990002244");
    }
    public void askForEmployeeRole(long chatId) {
    sendMessage(chatId, " Отлично. Осталось выбрать свою должность");
    }

    public void sendConfirmationMessage(long chatId) {
        sendMessage(chatId, "Спасибо! Вы были успешно добавлены в базу данных сотрудников.");
    }

    public void sendUnknownCommandMessage(long chatId) {
        sendMessage(chatId, "Неизвестная команда.");
    }

    public void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);

        try {
            tgBot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public void sendJobSelectionMessage(long chatId, SendMessage sendMessage) {

        try {
            tgBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendPhoto(long chatId, String filePath) {
        SendPhoto photo = new SendPhoto();
        photo.setChatId(String.valueOf(chatId));
        photo.setPhoto(new InputFile(new File(filePath)));

        try {
            tgBot.execute(photo);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}