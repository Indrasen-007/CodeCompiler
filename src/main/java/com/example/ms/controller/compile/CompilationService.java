package com.example.ms.controller.compile;

import com.example.ms.controller.compile.language.CPlusPlusProcessor;
import com.example.ms.controller.compile.language.CSharpProcessor;
import com.example.ms.controller.compile.language.ILanguageProcessor;
import com.example.ms.controller.compile.language.JavaProcessor;
import com.example.ms.model.CodeInput;
import com.example.ms.model.CompilationResult;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CompilationService {
    public static final String BASE_PATH = ".\\files\\";
    public static final String C_SHARP_FILE_NAME = "CSharpSolution.cs";
    public static final String CPP_FILE_PATH = BASE_PATH + "Solution.cpp";
    public static final String JAVA_FILE_PATH = BASE_PATH + "Solution.java";
     public static final String C_SHARP_FILE_PATH = BASE_PATH.concat(C_SHARP_FILE_NAME);

    public static CompilationResult compileAndRunCode(CodeInput codeInput) {
        CompilationResult compilationResult = new CompilationResult();
        try {
            File file = null;
            ILanguageProcessor ILanguageProcessor;

            switch (codeInput.getProgrammingLanguage()) {
                case CPP:
                    file = createFileIfNotExists(CPP_FILE_PATH);
                    ILanguageProcessor = new CPlusPlusProcessor();
                    break;

                case JAVA:
                    file = createFileIfNotExists(JAVA_FILE_PATH);
                    ILanguageProcessor = new JavaProcessor();
                    break;

                case CS:
                    file = createFileIfNotExists(C_SHARP_FILE_PATH);
                    ILanguageProcessor = new CSharpProcessor();
                    break;

                default:
                    throw new IllegalStateException("Unexpected value: " + codeInput.getProgrammingLanguage());
            }

            //write the code to file
            writeToFile(codeInput.getCode(), file);

            //compile and create executable object
            Process processCompile = ILanguageProcessor.buildCode();

            //check if no error in code compilation
            String error = compilationError(new InputStreamReader(processCompile.getErrorStream()));

            // If no error
            if (error.equals("")) {
                //Execute the code
                Process process;
                List<String> output = new ArrayList<>();

                //process custom inputs
                if (codeInput.getCustomTestCasesInput() != null && !codeInput.getCustomTestCasesInput().isEmpty()) {
                    for (String input : codeInput.getCustomTestCasesInput()) {
                        process = ILanguageProcessor.executeCode();
                        //add input
                        processInput(process.getOutputStream(), input);
                        //output of the execution
                        output.add(generateOutput(process.getInputStream()));
                    }
                }

                //set the out to compile result
                compilationResult.setResult(output);
                assertTestCase(codeInput.getCustomTestCasesOutput(), output, compilationResult);
            } else {
                //error occurred
                compilationResult.setError(error);
            }
        } catch (Exception e) {
            compilationResult.setError(e.getMessage());
        }
        return compilationResult;
    }

    private static File createFileIfNotExists(String path) throws IOException {
        File file = new File(path);
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        if (!file.exists())
            file.createNewFile();
        return file;
    }

    private static void assertTestCase(List<String> expectedOutput, List<String> actualOutput, CompilationResult compilationResult) {
        int pass = 0;
        if (expectedOutput.size() != actualOutput.size()) {
            compilationResult.setError("Test cases do match");
            return;
        } else {
            String expectedOutputString;
            int totalTestCases = expectedOutput.size();
            //match if the expected test case and actual are same or not
            for (int i = 0; i < totalTestCases; i++) {
                expectedOutputString = expectedOutput.get(i) + "\n";
                if (expectedOutputString.equals(actualOutput.get(i))) {
                    pass++;
                }
            }
            //restrict percentage to 2 decimal digits
            DecimalFormat df = new DecimalFormat("#.##");
            double passPercentage = pass * 100.0 / totalTestCases;
            passPercentage = Double.valueOf(df.format(passPercentage));
            compilationResult.setTotalTestCasesPassed(pass);
            compilationResult.setTotalTestCase(totalTestCases);
            compilationResult.setPassPercentage(passPercentage);
        }
    }

    private static String generateOutput(InputStream inputStream) throws IOException {
        BufferedReader bufferedReaderOutput = new BufferedReader(new InputStreamReader(inputStream));
        String output = "";
        String temp1;
        while ((temp1 = bufferedReaderOutput.readLine()) != null) {
            output += temp1 + "\n";
        }
        return output;
    }

    private static void processInput(OutputStream stdin, String customTestCases) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin));
        writer.write(customTestCases);
        writer.flush();
        writer.close();
    }

    private static String compilationError(InputStreamReader errorStream) throws IOException {
        BufferedReader bufferedReaderError = new BufferedReader(errorStream);
        String error = "";
        String temp;
        while ((temp = bufferedReaderError.readLine()) != null) {
            error += temp + "\n";
        }
        return error;
    }

    private static void writeToFile(String text, File file) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(text);
        writer.close();
    }
}
