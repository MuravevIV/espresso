package com.ilyamur.espresso.web.handler;

import org.telegram.telegrambots.api.objects.Message;

import java.util.function.Consumer;

public interface CommandHandler extends Consumer<Message> {

    default boolean triggersOn(String commandName) {
        return false;
    }
}
