package com.example.ms.model;

import lombok.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CodeInput {
    //Question Id
    private int QuestionId;
    //Programming Language (JAVA,CPP)
    private ProgrammingLanguage programmingLanguage;
    //The actual Code
    private String code;
    //Test cases input to validate the code
    private List<String> customTestCasesInput;
    //Test case expected output
    private List<String> customTestCasesOutput;
}
