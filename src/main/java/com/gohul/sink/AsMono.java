package com.gohul.sink;

import com.gohul.common.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

public class AsMono {

    private static final Logger log = LoggerFactory.getLogger(AsMono.class);

    public static void main(String[] args) {

        demo2();
    }

    public static void demo1(){
        Sinks.One<String> sink = Sinks.one();
        Mono<String> mono = sink.asMono();

        mono.subscribe(CommonUtils.subscriber());

        sink.tryEmitValue(CommonUtils.ipAddress());

        // the rest of the following portion will fail as mono can only emit one value(then invoke the complete signal!)
        sink.tryEmitError(new RuntimeException("Boom!"));
        sink.tryEmitEmpty();
    }

    public static void demo2(){
        Sinks.One<String> sink = Sinks.one();
        Mono<String> mono = sink.asMono();

        mono.subscribe(CommonUtils.subscriber());

        sink.tryEmitError(new RuntimeException("Boom!"));

        sink.emitValue(CommonUtils.ipAddress(), (signalType, emitRes) -> {
            log.info("Failure Handler!");
            log.info("SignalType: {}", signalType.name());
            log.info("EmittedResult: {}", emitRes.name());
            return false; // if true -> will retry
        } );

    }
}
