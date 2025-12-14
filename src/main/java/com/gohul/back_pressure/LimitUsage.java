package com.gohul.back_pressure;

import com.gohul.common.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class LimitUsage {

    private static final Logger log = LoggerFactory.getLogger(LimitUsage.class);

    public static void main(String[] args) {

        var producer = Flux.generate(
                        () -> 1,
                        (state, sink) -> {
                            log.info("generating {}", state);
                            sink.next(state);
                            return ++state;
                        }
                )
                .cast(Integer.class)
                .subscribeOn(Schedulers.parallel());

        producer
                .limitRate(5) // Subscriber will request for "5(Here)" per request, And it will request the same 5 item when the consumer consumed 75% of items(4 in this case!)
                .publishOn(Schedulers.boundedElastic())
                .map(LimitUsage::timeConsumingTask)
                .subscribe(CommonUtils.subscriber());


        CommonUtils.timeOut(Duration.ofSeconds(60));

    }

    private static int timeConsumingTask(int i) {
        CommonUtils.timeOut(Duration.ofSeconds(1));
        return i;
    }
}
