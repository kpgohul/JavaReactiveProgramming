package com.gohul.flux.operator;

import com.gohul.common.CommonUtils;
import reactor.core.publisher.Flux;

import java.util.function.Function;

public class Transform {

    private static class EvenWithMulTen implements Function<Flux<Integer>, Flux<Integer>>{
        @Override
        public Flux<Integer> apply(Flux<Integer> integerFlux) {
            return integerFlux.filter(i -> i % 2 == 0)
                    .map( i -> i * 10);
        }
    }

    public static void main(String[] args) {
        transform3();
    }

    public static void transform1(){
        Flux.just(1, 2, 3, 4, 5)
                .transform(new EvenWithMulTen())
                .subscribe(CommonUtils.subscriber());
    }

    public static void transform2(){

        Function<Flux<Integer>, Flux<Integer>> transform = flux -> flux
                                                                                .filter(i -> i % 2 == 0)
                                                                                .map( i -> i * 10);
        Flux.just(1, 2, 3, 4, 5)
                .transform(transform)
                .subscribe(CommonUtils.subscriber());

    }

    public static void transform3(){

        Flux.just(1, 2, 3, 4, 5)
                .transform(flux -> flux
                        .filter(i -> i % 2 == 0)
                        .map( i -> i * 10))
                .subscribe(CommonUtils.subscriber());

    }

}
