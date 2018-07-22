package com.ilyamur.espresso;

import com.ilyamur.espresso.util.ApplicationConfigLoader;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ApplicationTest {

    private Application application;
    private AppFileReader appFileReader;
    private AppFileProcessor appFileProcessor;
    private AppOutput appOutput;

    @Before
    public void before() {
        appFileProcessor = mock(AppFileProcessor.class);
        appFileReader = mock(AppFileReader.class);
        appOutput = mock(AppOutput.class);
        application = new Application(appFileProcessor, appFileReader, appOutput);
    }

    @Test
    public void testRun() {
        String fileName = "D:\\projects\\espresso\\.local\\content.txt";
        String fileContent = "example content";
        String result = "processing result";
        ApplicationConfigLoader.load(new String[]{
                "-file", fileName
        });
        when(appFileReader.readFile(fileName)).thenReturn(fileContent);
        when(appFileProcessor.process(fileContent)).thenReturn(result);

        application.run();

        verify(appOutput).output(result);
    }
}
