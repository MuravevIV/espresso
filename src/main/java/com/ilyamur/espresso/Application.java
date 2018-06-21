package com.ilyamur.espresso;

import com.ilyamur.espresso.util.ApplicationConfig;
import com.typesafe.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        Config config = ApplicationConfig.load(args);
        log.info("ok, config: " + config);
        System.out.println("ok");
    }
}
