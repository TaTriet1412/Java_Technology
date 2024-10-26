package org.example;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public interface TextWriter {
    void write(String fileName, String text) throws IOException;
}
