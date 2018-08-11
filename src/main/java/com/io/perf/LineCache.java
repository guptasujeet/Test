package com.io.perf;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LineCache {
    private ArrayList list = new ArrayList();

    public LineCache(String fn) throws IOException {
        FileReader fr = new FileReader(fn);
        BufferedReader br = new BufferedReader(fr);
        String ln;
        while ((ln = br.readLine()) != null) list.add(ln);
        br.close();
    }

    public String getLine(int n) {
        if (n < 0) throw new IllegalArgumentException();
        return (n < list.size() ? (String) list.get(n) : null);
    }

    public static void main(String args[]) {
        if (args.length != 1) {
            System.err.println("missing filename");
            System.exit(1);
        }
        try {
            LineCache lc = new LineCache(args[0]);
            int i = 0;
            String ln;
            while ((ln = lc.getLine(i++)) != null) System.out.println(ln);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}

