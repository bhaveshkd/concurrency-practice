package main.java.com.executorService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorDemo {
    public static void main(String[] args) {
        try (ScheduledExecutorService service = Executors.newScheduledThreadPool(1)) {
            service.scheduleAtFixedRate(new ProbeTask(), 1000, 2000, TimeUnit.MILLISECONDS);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class ProbeTask implements Runnable{
    @Override
    public void run() {
        System.out.println("Probing endpoint for updates...");
    }
}