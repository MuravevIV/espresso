package com.ilyamur.espresso.web.handler;

import org.telegram.telegrambots.api.objects.Message;

import java.util.function.Consumer;

/**
 * @author Ilya_Muravyev
 */
public interface TextHandler extends Consumer<Message> {
}
