package com.wik.stuff.W.K_Stuff_assist.response;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface JobResponse {
    String getWelcomeMessage();

    String getInstructions();
}
