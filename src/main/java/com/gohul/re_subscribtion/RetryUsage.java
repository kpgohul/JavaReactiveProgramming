package com.gohul.re_subscribtion;

import com.gohul.common.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class RetryUsage {

    private static final Logger log = LoggerFactory.getLogger(RetryUsage.class);

    public static void main(String[] args) {

        retry2();
    }

    public static void retry1(){
        AtomicInteger integer = new AtomicInteger(1);
        publisher(integer)
                .retry(6)
                .subscribe();
    }

    public static void retry2(){
        AtomicInteger integer = new AtomicInteger(1);
        publisher(integer)
                .retryWhen(Retry.fixedDelay(6, Duration.ofSeconds(2))
                        .onRetryExhaustedThrow((spec, signal) -> signal.failure()))
                .subscribe();

        CommonUtils.timeOut(Duration.ofSeconds(30));
    }

    public static Flux<Integer> publisher(AtomicInteger integer) {

        return Flux.<Integer>generate(
                (sink) -> {

                    int curr = integer.get();

                    if (curr > 10) {
                        sink.complete();
                    }
                    else if (curr < 5) {
                        sink.error(new RuntimeException("Boom!"));
                    }
                    else {
                        sink.next(curr);
                    }
                    integer.set(curr + 1);

                }
        )
                .doOnError(ex -> log.info("Exception: {}", ex.getMessage()))
                .doOnNext(val -> log.info("Received: {}", val));
    }



}
