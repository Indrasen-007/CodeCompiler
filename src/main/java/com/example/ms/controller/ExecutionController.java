package com.example.ms.controller;

import com.example.ms.controller.compile.CompilationService;
import com.example.ms.model.CodeInput;
import com.example.ms.model.CompilationResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ExecutionController {
    @PostMapping(path= "/compile")
    public CompilationResult executeCode(@RequestBody CodeInput codeInput) throws Exception
    {
        System.out.println(codeInput);
        return CompilationService.compileAndRunCode(codeInput);
    }
}
