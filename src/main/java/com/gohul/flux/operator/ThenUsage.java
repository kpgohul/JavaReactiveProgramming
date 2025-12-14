package com.gohul.flux.operator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class ThenUsage {

    private static final Logger log = LoggerFactory.getLogger(ThenUsage.class);

    public static void main(String[] args) {

        var names = List.of("gohul", "rahul");

        Flux.fromIterable(names)
                .then(sendNotification(names))
                .subscribe();

    }

    public static Mono<Void> sendNotification(List<String> usernames) {
        return Flux.fromIterable(usernames)
                .doOnNext(name -> log.info("Sent notification to {}", name))
                .then();   // completes with Mono<Void>
    }

}
