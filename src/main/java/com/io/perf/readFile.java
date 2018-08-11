package com.io.perf;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class readFile {
    public static void main(String args[]) {
        if (args.length != 1) {
            System.err.println("missing filename");
            System.exit(1);
        }
        try {
            int len = (int) (new File(args[0]).length());
            FileInputStream fis = new FileInputStream(args[0]);
            byte buf[] = new byte[len];
            fis.read(buf);
            fis.close();
            int cnt = 0;
            for (int i = 0; i < len; i++) {
                if (buf[i] == '\n') cnt++;
            }
            System.out.println(cnt);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}

