package com.ilyamur.espresso.handlers.impl;

import com.ilyamur.espresso.web.ApplicationBotApi;
import com.ilyamur.espresso.web.handler.TextHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.objects.Message;

@Component
public class EchoTextHandler implements TextHandler {

    @Autowired
    private ApplicationBotApi botApi;

    @Override
    public void accept(Message message) {
        botApi.respond(message, message.getText());
    }
}
