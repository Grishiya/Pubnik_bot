package com.wik.stuff.W.K_Stuff_assist.service;

import com.wik.stuff.W.K_Stuff_assist.Role;

public interface UserInteractionService {
    void handleMessage(long chatId, String messageText);

    void processJobTitleSelection(long chatId, Role role);

    void sendUnknownCommandMessage(long chatId);
}
