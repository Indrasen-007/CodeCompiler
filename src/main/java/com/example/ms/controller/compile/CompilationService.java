package com.example.ms.controller.compile;

import com.example.ms.controller.compile.language.CPlusPlus;
import com.example.ms.controller.compile.language.Java;
import com.example.ms.controller.compile.language.LanguageProcessor;
import com.example.ms.model.CodeInput;
import com.example.ms.model.CompilationResult;

import java.io.*;
import java.nio.file.Files;

public class CompilationService {
    public static final String BASE_PATH = "./files/";
    public static final String CPP_FILE_PATH = BASE_PATH+"a.cpp";
    public static final String JAVA_FILE_PATH = BASE_PATH+"a.java";
    public static final String OUTPUT_PATH = BASE_PATH+"output.txt";

    public static CompilationResult compileAndRunCode(CodeInput codeInput) throws IOException {
        File file = null;

        CompilationResult compilationResult = new CompilationResult();

        LanguageProcessor languageProcessor ;
        switch(codeInput.getProgrammingLanguage()){
            case CPP:
                file = new File(CPP_FILE_PATH);
                languageProcessor = new CPlusPlus();
                break;
            case JAVA:
                file = new File(JAVA_FILE_PATH);
                languageProcessor = new Java();
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + codeInput.getProgrammingLanguage());
        }

        //write the code to file
        writeToFile(codeInput.getCode(),file);

        //compile and create executable object
        Process processCompile = languageProcessor.buildCode();

        //check if no error in compilation
        String error = compilationError(new InputStreamReader(processCompile.getErrorStream()));

        // If no error
        if(error.equals("")){

            //Execute the code
            Process process = languageProcessor.executeCode();

            //process custom inputs
            if(codeInput.getCustomTestCases()!=null) {
                processInput(process.getOutputStream(),codeInput.getCustomTestCases());
            }

            //output of the execution
            String output = generateOutput(process.getInputStream());

            //set the out to compile result
            compilationResult.setName(output);
            //write the output to file
            writeToFile(output,OUTPUT_PATH);
        }
        else{
            compilationResult.setName(error);
            writeToFile(error,OUTPUT_PATH);
        }

        return compilationResult;
    }

    private static String generateOutput(InputStream inputStream) throws IOException {
        BufferedReader bufferedReaderOutput = new BufferedReader(new InputStreamReader(inputStream));
        String output = "";
        String temp1 = "";
        while((temp1 = bufferedReaderOutput.readLine()) != null) {
            output += temp1 + "\n";
        }
        return output;
    }

    private static void processInput(OutputStream stdin, String customTestCases) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin));
        writer.write(customTestCases);
        //writer.write("10\n13");
        writer.flush();
        writer.close();
    }

    private static String compilationError(InputStreamReader errorStream) throws IOException {
        BufferedReader bufferedReaderError = new BufferedReader(errorStream);
        String error = "";
        String temp = "";
        while((temp = bufferedReaderError.readLine()) != null) {
            error += temp + "\n";
        }
        return error;
    }

    private static void writeToFile(String text, File file) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(text);
        writer.close();
    }
    private static void writeToFile(String text, String filePath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write(text);
        writer.close();
    }
}
