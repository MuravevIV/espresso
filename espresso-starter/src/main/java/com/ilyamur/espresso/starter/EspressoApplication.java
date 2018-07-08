package com.ilyamur.espresso.starter;

import com.ilyamur.espresso.handlers.EspressoHandlersConfiguration;
import com.ilyamur.espresso.web.ApplicationBotStarter;
import com.ilyamur.espresso.web.EspressoCoreConfiguration;
import com.ilyamur.espresso.web.EspressoWebConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

@SpringBootApplication
@Import({
        EspressoCoreConfiguration.class,
        EspressoHandlersConfiguration.class,
        EspressoWebConfiguration.class
})
public class EspressoApplication {

    @Autowired
    private ApplicationBotStarter botStarter;

    @PostConstruct
    public void postConstruct() {
        botStarter.init();
    }

    public static void main(String[] args) {
        SpringApplication.run(EspressoApplication.class, args);
    }
}
