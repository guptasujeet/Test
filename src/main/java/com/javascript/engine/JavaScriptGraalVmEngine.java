package com.javascript.engine;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

import javax.script.ScriptException;

public class JavaScriptGraalVmEngine {

    static String JS_CODE = "(function myFun(param){console.log('hello '+param);})";

    public static void main(String[] args) throws ScriptException {
        String script1Code = "(function hello(name) {print ('Hello, ' + name);})";
        String script2Code = "(function getValue(a,b) { if (a===\"Number\") return 1; else return b;})";
        try (Context context = Context.newBuilder().allowAllAccess(true).build()) {

            // evaluate script
            Value script1 = context.eval("js", script1Code);
            Value script2 = context.eval("js", script2Code);

            Value script3 = context.eval("js", JS_CODE);
            script3.execute("Sujeet");

            script1.executeVoid("Sujeet");
            Value script2Result = script2.execute(1, 2);
            System.out.println("Script2 Result -> " + script2Result);

            Student existingStudent = new Student("Missing", "Missing", 0, "0302CS071084");

            String complexCode = """
                    (
                        function getStudent(fn, ln, mathNum, engNum, scienceNum) {
                            var total = mathNum + engNum + scienceNum;
                            var StudentClass = Java.type('com.javascript.engine.Student');
                            var existingRollNum = existingStudent.getRollNum();
                            var student = new StudentClass(fn, ln, total, existingRollNum);
                            return student;
                        }
                    )
                    """;
            //existingStudent will be available from java to javaScript
            context.getBindings("js").putMember("existingStudent", existingStudent);
            Value complexCodeFunction = context.eval("js", complexCode);
            Value complexCodeResult = complexCodeFunction.execute("Sujeet", "Gupta", 5, 10, 15);
            System.out.println(complexCodeResult);

            Student student = complexCodeResult.as(Student.class);
            System.out.println("Now as java object -> " + student);

        }
    }


}
