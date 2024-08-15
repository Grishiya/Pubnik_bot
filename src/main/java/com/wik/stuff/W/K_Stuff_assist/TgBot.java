package com.wik.stuff.W.K_Stuff_assist;

import com.wik.stuff.W.K_Stuff_assist.handler.BotUpdateHandler;
import com.wik.stuff.W.K_Stuff_assist.config.TelegramBotConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@Slf4j
public class TgBot extends TelegramLongPollingBot {
    Logger logger = LoggerFactory.getLogger(TgBot.class);
    @Value("${bot.token}")
    private String token;
    @Value("${bot.username}")
    private String botName;
    @Autowired
    BotUpdateHandler botUpdateHandler;

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
        botUpdateHandler.handleUpdate(update);
    }

//    public void sendMessage(SendMessage sendMessage) throws TelegramApiException {
//        execute(sendMessage);
//    }
//
//    public void sendPhoto(SendPhoto photo) throws TelegramApiException {
//        execute(photo);
//    }
}