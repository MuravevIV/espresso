package com.ilyamur.espresso;

import com.typesafe.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        Config config = ApplicationConfig.prepare(args);
        log.info("ok, config: " + config);
        System.out.println("ok");
    }
}
