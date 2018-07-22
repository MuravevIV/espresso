package com.ilyamur.espresso.util;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ApplicationConfig {

    private static final String ROOT_PREFIX = "com.ilyamur.espresso.";
    private static final String ARGS_LIST = "args-list";
    private static final String ARGS_MAP = "args-map";

    private Config mainConfig = ConfigFactory.empty();

    void load(Config mainConfig) {
        this.mainConfig = mainConfig;
    }

    public List<String> getArgsList() {
        return mainConfig.getList(ARGS_LIST).unwrapped().stream()
                .map(Object::toString)
                .collect(Collectors.toList());
    }

    public Map<String, String> getArgsMap() {
        return mainConfig.getObject(ARGS_MAP).unwrapped().entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, b -> b.getValue().toString()));
    }
}
