package com.example.ms.controller.compile.language;

import java.io.IOException;

import static com.example.ms.controller.compile.CompilationService.BASE_PATH;

public class CPlusPlus implements LanguageProcessor{

    @Override
    public Process buildCode() throws IOException {
        ProcessBuilder builder = new ProcessBuilder("g++","-o",BASE_PATH+"outputObj",BASE_PATH+"a.cpp");
        Process process = builder.start();
        return  process;
      //  return Runtime.getRuntime().exec( "g++ -o /Users/personal/workspace/CodeAaService/outputObj /Users/personal/workspace/CodeAaService/a.cpp");
    }

    @Override
    public Process executeCode() throws IOException {
        ProcessBuilder builder = new ProcessBuilder(BASE_PATH+"outputObj");
        Process process = builder.start();
        return  process;
    }
}
