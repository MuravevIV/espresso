package com.ilyamur.espresso;

import com.google.common.base.Charsets;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class AppFileReader {

    public String readFile(String fileName) {
        try {
            return FileUtils.readFileToString(new File(fileName), Charsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
