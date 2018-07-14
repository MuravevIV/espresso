package com.ilyamur.espresso.web.handlers.impl;

import com.ilyamur.espresso.data.entity.EchoMessage;
import com.ilyamur.espresso.data.repository.EchoMessageHistoryRepository;
import com.ilyamur.espresso.web.ApplicationBotApi;
import com.ilyamur.espresso.web.handler.TextHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.objects.Message;

@Component
public class EchoTextHandler implements TextHandler {

    @Autowired
    private ApplicationBotApi botApi;

    @Autowired
    private EchoMessageHistoryRepository repo;

    @Override
    public void accept(Message message) {
        String messageText = message.getText();
        EchoMessage echoMessage = getEchoMessage(message, messageText);
        repo.save(echoMessage);
        botApi.respond(message, messageText);
    }

    private EchoMessage getEchoMessage(Message message, String messageText) {
        String fullId = getFullId(message);
        return new EchoMessage(fullId, messageText);
    }

    private String getFullId(Message message) {
        Long chatId = message.getChatId();
        Integer messageId = message.getMessageId();
        return String.format("%s-%s", chatId, messageId);
    }
}
