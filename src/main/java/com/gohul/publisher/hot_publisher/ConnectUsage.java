package com.gohul.publisher.hot_publisher;

import com.gohul.common.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class ConnectUsage {

    private static final Logger log = LoggerFactory.getLogger(ConnectUsage.class);


    public static void main(String[] args) {
        connect();
    }

    public static void connect(){

        Flux<Long> flux = Flux.interval(Duration.ofSeconds(1))
//                .map(i -> CommonUtils.email())
                .publish().autoConnect(0);
        log.info("Initial Delay!");

        CommonUtils.timeOut(Duration.ofSeconds(3));


        flux
                .doOnSubscribe(s -> log.info("Subscriber1 subscribed!"))
                .subscribe(i -> log.info("Subscriber1: {}", i));


        CommonUtils.timeOut(Duration.ofSeconds(3));
        log.info("Some Delay after!");

        flux
                .doOnSubscribe(s -> log.info("Subscriber2 subscribed!"))
                .subscribe(i -> log.info("Subscriber2: {}", i));

        CommonUtils.timeOut(Duration.ofSeconds(20));


    }
}
