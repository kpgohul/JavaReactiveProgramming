package com.gohul.flux.operator;

import com.gohul.common.CommonUtils;
import reactor.core.publisher.Flux;

public class FluxHandle {

    public static void main(String[] args) {
        handle1();
    }

    // handle = filter + map
    public static void handle1(){

        Flux.range(1, 10)
                .handle((i, sink) -> {
                    if(i % 2 == 0) sink.next(i);
                })
                .subscribe(CommonUtils.subscriber());
    }



}
