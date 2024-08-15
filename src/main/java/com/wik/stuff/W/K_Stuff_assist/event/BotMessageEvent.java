package com.wik.stuff.W.K_Stuff_assist.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

public class BotMessageEvent extends ApplicationEvent {
    private final long chatId;
    private final String messageText;
@Autowired
    public BotMessageEvent(Object source, long chatId, String messageText) {
        super(source);
        this.chatId = chatId;
        this.messageText = messageText;
    }

    public long getChatId() {
        return chatId;
    }

    public String getMessageText() {
        return messageText;
    }
}