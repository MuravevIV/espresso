package com.ilyamur.espresso.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Service;

@Service
public class ApplicationCoreStarter {

    @Autowired
    private ApplicationArguments appArgs;

    public void run() {
        ApplicationConfigLoader.load(appArgs.getSourceArgs());
    }
}
