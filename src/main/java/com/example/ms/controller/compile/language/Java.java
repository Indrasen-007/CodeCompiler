package com.example.ms.controller.compile.language;

import java.io.IOException;

import static com.example.ms.controller.compile.CompilationService.BASE_PATH;

public class Java implements LanguageProcessor{

    @Override
    public Process buildCode() throws IOException {
        ProcessBuilder builder = new ProcessBuilder("javac",BASE_PATH+"a.java");
        Process process = builder.start();
        return  process;
//        return Runtime.getRuntime().exec( "javac " + BASE_PATH + "a.java");
    }

    @Override
    public Process executeCode() throws IOException {
        ProcessBuilder builder = new ProcessBuilder("java","-cp",BASE_PATH,"a");
        Process process = builder.start();
        return  process;
//        return Runtime.getRuntime().exec( "java -cp " + BASE_PATH + " a");

    }
}
