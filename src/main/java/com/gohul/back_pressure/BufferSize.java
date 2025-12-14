package com.gohul.back_pressure;

import com.gohul.common.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class BufferSize {

    private static final Logger log = LoggerFactory.getLogger(BufferSize.class);


    public static void main(String[] args) {

        System.setProperty("reactor.bufferSize.small", "16"); // Set the buffer size for every subscriber as 16 (Default - 256)

        var producer = Flux.generate(
                        () -> 1,
                        (state, sink) -> {
                            log.info("generating {}", state);
                            sink.next(state);
                            return ++state;
                        }
                )
                .cast(Integer.class)
                .subscribeOn(Schedulers.parallel()); // The Producer will run on the parallel scheduler (CPU intensive task)

        producer
                .publishOn(Schedulers.boundedElastic()) // The Downstream will run on the elastic scheduler (Delayed task)
                .map(BufferSize::timeConsumingTask)
                .subscribe(CommonUtils.subscriber());


        CommonUtils.timeOut(Duration.ofSeconds(60));


    }

    private static int timeConsumingTask(int i) {
        CommonUtils.timeOut(Duration.ofSeconds(1));
        return i;
    }
}
