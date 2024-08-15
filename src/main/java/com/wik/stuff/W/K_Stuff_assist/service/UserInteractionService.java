package com.wik.stuff.W.K_Stuff_assist.service;

import com.wik.stuff.W.K_Stuff_assist.models.Position;

public interface UserInteractionService {
    void handleMessage(long chatId, String messageText);

    void processJobTitleSelection(long chatId, Position position);

    void sendUnknownCommandMessage(long chatId);
}
