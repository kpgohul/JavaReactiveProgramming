package com.gohul.flux;

import com.gohul.common.CommonUtils;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Stream;

public class FluxUsage {

    public static void main(String[] args) {
        just();
    }

    private static void just(){
        Flux.just(1, 2, 3, 4, 5, 6)
                .map(i -> i*10)
                .subscribe(CommonUtils.subscriber("Sub1"));
    }

    private static void fromArray(){
        Integer[] arr = {1, 2, 3, 4, 5, 6, 78};
        Flux.fromArray(arr)
                .map(i -> i*10)
                .subscribe(CommonUtils.subscriber("Sub1"));
    }

    private static void fromIterable(){
        Flux.fromIterable(List.of(1, 2, 3, 4, 5, 6, 7))
                .map(i -> i*10)
                .subscribe(CommonUtils.subscriber("Sub1"));
    }

    private static void fromStream(){
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7);
        Flux.fromStream(stream)
                .map(i -> i*10)
                .subscribe(CommonUtils.subscriber("Sub1"));
    }

    private static void range(){
        Flux.range(1, 100)
                .map(i -> i*10)
                .subscribe(CommonUtils.subscriber("Sub1"));
    }

    private static void empty(){
        Flux.empty()
                .subscribe(CommonUtils.subscriber("Sub1"));
    }

    private static void error(){
        Flux.error(new RuntimeException())
                .subscribe(CommonUtils.subscriber("Sub1"));
    }




}
