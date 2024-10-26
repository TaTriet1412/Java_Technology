package org.example;

import org.springframework.context.annotation.Bean;

public class AppConfig {

    @Bean
    public TextWriter plainTextWriter() {return new PlainTextWriter();}

    @Bean
    public TextWriter pdfTextWriter() {return new PdfTextWriter();}

    @Bean
    public TextEditor textEditor() {return new TextEditor();}

}
