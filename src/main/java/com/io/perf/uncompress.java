package com.io.perf;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class uncompress {
    public static void doit(String filein, String fileout) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(filein);
            fos = new FileOutputStream(fileout);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry ze = zis.getNextEntry();
            final int BUFSIZ = 4096;
            byte inbuf[] = new byte[BUFSIZ];
            int n;
            while ((n = zis.read(inbuf, 0, BUFSIZ)) != -1) fos.write(inbuf, 0, n);
            zis.close();
            fis = null;
            fos.close();
            fos = null;
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            try {
                if (fis != null) fis.close();
                if (fos != null) fos.close();
            } catch (IOException e) {
            }
        }
    }

    public static void main(String args[]) {
        if (args.length != 2) {
            System.err.println("missing filenames");
            System.exit(1);
        }
        if (args[0].equals(args[1])) {
            System.err.println("filenames are identical");
            System.exit(1);
        }
        doit(args[0], args[1]);
    }
}

