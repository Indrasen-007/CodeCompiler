package com.example.ms.controller.compile.language;

import java.io.IOException;

import static com.example.ms.controller.compile.CompilationService.BASE_PATH;

public class CPlusPlusProcessor implements ILanguageProcessor {

    @Override
    public Process buildCode() throws IOException {
        // return Runtime.getRuntime().exec("g++ -o BASE_PATH/outputObj BASE_PATH/Solution.cpp");
        String[] param = new String[]{"g++", "-o", BASE_PATH + "CPlusPlusSolution.exe", BASE_PATH + "Solution.cpp"};
        return process(param);
    }

    @Override
    public Process executeCode() throws IOException {
        // return Runtime.getRuntime().exec("BASE_PATH+"outputObj");
        String[] param = new String[]{BASE_PATH + "CPlusPlusSolution.exe"};
        return process(param);
    }
}
