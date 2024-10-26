package org.example;

import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public interface TextWriter {
    void write(String fileName, String text) throws IOException;
}
