package com.gohul.compain_operator;

import com.gohul.common.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class ZIpUsage {

    private static final Logger log = LoggerFactory.getLogger(ZIpUsage.class);

    public static void main(String[] args) {
        zip();

    }

    public static void zip(){

        Flux.zip(head(), body(), hand(), leg())
                .doOnNext(tuple ->
                        log.info("Human --> Head: {}, Body: {}, Hand: {}, Leg: {}", tuple.getT1(), tuple.getT2(), tuple.getT3(), tuple.getT4()))
                .subscribe();

        CommonUtils.timeOut(Duration.ofSeconds(60));

    }

    public static Flux<String> head(){
        return Flux.range(1, 10).map(i -> "Head-" + i)
                .delayElements(Duration.ofMillis(1000));
    }

    public static Flux<String> body(){
        return Flux.range(1, 10)
                .map(i -> "Body-" + i)
                .delayElements(Duration.ofMillis(900));
    }

    public static Flux<String> leg(){
        return Flux.range(1, 10).map(i -> "Leg-" + i)
                .delayElements(Duration.ofMillis(500));
    }

    public static Flux<String> hand(){
        return Flux.range(1, 10).map(i -> "hand-" + i)
                .delayElements(Duration.ofMillis(1500));
    }

}
