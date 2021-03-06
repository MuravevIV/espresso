package com.ilyamur.espresso.util;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigFactory;

import javax.inject.Inject;
import java.io.File;
import java.util.List;
import java.util.Map;

public class ApplicationConfigLoader {

    private ApplicationConfig applicationConfig;

    @Inject
    public ApplicationConfigLoader(ApplicationConfig applicationConfig) {
        this.applicationConfig = applicationConfig;
    }

    public void load(String[] args) {
        Config argsConfig = getArgumentsConfig(args);
        Config envConfig = getEnvironmentConfig(argsConfig);
        Config defaultConfig = ConfigFactory.defaultReference();

        Config mainConfig = argsConfig.withFallback(envConfig.withFallback(defaultConfig));

        applicationConfig.load(mainConfig);
    }

    private Config getEnvironmentConfig(Config argsConfig) {
        Config appConfig = ConfigFactory.empty();
        try {
            String appConfigPath = argsConfig.getString("args-map.config");
            if (!appConfigPath.isEmpty()) {
                appConfig = ConfigFactory.parseFile(new File(appConfigPath));
            }
        } catch (ConfigException.Missing e) {
            // ignore
        }
        return appConfig;
    }

    private Config getArgumentsConfig(String[] args) {
        List<String> argsList = Lists.newArrayList();
        Map<String, String> argsMap = Maps.newHashMap();
        String key = null;
        for (String arg : args) {
            if (key != null) {
                argsMap.put(key, arg);
                key = null;
            } else if (arg.charAt(0) == '-') {
                if (arg.length() > 1) {
                    key = arg.substring(1);
                }
            } else {
                argsList.add(arg);
            }
        }

        Config argsListConfig = ConfigFactory.parseString("args-list: [" + Joiner.on(", ").join(argsList) + "]");
        Config argsMapConfig = ConfigFactory.parseMap(argsMap).atPath("args-map");

        return argsListConfig.withFallback(argsMapConfig);
    }
}
