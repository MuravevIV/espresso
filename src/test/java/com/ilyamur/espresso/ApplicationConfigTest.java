package com.ilyamur.espresso;

import com.typesafe.config.Config;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ApplicationConfigTest {

    @Test
    public void testLoad() {
        Config config = ApplicationConfig.load(new String[]{"-config", "conf/application.conf.dev", "x"});

        List<Object> argsList = config.getList("args-list").unwrapped();
        assertEquals(1, argsList.size());
        assertEquals("x", argsList.get(0));

        Config argsMapConfig = config.getConfig("args-map");
        assertFalse(argsMapConfig.isEmpty());
        assertEquals("conf/application.conf.dev", argsMapConfig.getString("config"));

        assertNotNull(config.getString("user.dir"));
    }
}
