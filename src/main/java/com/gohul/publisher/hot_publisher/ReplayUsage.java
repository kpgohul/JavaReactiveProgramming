package com.gohul.publisher.hot_publisher;

import com.gohul.common.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class ReplayUsage {

    private static final Logger log = LoggerFactory.getLogger(ReplayUsage.class);

    public static void main(String[] args) {
        replay();
    }

    public static void replay(){

        Flux<Long> flux = Flux.interval(Duration.ofSeconds(1))
//                .map(i -> CommonUtils.email())
                .replay(1).autoConnect(0);
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
