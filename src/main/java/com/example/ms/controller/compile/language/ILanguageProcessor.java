package com.example.ms.controller.compile.language;

import java.io.IOException;

public interface ILanguageProcessor {

     default Process process(String[] params) throws IOException {
        ProcessBuilder builder = new ProcessBuilder(params);
        Process process = builder.start();
        return  process;
    }

    Process buildCode() throws IOException;

    Process executeCode() throws IOException;

}
