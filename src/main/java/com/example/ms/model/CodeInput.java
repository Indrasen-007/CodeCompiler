package com.example.ms.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CodeInput {
    private int QuestionId;
    private ProgrammingLanguage programmingLanguage;
    private String code;
    private String customTestCases;
}
