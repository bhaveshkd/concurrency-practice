package main.java.com.basicMultiThreading;

public class WaitAndNotifyDemo {
    public static final Object LOCK = new Object();

    public static void main(String[] args) {
        Thread one  = new Thread(() -> {
            try {
                one();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread two  = new Thread(() -> {
            try {
                two();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        one.start();
        two.start();

        try {
            one.join();
            two.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private static void one() throws InterruptedException {
        synchronized (LOCK){
            System.out.println("Hello from method one...");
            LOCK.wait();
            System.out.println("Back again in method one!");
        }
    }

    private static void two() throws InterruptedException {
        synchronized (LOCK){
            System.out.println("Hello from method two...");
            LOCK.notify();
            System.out.println("Hello from method two after notifying!");
        }
    }
}
