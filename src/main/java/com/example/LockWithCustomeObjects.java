package main.java.com.example;

public class LockWithCustomeObjects {

    private static int counter1 = 0;
    private static int counter2 = 0;

    public static final Object lock1 = new Object();
    public static final Object lock2 = new Object();

    public static void main(String[] args) {
        Thread one  = new Thread(() -> {
           for (int i = 0; i < 1000000; i++){
               increment1();
           }
        });

        Thread two  = new Thread(() -> {
            for (int i = 0; i < 1000000; i++){
                increment2();
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

        System.out.println(counter1 + " -- " + counter2);
    }

    private static void increment1(){
        synchronized (lock1){
            counter1++;
        }
    }

    private static void increment2(){
        synchronized (lock2){
            counter2++;
        }
    }
}
