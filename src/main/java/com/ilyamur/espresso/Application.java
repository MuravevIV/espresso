package com.ilyamur.espresso;

import com.ilyamur.espresso.util.ApplicationConfigLoader;
import com.typesafe.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        Config config = ApplicationConfigLoader.load(args);
        Application application = new Application(new FileProcessor());
        application.run(config);
    }

    //

    private final FileProcessor fileProcessor;

    public Application(FileProcessor fileProcessor) {
        this.fileProcessor = fileProcessor;
    }

    private void run(Config config) {
        String fileContent = readFile(config);
        String processingResult = fileProcessor.process(fileContent);
        System.out.println(processingResult);
    }

    private String readFile(Config config) {
        return null;
    }
}
