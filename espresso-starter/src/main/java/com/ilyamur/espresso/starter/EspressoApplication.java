package com.ilyamur.espresso.starter;

import com.ilyamur.espresso.web.ApplicationBotStarter;
import com.ilyamur.espresso.web.EspressoBotConfiguration;
import com.ilyamur.espresso.web.EspressoWebConfiguration;
import com.ilyamur.espresso.web.handlers.EspressoBotHandlersConfiguration;
import com.ilyamur.espresso.core.ApplicationCoreStarter;
import com.ilyamur.espresso.core.EspressoCoreConfiguration;
import com.ilyamur.espresso.data.EspressoDataConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

@SpringBootApplication
@Import({
        EspressoCoreConfiguration.class,
        EspressoDataConfiguration.class,
        EspressoBotConfiguration.class,
        EspressoBotHandlersConfiguration.class,
        EspressoWebConfiguration.class
})
public class EspressoApplication {

    @Autowired
    private ApplicationCoreStarter coreStarter;

    @Autowired
    private ApplicationBotStarter botStarter;

    @PostConstruct
    public void postConstruct() {
        coreStarter.run();
        botStarter.run();
    }

    public static void main(String[] args) {
        SpringApplication.run(EspressoApplication.class, args);
    }
}
