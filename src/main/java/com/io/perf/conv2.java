package com.io.perf;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class conv2 {
    public static void main(String args[]) {
        try {
            FileOutputStream fos = new FileOutputStream("out2");
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF8");
            PrintWriter pw = new PrintWriter(osw);
            pw.println("\uffff\u4321\u1234");
            pw.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}

