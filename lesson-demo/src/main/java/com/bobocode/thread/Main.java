package com.bobocode.thread;

public class Main {

    public static String lock1 = "Lock1";
    public static String lock2 = "Lock2";

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable1 = () -> {
            try {
                printHello();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Runnable runnable2 = () -> {
            try {
                printWorld();

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };



        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);

        Runnable runnable3 = () -> {
            while (true) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Thread1 state" + thread1.getState().toString());
                System.out.println("Thread2 state" + thread2.getState().toString());
            }
        };

        thread1.start();
        thread2.start();

        var thread3 = new Thread(runnable3);
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println("Done");
    }

    public static void printHello() throws InterruptedException {
        synchronized (lock1) {
            Thread.sleep(3000);
            System.out.println("Hello");
            printWorld();
        }
    }

    public static void printWorld() throws InterruptedException {
        synchronized (lock2) {
            Thread.sleep(3000);
            System.out.println("World");
            printHello();
        }
    }
}
