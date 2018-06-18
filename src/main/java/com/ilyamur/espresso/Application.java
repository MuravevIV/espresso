package com.ilyamur.espresso;

import com.google.common.base.Joiner;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {

        Config argsConfig = getArgsConfig(args);
        Config defaultConfig = ConfigFactory.defaultApplication();
        Config appConfig = ConfigFactory.parseFile(new File("conf/app.conf"));

        Config config = argsConfig.withFallback(appConfig.withFallback(defaultConfig));

        main(config);
    }

    private static void main(Config config) {
        log.info("ok, config: " + config);
        System.out.println("ok");
    }

    private static Config getArgsConfig(String[] args) {
        return ConfigFactory.parseString("args: [" + Joiner.on(", ").join(args) + "]");
    }
}
