package com.gohul.sink;

import com.gohul.common.CommonUtils;
import reactor.core.publisher.Sinks;

import java.time.Duration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

// Will deliver to all the subscriber as sure,
// The fast subscriber will get affect when any of the subscriber is slow or buffer get filled
// Will emit the next value only when the current values emitted to all the subscribers

public class MultiCast {

    public static void main(String[] args) {

        demo3();
    }

    public static void demo1(){
        Sinks.Many<String> sink = Sinks.many().multicast().onBackpressureBuffer(16);
        var flux = sink.asFlux();

        flux.subscribe(CommonUtils.subscriber("Sub1")); // Fast subscriber
        flux.delayElements(Duration.ofMillis(1000)).subscribe(CommonUtils.subscriber("Sab2")); // Slow subscriber

        sink.tryEmitNext(CommonUtils.capital());
        sink.tryEmitNext(CommonUtils.capital());
        sink.tryEmitNext(CommonUtils.capital());
        sink.tryEmitNext(CommonUtils.capital());
        sink.tryEmitNext(CommonUtils.capital());
        sink.tryEmitNext(CommonUtils.capital());


        CommonUtils.timeOut(Duration.ofSeconds(10));
    }

    // Will focus on the fast subscriber alone, no buffer

    public static void demo2(){
        Sinks.Many<String> sink = Sinks.many().multicast().directBestEffort();
        var flux = sink.asFlux();

        flux.subscribe(CommonUtils.subscriber("Sub1")); // Fast subscriber
        flux.delayElements(Duration.ofMillis(1000)).subscribe(CommonUtils.subscriber("Sab2")); // Slow subscriber

        sink.tryEmitNext(CommonUtils.capital());
        sink.tryEmitNext(CommonUtils.capital());
        sink.tryEmitNext(CommonUtils.capital());
        sink.tryEmitNext(CommonUtils.capital());
        sink.tryEmitNext(CommonUtils.capital());
        sink.tryEmitNext(CommonUtils.capital());

        CommonUtils.timeOut(Duration.ofSeconds(10));

    }

    // It would be delivered the item to all the subscriber or not, no buffer, no skipping, no partial delivery

    public static void demo3(){
        Sinks.Many<String> sink = Sinks.many().multicast().directAllOrNothing();
        var flux = sink.asFlux();

        flux.subscribe(CommonUtils.subscriber("Sub1")); // Fast subscriber
        flux.delayElements(Duration.ofMillis(1000)).subscribe(CommonUtils.subscriber("Sab2")); // Slow subscriber

        for(int i = 1; i < CommonUtils.randInt(50, 100); i++)
            sink.tryEmitNext(CommonUtils.capital());

        CommonUtils.timeOut(Duration.ofSeconds(100));

    }
}
