package com.ilyamur.espresso;

import com.ilyamur.espresso.util.ApplicationConfig;
import com.ilyamur.espresso.util.ApplicationConfigLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        ApplicationConfigLoader.load(args);

        Application application = new Application(
                new AppFileProcessor(),
                new AppFileReader(),
                new AppOutput());

        application.run();
    }

    //

    private final AppFileProcessor appFileProcessor;
    private final AppFileReader appFileReader;
    private AppOutput appOutput;

    public Application(AppFileProcessor appFileProcessor, AppFileReader appFileReader, AppOutput appOutput) {
        this.appFileProcessor = appFileProcessor;
        this.appFileReader = appFileReader;
        this.appOutput = appOutput;
    }

    public void run() {
        String fileContent = appFileReader.readFile(ApplicationConfig.argsMap.file);
        String processingResult = appFileProcessor.process(fileContent);
        appOutput.output(processingResult);
    }
}
