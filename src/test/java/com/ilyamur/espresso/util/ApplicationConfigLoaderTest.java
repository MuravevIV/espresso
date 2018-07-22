package com.ilyamur.espresso.util;

import com.typesafe.config.Config;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

public class ApplicationConfigLoaderTest {

    private ApplicationConfig applicationConfig;
    private ApplicationConfigLoader applicationConfigLoader;

    @Before
    public void before() {
        applicationConfig = mock(ApplicationConfig.class);
        applicationConfigLoader = new ApplicationConfigLoader(applicationConfig);
    }

    @Test
    public void testLoad() {
        ArgumentCaptor<Config> argCaptor = ArgumentCaptor.forClass(Config.class);
        doNothing().when(applicationConfig).load(argCaptor.capture());

        applicationConfigLoader.load(new String[]{"-file", "conf/application.conf.dev", "x"});

        Config config = argCaptor.getValue();
        assertNotNull(config.getAnyRef("com.ilyamur.espresso"));
        assertNotNull(config.getAnyRef("java.version"));
        assertEquals("x", config.getList("args-list").get(0).unwrapped());
        assertEquals("conf/application.conf.dev", config.getString("args-map.file"));
    }
}
