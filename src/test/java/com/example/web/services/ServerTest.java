package com.example.web.services;


import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ServerTest {


    private Path workingDir;
    String expected;

    @BeforeAll
    public void init() {
        this.workingDir = Path.of("", "src/test/resources");
    }
    @Test
    void serverTest() {
        String expected = "";
        Path file;
        try {
            file = this.workingDir.resolve("test");
             expected = Files.readString(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(expected,Server.connectAndReadXmlToString("http://www.lb.lt/webservices/FxRates/FxRates.asmx/getCurrentFxRates?tp=EU"));
    }

}