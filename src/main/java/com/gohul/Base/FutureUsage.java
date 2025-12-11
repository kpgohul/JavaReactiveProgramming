package com.gohul.Base;

import com.github.javafaker.Faker;
import com.gohul.common.CommonUtils;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.*;

public class FutureUsage {

    // Runnable - execution + doesn't has return value
    // Callable - execution + has return value

    public static void main(String[] args) throws Exception {

//        Runnable run = () -> System.out.println("Hello Guys");
//        run.run();
//        Callable cal = () -> "Hello Guys";
//        System.out.println(cal.call());


//        newSingleThreadExecutor();
//        newFixedThreadPool();
//        newCachedThreadPool();
//        newWorkStealingPool();


    }

    public static void newSingleThreadExecutor() {

        // Each task will be executed one by one as it dealing with only one worker thread
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<String> future = service.submit(() -> {
            CommonUtils.timeOut(Duration.ofSeconds(3));
            return new Faker().country().name();
        });

        try{
            System.out.println(future.get());
        }catch (ExecutionException | InterruptedException e){
            System.out.println("Execution exception raised!");
        }finally {
            service.shutdownNow();
        }
        System.out.println("isDone: "+future.isDone());
        future.cancel(true);
        System.out.println("isCancelled: "+future.isCancelled());

    }

    public static void newFixedThreadPool() {

        // Each task will be executed parallelly with fixed set of worker thread
        ExecutorService service = Executors.newFixedThreadPool(3);
        Future<String> future = service.submit(() -> {
            CommonUtils.timeOut(Duration.ofSeconds(3));
            return new Faker().country().name();
        });

        try{
            System.out.println(future.get());
        }catch (ExecutionException | InterruptedException e){
            System.out.println("Execution exception raised!");
        }finally {
            service.shutdownNow();
        }
        System.out.println("isDone: "+future.isDone());
        future.cancel(true);
        System.out.println("isCancelled: "+future.isCancelled());

    }

    // It create new thread only if needed or else will reuse the idle thread for balancing the load, the thread will be removed if it idle for 1min
    public static void newCachedThreadPool() {

        // Each task will be executed parallelly with fixed set of worker thread
        ExecutorService service = Executors.newCachedThreadPool();
        Future<String> future = service.submit(() -> {
            CommonUtils.timeOut(Duration.ofSeconds(3));
            return new Faker().country().name();
        });

        try{
            System.out.println(future.get());
        }catch (ExecutionException | InterruptedException e){
            System.out.println("Execution exception raised!");
        }finally {
            service.shutdownNow();
        }
        System.out.println("isDone: "+future.isDone());
        future.cancel(true);
        System.out.println("isCancelled: "+future.isCancelled());

    }

    // will create number of thread equal to the number of CPU cores, will utilize the busy thread for balancing the load - the big task will splitted into smaller forks!
    public static void newWorkStealingPool() {

        // Each task will be executed parallelly with fixed set of worker thread
        ExecutorService service = Executors.newWorkStealingPool();
        Future<String> future = service.submit(() -> {
            CommonUtils.timeOut(Duration.ofSeconds(3));
            return new Faker().country().name();
        });

        try{
            System.out.println(future.get());
        }catch (ExecutionException | InterruptedException e){
            System.out.println("Execution exception raised!");
        }finally {
            service.shutdownNow();
        }
        System.out.println("isDone: "+future.isDone());
        future.cancel(true);
        System.out.println("isCancelled: "+future.isCancelled());

    }
}
