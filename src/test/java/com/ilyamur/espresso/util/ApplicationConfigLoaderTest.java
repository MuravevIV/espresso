package com.ilyamur.espresso.util;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ApplicationConfigLoaderTest {

    @Test
    public void testLoad() {
        ApplicationConfigLoader.load(new String[]{"-file", "conf/application.conf.dev", "x"});

        List<String> argsList = ApplicationConfig.getArgsList();
        assertEquals(1, argsList.size());
        assertEquals("x", argsList.get(0));

        String file = ApplicationConfig.argsMap.file;
        assertEquals("conf/application.conf.dev", file);
    }
}
