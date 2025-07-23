package main.java.com.executorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CPUIntensiveTask {
    public static void main(String[] args) {
        int cores = Runtime.getRuntime().availableProcessors();
        try (ExecutorService service = Executors.newFixedThreadPool(cores)) {
            System.out.println("cores = " + cores);
            for (int i = 0; i < 200; i++) {
                service.execute(new CPUTask());
            }
        }
    }
}

class CPUTask implements Runnable{
    @Override
    public void run() {
        System.out.println("Some CPU intensive task being done with thread : " + Thread.currentThread().getName());
    }
}
