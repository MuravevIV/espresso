package com.ilyamur.espresso.web;

import com.ilyamur.espresso.web.handler.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.logging.BotLogger;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@Service
public class ApplicationBot extends TelegramLongPollingBot {

    @Autowired(required = false)
    private List<CommandHandler> commandHandlers = Collections.emptyList();

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.hasText()) {
                if (message.isCommand()) {
                    handleCommandMessage(message);
                }
            } else {
                BotLogger.debug(Constants.BOT_NAME, "empty message");
            }
        } else {
            BotLogger.debug(Constants.BOT_NAME, "no message");
        }
    }

    private void handleCommandMessage(Message message) {
        String commandName = getCommandName(message);
        commandHandlers.stream()
                .filter(h -> h.triggersOn(commandName))
                .sorted()
                .forEach(h -> h.accept(message));
    }

    @Override
    public String getBotUsername() {
        return ApplicationBotConfig.COMMANDS_USER;
    }

    @Override
    public String getBotToken() {
        return ApplicationBotConfig.COMMANDS_TOKEN;
    }

    private String getCommandName(Message message) {
        return getCommandStream(message)
                .findFirst()
                .orElse("");
    }

    public String[] getCommandArgs(Message message) {
        return getCommandStream(message)
                .skip(1)
                .filter(s -> !s.isEmpty())
                .toArray(String[]::new);
    }

    private Stream<String> getCommandStream(Message message) {
        String[] commandSplit = message.getText().substring(1).split(" ");
        return Arrays.stream(commandSplit);
    }

    public void respond(Message message, String text) {
        SendMessage echoMessage = new SendMessage();
        echoMessage.setChatId(message.getChatId().toString());
        echoMessage.setText(text);
        try {
            sendMessage(echoMessage);
        } catch (TelegramApiException e) {
            BotLogger.error(Constants.BOT_NAME, e);
        }
    }
}
