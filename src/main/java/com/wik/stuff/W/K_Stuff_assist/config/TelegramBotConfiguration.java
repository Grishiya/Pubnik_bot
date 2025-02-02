package com.wik.stuff.W.K_Stuff_assist.config;

import com.wik.stuff.W.K_Stuff_assist.TgBot;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
@Data
public class TelegramBotConfiguration {
    @Value("${bot.token}")
    private String token;
    @Value("${bot.username}")
    private String botName;

}