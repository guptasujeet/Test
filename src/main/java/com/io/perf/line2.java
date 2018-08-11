package com.io.perf;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class line2 {
    public static void main(String args[]) {
        if (args.length != 1) {
            System.err.println("missing filename");
            System.exit(1);
        }
        try {
            FileReader fr = new FileReader(args[0]);
            BufferedReader br = new BufferedReader(fr);
            int cnt = 0;
            while (br.readLine() != null) cnt++;
            br.close();
            System.out.println(cnt);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}

