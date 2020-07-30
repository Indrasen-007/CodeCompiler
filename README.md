# AssessmentAsAService
To clone the project : `git clone -b AssessmentAsAServiceBackend --single-branch https://msazure.visualstudio.com/DefaultCollection/One/_git/DI-ProjectSangam`

To Run the Project:   
1. Open the project in IntelliJ
2. And run the "COde" from intelliJ   

URL : http://localhost:8080/compile

### ***NOTE:  THE NAME OF THE FILE TO EXECUTE SHOULD ALWAYS BE "Solution"***

Input Param
-------
    //Question Id
    int QuestionId;
    //Programming Language (JAVA,CPP,CS)
    ProgrammingLanguage programmingLanguage;
    //The actual Code
    String code;
    //Test cases input to validate the code
    List<String> customTestCasesInput;
    //Test case expected output
    List<String> customTestCasesOutput;

Output Param
-----------
    //Number of test cases passed
    int totalTestCasesPassed;
    //Total number of test cases
    int totalTestCase;
    //Pass Percentage
    double passPercentage;
    //Output of each test case
    List<String> result;
    //error if any
    String error;
    
Sample Payload:
------------------
EXAMPLE 1 : A simple addition of two numbers C++ code, with 3 input sets  11 & 24 , 1 & 2, -2 & -5.     
REQUEST BODY 1:      
   ```
     {
         "QuestionId": 123,
         "programmingLanguage": "JAVA",
         "code": "import java.io.*; import java.util.*; import java.text.*; import java.math.*; import java.util.regex.*; public class Solution { static int solveMeFirst(int a, int b) { return a+b; } public static void main(String[] args) { Scanner in = new Scanner(System.in); int a; a = in.nextInt(); int b; b = in.nextInt(); int sum; sum = solveMeFirst(a, b); System.out.println(sum); } }",
         "customTestCasesInput": [
           "11\n24",
           "1\n2",
           "-2\n-5"
         ],
         "customTestCasesOutput": [
           "35",
           "3",
           "-7"
         ]
    } 
```


OUTPUT 1:
```$xslt
{
    "totalTestCasesPassed": 3,
    "totalTestCase": 3,
    "passPercentage": 100,
    "result": [
        "35\n",
        "3\n",
        "-7\n"
    ],
    "error": null
}
```
---------------------
EXAMPLE 2 : A simple addition of two numbers C# code, with 3 input sets  11 & 24 , 1 & 2, -2 & -5.     
REQUEST BODY 2:     
```$xslt
{
  "QuestionId": 123,
  "programmingLanguage": "CS",
  "code": "using System; using System.Collections.Generic; using System.IO; class Solution { static int solveMeFirst(int a, int b) { return a+b; } static void Main(String[] args) { int val1 = Convert.ToInt32(Console.ReadLine()); int val2 = Convert.ToInt32(Console.ReadLine()); int sum = solveMeFirst(val1,val2); Console.WriteLine(sum); } }",
  "customTestCasesInput": [
    "11\n24",
    "1\n2",
    "-2\n-5"
  ],
  "customTestCasesOutput": [
    "35",
    "3",
    "-7"
  ]
}
```
OUTPUT 2: 
```$xslt
{
    "totalTestCasesPassed": 3,
    "totalTestCase": 3,
    "passPercentage": 100,
    "result": [
        "35\n",
        "3\n",
        "-7\n"
    ],
    "error": null
}
```
---------------------
EXAMPLE 3 : A simple addition of two numbers C++ code, with 3 input sets  11 & 24 , 1 & 2, -2 & -5.     
REQUEST BODY 3:  
```$xslt
{
"QuestionId" : 123,
"programmingLanguage" : "CPP",
"code" :
"#include <cmath> \n#include <cstdio> \n #include <vector>\n #include <iostream> \n#include <algorithm> \n using namespace std; int solveMeFirst(int a, int b) { return a+b; } int main() { int num1, num2; int sum; cin>>num1>>num2; sum = solveMeFirst(num1,num2); cout<<sum; return 0; }",
"customTestCasesInput" :["11\n24","1\n2","-2\n-5"],
"customTestCasesOutput" :["35","3","-7"]
}
```
OUTPUT 3: 
```$xslt
{
    "totalTestCasesPassed": 3,
    "totalTestCase": 3,
    "passPercentage": 100,
    "result": [
        "35\n",
        "3\n",
        "-7\n"
    ],
    "error": null
}
```
-------------

EXAMPLE 4 : A simple hello world program with syntax error in JAVA   
REQUEST BODY 4: 
```     
{         
"QuestionId" : 123,       
"programmingLanguage" : "JAVA",      
"code" :        
"class Solution{ public static void main(String args[]){ System.out.prinhjgtln(\"Hello World \"); } }"
}
```
OUTPUT 4: 
 ```
{"totalTestCasesPassed":0,"totalTestCase":0,"passPercentage":0.0,"result":null,"error":"./files/Solution.java:1: error: cannot find symbol\nclass Solution{ public static void main(String args[]){ System.out.prinhjgtln(\"Hello World \"); } }\n ^\n symbol: method prinhjgtln(String)\n location: variable out of type PrintStream\n1 error\n"}
```
--------------------------------
