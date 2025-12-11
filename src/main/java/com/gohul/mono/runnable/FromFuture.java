package com.gohul.mono.runnable;

import com.gohul.common.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class FromFuture {

    private static final Logger log = LoggerFactory.getLogger(FromFuture.class);

    public static void main(String[] args) {
        fromFuture();
    }

    public static void fromFuture(){

        Mono<String> objectMono = Mono.fromFuture(supplyAsync()).map(i -> i + " newly added");
        objectMono.subscribe(CommonUtils.subscriber("Sub1"));

        Mono<Void> voidMono = Mono.fromFuture(runAsync());
        voidMono.subscribe(CommonUtils.subscriber("Sub2"));


    }

    private static CompletableFuture<String> supplyAsync(){
        return CompletableFuture.supplyAsync(() -> "Hello Guys!");
    }
    private static CompletableFuture<Void> runAsync(){
        return CompletableFuture.runAsync(() -> log.info("From runAsync!"));
    }


}
