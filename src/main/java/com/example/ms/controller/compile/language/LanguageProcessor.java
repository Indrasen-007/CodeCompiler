package com.example.ms.controller.compile.language;

import java.io.IOException;

public interface LanguageProcessor {
    //String BASE_PATH = "./files/";

    Process buildCode() throws IOException;

    Process executeCode() throws IOException;

}
