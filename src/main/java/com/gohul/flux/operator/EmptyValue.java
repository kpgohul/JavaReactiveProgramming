package com.gohul.flux.operator;

import com.gohul.common.CommonUtils;
import reactor.core.publisher.Flux;

public class EmptyValue {

    public static void main(String[] args) {
        switchIfEmpty();
    }

    public static void defaultIfEmpty(){
        Flux.range(1, 5)
                .filter(i -> i > 6)
                .defaultIfEmpty(-1)
                .subscribe(CommonUtils.subscriber());
    }

    public static void switchIfEmpty(){
        Flux.range(1, 5)
                .filter(i -> i > 6)
                .switchIfEmpty(fallBack())
                .subscribe(CommonUtils.subscriber());
    }

    public static Flux<Integer> fallBack(){
        return Flux.just(1,3,2);
    }
}
