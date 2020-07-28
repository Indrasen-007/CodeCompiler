# AssessmentAsAService
Sample Payload:
URL : http://localhost:8080/compile
------------------
SAMPLE BODY1 : 
{
"QuestionId" : 123,
"programmingLanguage" : "CPP",
"code" : 
        "#include <cmath> \n#include <cstdio> \n #include <vector>\n #include <iostream> \n#include <algorithm> \n using namespace std; int solveMeFirst(int a, int b) { return a+b; } int main() { int num1, num2; int sum; cin>>num1>>num2; sum = solveMeFirst(num1,num2); cout<<sum; return 0; }",
  "customTestCases" :"1\n21"
}

OUTPUT 1:  {"name":"22\n"}

---------------------

SAMPLE BODY2 : 

{
"QuestionId" : 123,
"programmingLanguage" : "JAVA",
"code" : 
"class a{ public static void main(String args[]){ System.out.println(\"Hello World \"); } }"
}

OUTPUT 2:  {"name":"Hello World \n"}

--------------------------------
SAMPLE BODY3 : 

{
"QuestionId" : 123,
"programmingLanguage" : "JAVA",
"code" : 
        "import java.io.*; import java.util.*; import java.text.*; import java.math.*; import java.util.regex.*; public class a { static int solveMeFirst(int a, int b) {  return a+b; } public static void main(String[] args) { Scanner in = new Scanner(System.in); int a; a = in.nextInt(); int b; b = in.nextInt(); int sum; sum = solveMeFirst(a, b); System.out.println(sum); } }",
          "customTestCases" :"11\n24"


}

OUTPUT 3 : 
{"name":"35\n"}
