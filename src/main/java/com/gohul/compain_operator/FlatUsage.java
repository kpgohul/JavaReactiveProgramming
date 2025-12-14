package com.gohul.compain_operator;

import com.gohul.common.CommonUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

public class FlatUsage {
    public static void main(String[] args) {
        collectList();
    }

    // All Stream of Flux will be converted to a single Flux and passed to downstream. And it doesn't maintain the order
    public static void flatMap(){

        Flux<Integer> flux = Flux.just(1, 2, 3);

        flux
                .flatMap(i -> Flux.range(i, i + 3)
                        .map(i1 -> "Data-"+i+"-"+i1)
                        .delayElements(Duration.ofMillis(100L * i)))
                .subscribe(CommonUtils.subscriber());

        CommonUtils.timeOut(Duration.ofMinutes(1));

    }

    // Same as flatMap but maintain the order as it subscribing each inner publisher one by one (Slow compare than flatMap)
    public static void concatMap(){

        Flux<Integer> flux = Flux.just(1, 2, 3);

        flux
                .concatMap(i -> Flux.range(i, i + 3)
                        .map(i1 -> "Data-"+i+"-"+i1)
                        .delayElements(Duration.ofMillis(100L * i)))
                .subscribe(CommonUtils.subscriber());

        CommonUtils.timeOut(Duration.ofMinutes(1));

    }

    // Used only with mono -> single element to Stream of elements
    public static void flatMapMany(){
        Mono<String> mono = Mono.just("Gohul,Rahul,Lasiya");

        mono.flatMapMany(i -> Flux.fromArray(i.split(",")))
                .subscribe(CommonUtils.subscriber());
    }

    //Collect the emitted as a list finally
    public static void collectList(){
        Mono<String> mono = Mono.just("Gohul,Rahul,Lasiya");

        Mono<List<String>> listMono = mono.flatMapMany(i -> Flux.fromArray(i.split(",")))
                .collectList();

        listMono.subscribe(CommonUtils.subscriber());
    }

}
