package com.io.perf;

import java.io.FileInputStream;
import java.io.IOException;

public class intro1 {
    public static void main(String args[]) {
        if (args.length != 1) {
            System.err.println("missing filename");
            System.exit(1);
        }
        try {
            FileInputStream fis = new FileInputStream(args[0]);
            int cnt = 0;
            int b;
            while ((b = fis.read()) != -1) {
                if (b == '\n') cnt++;
            }
            fis.close();
            System.out.println(cnt);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}

