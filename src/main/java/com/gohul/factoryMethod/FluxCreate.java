package com.gohul.factoryMethod;

import com.gohul.common.CommonUtils;
import com.gohul.factoryMethod.assist.IPAddrGenerator;
import org.reactivestreams.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FluxCreate {

    private static final Logger log = LoggerFactory.getLogger(FluxCreate.class);

    public static void main(String[] args) {
        fluxCreate2();
    }

    public static void fluxCreate(){
        Flux.create(
                sink -> {
                    for(int i=0; i<10; i++) sink.next(i);
                    sink.complete();
                }
        ).subscribe(CommonUtils.subscriber());
    }

    public static void fluxCreate2(){
        List<String> ips = new ArrayList<>();
        var generator = new IPAddrGenerator();
        var flux = Flux.create(generator);
        flux.subscribe(val -> ips.add(val));

        Runnable run = () -> {
            for(int i=1; i<=1000; i++) generator.generate();
        };

        for(int i=1; i<=10; i++){
            Thread.ofPlatform().start(run);
        }

        CommonUtils.timeOut(Duration.ofSeconds(5));
        log.info("List Size: {}", ips.size());
    }


//    Overflow strategies:
//    BUFFER → stores all values until subscriber is ready
//    DROP → ignores values when subscriber is slow
//    ERROR → throws error when subscriber is slow
//    LATEST → keeps only latest item

    public static void fluxCreate3(){
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Flux<Integer> flux = Flux.create(sink -> {
            // register a listener / start asynchronous work
            executor.submit(() -> {
                try {
                    for (int i = 1; i <= 10; i++) {
                        if (sink.isCancelled()) {
                            System.out.println("Subscriber cancelled — stopping producer");
                            return;
                        }
                        sink.next(i);  // can call many times
                        CommonUtils.timeOut(Duration.ofSeconds(2)); // simulate async events
                    }
                    sink.complete();
                } catch (Throwable t) {
                    sink.error(t);
                }
            });
        }, FluxSink.OverflowStrategy.BUFFER); // Overflow strategy chosen

        // Subscriber that only requests 5 items
        flux
                .take(5) // demonstrates cancellation and how sink.isCancelled() can help stop producing
                .subscribe(
                        x -> System.out.println("onNext: " + x),
                        err -> System.err.println("onError: " + err),
                        () -> System.out.println("Completed")
                );

        // give the executor some time then shutdown
        CommonUtils.timeOut(Duration.ofSeconds(2));
        executor.shutdown();
    }
}
