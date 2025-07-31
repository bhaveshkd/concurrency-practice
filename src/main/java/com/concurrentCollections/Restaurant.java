package main.java.com.concurrentCollections;

import java.util.concurrent.CountDownLatch;

public class Restaurant {
    public static void main(String[] args) throws InterruptedException {
        int numOfChefs = 3;
        CountDownLatch latch = new CountDownLatch(numOfChefs);
        new Thread(new Chef("ChefA", "Pizza", latch)).start();
        new Thread(new Chef("ChefB", "Pasta", latch)).start();
        new Thread(new Chef("ChefC", "Salad", latch)).start();

        latch.await();

        System.out.println("All the dishes are ready!!!");
    }
}

class Chef implements Runnable{

    private final String name;
    private final String dish;
    private final CountDownLatch latch;

    public Chef(String name, String dish, CountDownLatch latch) {
        this.name = name;
        this.dish = dish;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            System.out.println(name + " is preparing " + dish);
            Thread.sleep(2000);
            System.out.println(name + " has finished preparing " + dish);
            latch.countDown();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}