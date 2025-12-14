package com.gohul.compain_operator;

import com.gohul.common.CommonUtils;
import reactor.core.publisher.Flux;

import java.util.List;

public class StartWithUsage {

    public static void main(String[] args) {
        startWith3();
    }

    public static void startWith1(){
        Flux<String> flux = Flux.range(1, 10).map(i -> CommonUtils.username());

        flux.startWith("Hello", "Hi")
                .subscribe(CommonUtils.subscriber());
    }

    public static void startWith2(){
        Flux<String> flux = Flux.range(1, 10).map(i -> CommonUtils.username());

        Flux.just("Hello", "Hi")
                .startWith(flux)
                .subscribe(CommonUtils.subscriber());
    }

    public static void startWith3(){
        Flux<String> flux = Flux.range(1, 10).map(i -> CommonUtils.username());

        Flux.just("Hello", "Hi")
                .startWith(List.of("New Hell0", "New Hi"))
                .subscribe(CommonUtils.subscriber());
    }


}
