package com.ilyamur.espresso.core;

import com.typesafe.config.Config;

public class ApplicationConfig {

    private static Config mainConfig;

    static void load(Config mainConfig) {
        ApplicationConfig.mainConfig = mainConfig;
    }

    private static String ROOT_PREFIX = "com.ilyamur.espresso.";

    public static class bot {

        private static String BOT_PREFIX = ROOT_PREFIX + "bot.";

        public static final String username = mainConfig.getString(BOT_PREFIX + "username");
        public static final String token = mainConfig.getString(BOT_PREFIX + "token");
    }
}
