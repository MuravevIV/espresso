package com.ilyamur.espresso;

import com.ilyamur.espresso.util.ApplicationConfig;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.mockito.Mockito.*;

public class ApplicationTest {

    private Application application;
    private ApplicationConfig applicationConfig;
    private AppFileReader appFileReader;
    private AppFileProcessor appFileProcessor;
    private AppOutput appOutput;

    @Before
    public void before() {
        applicationConfig = mock(ApplicationConfig.class);
        appFileProcessor = mock(AppFileProcessor.class);
        appFileReader = mock(AppFileReader.class);
        appOutput = mock(AppOutput.class);
        application = new Application(applicationConfig, appFileProcessor, appFileReader, appOutput);
    }

    @Test
    public void testRun() {
        String fileName = "D:\\projects\\espresso\\.local\\content.txt";
        String fileContent = "example content";
        String result = "processing result";
        when(applicationConfig.getArgsMap()).thenReturn(Collections.singletonMap("file", fileName));
        when(appFileReader.readFile(fileName)).thenReturn(fileContent);
        when(appFileProcessor.process(fileContent)).thenReturn(result);

        application.run();

        verify(appOutput).output(result);
    }
}
