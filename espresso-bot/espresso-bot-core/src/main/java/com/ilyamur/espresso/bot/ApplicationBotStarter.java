package com.ilyamur.espresso.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.logging.BotLogger;

@Service
public class ApplicationBotStarter {

    @Autowired
    private ApplicationBot applicationBot;

    static {
        ApiContextInitializer.init();
    }

    public void run() {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
            telegramBotsApi.registerBot(applicationBot);
        } catch (Exception e) {
            BotLogger.error(Constants.BOT_NAME, e);
        }
    }
}
