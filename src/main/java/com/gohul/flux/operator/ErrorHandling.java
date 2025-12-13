package com.gohul.flux.operator;

import com.gohul.common.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class ErrorHandling {

    private static final Logger log = LoggerFactory.getLogger(ErrorHandling.class);

    public static void main(String[] args) {
        onErrorResume();
    }

    public static void onErrorComplete(){
        Flux.error(new RuntimeException("Just an error!"))
                .onErrorComplete() // send the complete signal if error occurred
                .subscribe();
    }

    public static void onErrorReturn(){
        Flux.error(new RuntimeException("Just an error!"))
                .onErrorReturn(-1) // send a default value if error occurred
                .subscribe();
    }

    public static void onErrorContinue(){
        Flux.just(1)
                .map(i -> {
                    throw new RuntimeException("Boom!");
                })
                .onErrorContinue((ex, val) ->  // Will look for the error raised from the operator while processing the value
                        System.out.println("Error: " + ex.getMessage() + " | Value: " + val)
                )
                .subscribe();
    }

    private static void onErrorResume(){
        Flux.just(1, 2, 3, 4, 5)
                .map(i -> {
                    if(i == 6) throw new RuntimeException("Boom!");
                    if(i == 5) return i/0;
                    else return i;
                })
                // Used to consume form different publisher when an error occurred
                .onErrorResume(ArithmeticException.class, ex -> fallBack1()) // Handle for specific
                .onErrorResume(ex -> fallBack2()) // Handle all
                .onErrorReturn(-1) // for default
                .subscribe(CommonUtils.subscriber());

    }


    private static Flux<Integer> fallBack1(){
        return Flux.range(1, 5)
                .map(i -> CommonUtils.randInt(11, 20));
    }

    private static Flux<Integer> fallBack2(){
        return Flux.range(1, 3)
                .map(i -> CommonUtils.randInt(1, 10));
    }


}
