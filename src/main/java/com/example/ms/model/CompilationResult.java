package com.example.ms.model;

import lombok.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CompilationResult {
    //Number of test cases passed
    private int totalTestCasesPassed;
    //Total number of test cases
    private int totalTestCase;
    //Pass Percentage
    private double passPercentage;
    //Output of each test case
    private List<String> result;
    //error if any
    private String error;
}
