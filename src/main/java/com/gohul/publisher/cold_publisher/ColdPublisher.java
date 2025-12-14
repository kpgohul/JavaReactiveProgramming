package com.gohul.publisher.cold_publisher;

import reactor.core.publisher.Flux;

public class ColdPublisher {

    public static void main(String[] args) {
        Flux<Integer> coldFlux = Flux.range(1, 5)
                .doOnSubscribe(s -> System.out.println("New Subscription Started"))
                .doOnNext(i -> System.out.println("Emitting: " + i));

        System.out.println("Subscriber 1 Subscribing...");
        coldFlux.subscribe(i -> System.out.println("Subscriber 1: " + i));

        System.out.println("\nSubscriber 2 Subscribing...");
        coldFlux.subscribe(i -> System.out.println("Subscriber 2: " + i));
    }
}
