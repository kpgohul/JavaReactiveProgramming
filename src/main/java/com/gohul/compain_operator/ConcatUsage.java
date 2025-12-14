package com.gohul.compain_operator;

import com.gohul.common.CommonUtils;
import reactor.core.publisher.Flux;

// Will subscribe to each publisher
public class ConcatUsage {

    public static void main(String[] args) {
        concat4();
    }

    public static void concat1(){
        Flux.just(1, 2, 3)
                .concatWithValues(4, 5, 6)
                .subscribe(CommonUtils.subscriber());
    }

    public static void concat2(){

        Flux<Integer> flux = Flux.range(1, 1000).take(10);

        Flux.just(1, 2, 3)
                .concatWith(flux)
                .subscribe(CommonUtils.subscriber());
    }

    public static void concat3(){

        Flux<Integer> flux1 = Flux.range(1, 10);
        Flux<Integer> flux2 = Flux.range(11, 10);
        Flux<Integer> flux3 = Flux.range(21, 10);



        Flux.concat(flux1, flux2, flux3)
                .subscribe(CommonUtils.subscriber());
    }

    // Continue to emit the value to the subscriber even when an error occurred, and it shows the error at the end
    public static void concat4(){

        Flux<Integer> flux1 = Flux.range(1, 10);
        Flux<Integer> flux2 = Flux.error(new RuntimeException("Boom!"));
        Flux<Integer> flux3 = Flux.range(21, 10);



        Flux.concatDelayError(flux1, flux2, flux3)
                .subscribe(CommonUtils.subscriber());
    }
}
