package com.ilyamur.espresso;

import com.ilyamur.espresso.util.ApplicationConfig;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class ApplicationTest {

    private Application application;
    private ApplicationConfig applicationConfig;

    @Before
    public void before() {
        applicationConfig = mock(ApplicationConfig.class);
        application = new Application(applicationConfig);
    }

    @Test
    public void testRun() {
        application.run();
    }
}
