package com.ilyamur.espresso.util;

import com.typesafe.config.Config;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ApplicationConfigLoaderTest {

    @Test
    public void testLoad() {
        Config config = ApplicationConfigLoader.load(new String[]{"-config", "conf/application.conf.dev", "x"});

        List<Object> argsList = ApplicationConfigLoader.getArgsList(config);
        assertEquals(1, argsList.size());
        assertEquals("x", argsList.get(0));

        Config argsMapConfig = ApplicationConfigLoader.getArgsMap(config);
        assertFalse(argsMapConfig.isEmpty());
        assertEquals("conf/application.conf.dev", argsMapConfig.getString("config"));

        assertNotNull(config.getString("user.dir"));
    }
}
