package com.gohul.mono;

import com.gohul.common.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class MonoUsage {

    private static final Logger log = LoggerFactory.getLogger(MonoUsage.class);

    public static void main(String[] args) {
//        mono1();
//        mono2();
        mono3();
    }


    public  static void mono1(){

        Mono.just("Hello Bro!").subscribe(CommonUtils.subscriber("Sub1"));
    }

    public  static void mono2(){

        Mono.just("Hello Bro!")
                .subscribe(
                        val -> log.info("Received: {}",val),
                        ex -> log.error("Error: {}",ex.getMessage()),
                        () -> log.info("Completed!..."),
                        sub -> {
                            log.info("Subscribed!...");
                            sub.request(Long.MAX_VALUE);
                        }
                );
    }

    public  static void mono3(){

        Mono.error(new RuntimeException("Dummy Boy!")).subscribe(CommonUtils.subscriber("Sub1"));
    }



}
