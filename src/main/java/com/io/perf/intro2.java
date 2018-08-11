package com.io.perf;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class intro2 {
    public static void main(String args[]) {
        if (args.length != 1) {
            System.err.println("missing filename");
            System.exit(1);
        }
        try {
            FileInputStream fis = new FileInputStream(args[0]);
            BufferedInputStream bis = new BufferedInputStream(fis);
            int cnt = 0;
            int b;
            while ((b = bis.read()) != -1) {
                if (b == '\n') cnt++;
            }
            bis.close();
            System.out.println(cnt);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
