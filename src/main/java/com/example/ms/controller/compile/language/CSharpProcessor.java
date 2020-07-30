package com.example.ms.controller.compile.language;

import java.io.IOException;

import static com.example.ms.controller.compile.CompilationService.BASE_PATH;

public class CSharpProcessor implements ILanguageProcessor {

    @Override
    public Process buildCode() throws IOException {
        //  return Runtime.getRuntime().exec("csc -out:"+BASE_PATH+"CSharpSolution.exe "+ BASE_PATH + "CSharpSolution.cs");
        String[] param = new String[]{"csc", "-out:" + BASE_PATH + "CSharpSolution.exe", BASE_PATH + "CSharpSolution.cs"};
        return process(param);
    }

    @Override
    public Process executeCode() throws IOException {
        // return Runtime.getRuntime().exec(BASE_PATH + "CSharpSolution.exe");
        String[] param = new String[]{BASE_PATH + "CSharpSolution.exe"};
        return process(param);
    }
}
