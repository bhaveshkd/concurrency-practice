package main.java.com.executorService;

import java.util.concurrent.*;

public class CallableDemo {
    public static void main(String[] args) {
        try(ExecutorService service = Executors.newFixedThreadPool(2)){
            Future<Integer> res = service.submit(new ReturnValueTask(1));
//            Future<Integer> res2 = service.submit(new ReturnValueTask(2));

            try {
                System.out.println(res.get());
//                System.out.println(res2.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

    }
}

class ReturnValueTask implements Callable<Integer> {
    int id;
    ReturnValueTask(int id){
        this.id = id;
    }
    @Override
    public Integer call(){
        System.out.println("Executing Task " + id + " on thread: " + Thread.currentThread().getName());
        return 3;
    }
}