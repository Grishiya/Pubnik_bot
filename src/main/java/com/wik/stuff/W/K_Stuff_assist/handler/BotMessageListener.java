package com.wik.stuff.W.K_Stuff_assist.handler;

import com.wik.stuff.W.K_Stuff_assist.event.BotMessageEvent;
import com.wik.stuff.W.K_Stuff_assist.service.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BotMessageListener {
    private final BotService botService;

    @Autowired
    public BotMessageListener(BotService botService) {
        this.botService = botService;
    }

    @EventListener
    public void handleBotMessageEvent(BotMessageEvent event) {
        long chatId = event.getChatId();
        String text = event.getMessageText();
        botService.handleMessage(chatId, text);
    }
}