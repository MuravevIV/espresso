package com.ilyamur.espresso;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.ilyamur.espresso.util.ApplicationConfig;
import com.ilyamur.espresso.util.ApplicationConfigLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;

public class Application {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {

        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(ApplicationConfigLoader.class).in(Singleton.class);
                bind(ApplicationConfig.class).in(Singleton.class);
                bind(Application.class).in(Singleton.class);
            }
        });

        // bootstrapping configuration
        ApplicationConfigLoader applicationConfigLoader = injector.getInstance(ApplicationConfigLoader.class);
        applicationConfigLoader.load(args);

        Application application = injector.getInstance(Application.class);
        application.run();
    }

    //

    private final ApplicationConfig applicationConfig;

    @Inject
    public Application(ApplicationConfig applicationConfig) {
        this.applicationConfig = applicationConfig;
    }

    public void run() {
        LOG.info("Application config loaded: {}", applicationConfig);
    }
}
