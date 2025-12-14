package com.gohul.publisher.hot_publisher;

import com.gohul.common.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class ShareUsage {

        private static final Logger log = LoggerFactory.getLogger(ShareUsage.class);

    public static void main(String[] args) {
        share();
    }

    public static void share(){

        Flux<Long> flux = Flux.interval(Duration.ofSeconds(1))
//                .map(i -> CommonUtils.email())
                .share();


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
