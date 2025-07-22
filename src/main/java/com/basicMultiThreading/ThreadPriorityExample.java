package main.java.com.basicMultiThreading;

public class ThreadPriorityExample {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " says Hi!");

        Thread one = new Thread(() -> {
            System.out.println("Thread one says Hi!!!");
        });

        one.setPriority(Thread.MAX_PRIORITY);
        one.start();

    }
}
