package com.ilyamur.espresso.bot;

import com.ilyamur.espresso.bot.handler.CommandHandler;
import com.ilyamur.espresso.bot.handler.TextHandler;
import com.ilyamur.espresso.core.ApplicationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.logging.BotLogger;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@Service
public class ApplicationBot extends TelegramLongPollingBot {

    @Autowired(required = false)
    private List<CommandHandler> commandHandlers = Collections.emptyList();

    @Autowired(required = false)
    private List<TextHandler> textHandlers = Collections.emptyList();

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.hasText()) {
                if (message.isCommand()) {
                    handleCommandMessage(message);
                } else {
                    handleTextMessage(message);
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

    private void handleTextMessage(Message message) {
        textHandlers.stream()
                .sorted()
                .forEach(h -> h.accept(message));
    }

    private String getCommandName(Message message) {
        return getCommandStream(message)
                .findFirst()
                .orElse("");
    }

    private Stream<String> getCommandStream(Message message) {
        String[] commandSplit = message.getText().substring(1).split(" ");
        return Arrays.stream(commandSplit);
    }

    @Override
    public String getBotUsername() {
        return ApplicationConfig.bot.username;
    }

    @Override
    public String getBotToken() {
        return ApplicationConfig.bot.token;
    }
}
