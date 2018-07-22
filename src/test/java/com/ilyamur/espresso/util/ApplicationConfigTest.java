package com.ilyamur.espresso.util;

import com.typesafe.config.ConfigFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class ApplicationConfigTest {

    private ApplicationConfig applicationConfig;

    @Before
    public void before() {
        applicationConfig = new ApplicationConfig();
    }

    @Test
    public void testLoad() {
        applicationConfig.load(ConfigFactory.parseString("{args-list: [\"x\"], args-map: {file: \"conf/application.conf.dev\"}}"));

        assertEquals(Collections.singletonList("x"), applicationConfig.getArgsList());
        assertEquals(Collections.singletonMap("file", "conf/application.conf.dev"), applicationConfig.getArgsMap());
    }
}
