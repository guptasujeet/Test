package com.thread;

/**
 * Created by Sujeet on 20/01/19.
 */
public class KeepMainThreadRunning {


    public static void main(String[] args) throws InterruptedException {

        startListener();

        System.out.println("Main finished");
    }


    public static void startListener() {
        System.out.println("Listener started");
        Thread thread = new Thread(KeepMainThreadRunning::run);
        System.out.println("Is daemon thread : " + thread.isDaemon());
        //by default newly created thread is non-daemon only (inheriting parent daemon state), setting here just for illustration
        thread.setDaemon(false);
        thread.start();
        System.out.println("Thread started");
    }

    private static void run() {
        for (int i = 0; i <= 10; i++) {
            try {
                Thread.sleep(300);
                System.out.println("Finished task " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
