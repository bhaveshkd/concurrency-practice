package main.java.com.concurrentCollections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SynchronisedCollection {
    public static void main(String[] args) {
//        List<Integer> list = new ArrayList<>();
        List<Integer> list = Collections.synchronizedList(new ArrayList<>());

        Thread one = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                list.add(i);
            }
        });

        Thread two  = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                list.add(i);
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

        System.out.println("list.size() = " + list.size());

    }

}