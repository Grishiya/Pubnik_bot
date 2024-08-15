package com.wik.stuff.W.K_Stuff_assist.response;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class TraineeAssistantResponse implements JobResponse {
    public String getWelcomeMessage() {
        return "Добро пожаловать!";
    }

    public String getInstructions() {
        return "Получи свои инструкции!";
    }
}
