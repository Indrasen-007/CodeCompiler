package com.example.ms.controller.compile.language;

import java.io.IOException;

import static com.example.ms.controller.compile.CompilationService.BASE_PATH;

public class JavaProcessor implements ILanguageProcessor {

    @Override
    public Process buildCode() throws IOException {
        // return Runtime.getRuntime().exec( "javac " + BASE_PATH + "Solution.java");
        String[] param = new String[]{"javac", BASE_PATH + "Solution.java"};
        return process(param);
    }

    @Override
    public Process executeCode() throws IOException {
        // return Runtime.getRuntime().exec( "java -cp " + BASE_PATH + " Solution");
        String[] param = new String[]{"java", "-cp", BASE_PATH, "Solution"};
        return process(param);
    }
}
