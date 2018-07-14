package com.ilyamur.espresso.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.logging.BotLogger;

@Service
public class ApplicationBotApi {

    @Autowired
    private ApplicationBot bot;

/*    public String[] getCommandArgs(Message message) {
        return getCommandStream(message)
                .skip(1)
                .filter(s -> !s.isEmpty())
                .toArray(String[]::new);
    }*/

    public void respond(Message message, String text) {
        SendMessage echoMessage = new SendMessage();
        echoMessage.setChatId(message.getChatId().toString());
        echoMessage.setText(text);
        try {
            bot.sendMessage(echoMessage);
        } catch (TelegramApiException e) {
            BotLogger.error(Constants.BOT_NAME, e);
        }
    }
}
