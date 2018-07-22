package com.ilyamur.espresso.util;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ApplicationConfig {

    private static Config mainConfig = ConfigFactory.empty();

    static void load(Config mainConfig) {
        ApplicationConfig.mainConfig = mainConfig;
    }

    private static String ROOT_PREFIX = "com.ilyamur.espresso.";

    public static List<String> getArgsList() {
        return mainConfig.getList("args-list").unwrapped().stream()
                .map(Object::toString)
                .collect(Collectors.toList());
    }

    public static class argsMap {

        private static String ARGS_MAP = "args-map.";

        public static final String file = mainConfig.getString(ARGS_MAP + "file");
    }
}
