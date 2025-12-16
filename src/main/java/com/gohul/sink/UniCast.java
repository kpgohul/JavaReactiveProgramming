package com.gohul.sink;

import com.gohul.common.CommonUtils;
import reactor.core.publisher.Sinks;

public class UniCast {

    // Allow only one subscriber to consume

    public static void main(String[] args) {
        unicast();
    }

    public static void unicast(){
        Sinks.Many<String> sink = Sinks.many().unicast().onBackpressureBuffer();

        var flux = sink.asFlux();

        flux.subscribe(CommonUtils.subscriber());
//        flux.subscribe(CommonUtils.subscriber()); // will throw an error as it allowing only one subscriber

        sink.tryEmitNext(CommonUtils.ipAddress());
        sink.tryEmitNext(CommonUtils.ipAddress());
        sink.tryEmitNext(CommonUtils.ipAddress());
        sink.tryEmitNext(CommonUtils.ipAddress());
    }


}
