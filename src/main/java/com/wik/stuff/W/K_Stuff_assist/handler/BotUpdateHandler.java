package com.wik.stuff.W.K_Stuff_assist.handler;

import com.wik.stuff.W.K_Stuff_assist.event.BotMessageEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class BotUpdateHandler {
    private final ApplicationEventPublisher eventPublisher;
@Autowired
    public BotUpdateHandler(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void handleUpdate(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();
            String messageText = update.getMessage().getText();

            BotMessageEvent event = new BotMessageEvent(this, chatId, messageText);
            eventPublisher.publishEvent(event);
            }
    }
}
