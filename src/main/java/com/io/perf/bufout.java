package com.io.perf;

import java.io.BufferedOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class bufout {
    public static void main(String args[]) {
        FileOutputStream fdout = new FileOutputStream(FileDescriptor.out);
        BufferedOutputStream bos = new BufferedOutputStream(fdout, 1024);
        PrintStream ps = new PrintStream(bos, false);
        System.setOut(ps);
        final int N = 100000;
        for (int i = 1; i <= N; i++) System.out.println(i);
        ps.close();
    }
}

