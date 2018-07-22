package com.ilyamur.espresso;

import com.google.common.base.Preconditions;
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
                bind(AppFileReader.class).in(Singleton.class);
                bind(AppFileProcessor.class).in(Singleton.class);
                bind(AppOutput.class).in(Singleton.class);
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
    private final AppFileProcessor appFileProcessor;
    private final AppFileReader appFileReader;
    private AppOutput appOutput;

    @Inject
    public Application(ApplicationConfig applicationConfig, AppFileProcessor appFileProcessor,
                       AppFileReader appFileReader, AppOutput appOutput) {
        this.applicationConfig = applicationConfig;
        this.appFileProcessor = appFileProcessor;
        this.appFileReader = appFileReader;
        this.appOutput = appOutput;
    }

    public void run() {
        String fileName = applicationConfig.getArgsMap().get("file");
        Preconditions.checkNotNull(fileName, "Argument is not defined: -file");
        String fileContent = appFileReader.readFile(fileName);
        String processingResult = appFileProcessor.process(fileContent);
        appOutput.output(processingResult);
    }
}
