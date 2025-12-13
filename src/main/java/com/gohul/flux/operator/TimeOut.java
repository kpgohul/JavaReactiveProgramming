package com.gohul.flux.operator;

import com.gohul.common.CommonUtils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;

import java.time.Duration;

public class TimeOut {

    public static void main(String[] args) {
        timeOut2();
    }

    public static void timeOut1(){
        Flux.interval(Duration.ofMillis(500))
                .timeout(Duration.ofMillis(300))
                .subscribe(CommonUtils.subscriber());

        CommonUtils.timeOut(Duration.ofSeconds(5));
    }

    public static void timeOut2(){
        Flux.interval(Duration.ofMillis(500))
                .timeout(Duration.ofMillis(300), fallback())
                .subscribe(CommonUtils.subscriber());

        CommonUtils.timeOut(Duration.ofSeconds(5));
    }

    public static Flux<Long> fallback(){
        CommonUtils.timeOut(Duration.ofSeconds(1));
        return Flux.just(1L, 2L, 4L);
    }

}
