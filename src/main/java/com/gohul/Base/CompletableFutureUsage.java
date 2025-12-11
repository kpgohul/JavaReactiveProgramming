package com.gohul.Base;

import com.gohul.common.CommonUtils;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureUsage {

    // Allow Async behaviour
    // non-blocking
    // call-backs

    public static void main(String[] args) {

//        runAsyncAndSupplyAsync();
        modifyTheResult();

    }

    public static void runAsyncAndSupplyAsync(){
        // Execute the logic but won't return any result
        CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(() -> System.out.println("Hi Hello - RunAsync"));
        Void join = completableFuture1.join();

        //Execute the logic and returns a value as well
        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            CommonUtils.timeOut(Duration.ofSeconds(5));
            return "Hi Hello - SupplyAsync";
        });
        completableFuture2.cancel(true);
        String res = completableFuture2.join();
        System.out.println(res);
        System.out.println("isDone: "+completableFuture2.isDone());
        System.out.println("isCancelled: "+completableFuture2.isCancelled());

    }

    public static void modifyTheResult(){

        // The result from the one callable will passed over the chain

        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> "Hi Hello - SupplyAsync")
                .thenApply(val -> val + "newly added");  //returns value finally
        String str = completableFuture2.join();
        System.out.println(str);

        var cf = CompletableFuture.supplyAsync(() -> "Hello World")
                .thenAccept(val -> System.out.println("Newly Added ")); //doesn't return value finally
        Void join = cf.join();
        System.out.println(join);


        //The result from one runnable/callable won't pass over the chain

        CompletableFuture<Void> completableFuture3 = CompletableFuture.supplyAsync(() -> "Hi Hello - SupplyAsync")
                .thenRun(() -> System.out.println("Finally"));
        System.out.println(completableFuture3.join());




    }

    public static void handleException(){

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync( () -> {throw new RuntimeException("EEEE - Summa!");});

        completableFuture.exceptionally(ex -> {
            System.out.println(ex.getMessage());
            return "Exception Happened";
        });

        System.out.println(completableFuture.join());


    }
}
