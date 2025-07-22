package main.java.com.executorService;

import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPoolDemo {
    public static void main(String[] args) {
        try (ExecutorService service = Executors.newCachedThreadPool()) {
            for (int i = 0; i < 1000; i++) {
                service.execute(new TaskOne(i));
            }
        }

        System.out.println("TaskOne.threads.size() = " + TaskOne.threads.size());
    }
}

class TaskOne implements Runnable{

    private final int taskId;

    public static HashSet<String> threads;

    public TaskOne(int taskId) {
        this.taskId = taskId;
        threads = new HashSet<>();
    }

    @Override
    public void run() {
        System.out.println("Task: " + taskId + " being executed by " + Thread.currentThread().getName());
        threads.add(Thread.currentThread().getName());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}