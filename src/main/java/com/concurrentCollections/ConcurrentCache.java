package main.java.com.concurrentCollections;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentCache {
    public static final Map<String, String> cache = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            final int threadNum = i;
            
            new Thread(() -> {
                String key = "Key @" + threadNum;
                for (int j = 0; j < 3; j++) {
                    String value = getCachedValue(key);
                    System.out.println("Thread " + Thread.currentThread().getName() + "Key = " + key + " and Value = " + value);
                }
            }).start();
        }
    }

    private static String getCachedValue(final String key){
        String value = cache.get(key);
        if (value == null){
            value = compute(key);
            cache.put(key, value);
        }
        return value;
    }

    private static String compute(String key){
        System.out.println(key + " is not present in the cache!");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Value for " + key;
    }
}
