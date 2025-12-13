package com.gohul.factoryMethod;

import com.gohul.common.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class FluxGenerate {

    private static final Logger log = LoggerFactory.getLogger(FluxGenerate.class);

    public static void main(String[] args) {

        fluxGenerate2();

    }

    public static void fluxGenerate1() {

        Flux<Integer> fluxGen = Flux.generate(
                // Initial state (starts from 1)
                () -> 1,

                // Generator logic: emit current value, increment state, stop at 11
                (state, sink) -> {
                    if (state == 11) {
                        sink.complete();   // complete the Flux
                        return state;      // return final state
                    }
                    if(state == 5) sink.error(new RuntimeException("Just an error!"));
                    sink.next(state);      // emit current value
                    return state + 1;      // new state for next iteration
                },

                // Cleanup logic executed after termination
                finalState -> log.info("Cleanup state value: {}", finalState)
        );

        fluxGen.doOnCancel(() -> log.info("Cancelled..."))
                .subscribe(
                value -> log.info("Generated Value: {}", value),     // onNext
                error -> log.error("Exception: {}", error.getMessage()), // onError
                () -> log.info("Completed..."),                      // onComplete
                subscription -> {
                    log.info("Subscribed to Flux.generate()");// onSubscribe
                    subscription.request(Long.MAX_VALUE);
                }
        );

        CommonUtils.timeOut(Duration.ofSeconds(10)); // Give time to finish before main thread exits
    }

    public static void fluxGenerate2(){
        Flux<Integer> flux = Flux.generate(
                syncSink -> {
                    int rand = CommonUtils.randInt(1, 12);
                    if(rand == 11) syncSink.complete();
                    syncSink.next(rand);
                }
        );

        flux.subscribe(CommonUtils.subscriber("Sub1"));
    }

}
