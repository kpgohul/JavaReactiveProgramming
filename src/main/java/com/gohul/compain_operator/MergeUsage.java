package com.gohul.compain_operator;

import com.gohul.common.CommonUtils;
import reactor.core.publisher.Flux;

import java.time.Duration;

// Will subscribe to all the publisher parallelly
public class MergeUsage {

    public static void main(String[] args) {

        merge3();

    }

    public static void merge1(){

        var flux1 = Flux.range(1, 10).delayElements(Duration.ofMillis(500));
        var flux2 = Flux.range(11, 10).delayElements(Duration.ofMillis(500));
        var flux3 = Flux.range(21, 10).delayElements(Duration.ofMillis(500));

        Flux.merge(flux1, flux2, flux3)
                .subscribe(CommonUtils.subscriber());

        CommonUtils.timeOut(Duration.ofSeconds(60));
    }

    public static void merge2(){

        var flux1 = Flux.range(1, 10).delayElements(Duration.ofMillis(500));
        var flux2 = Flux.range(11, 10).delayElements(Duration.ofMillis(500));
        var flux3 = Flux.range(21, 10).delayElements(Duration.ofMillis(500));

        flux1
                .mergeWith(flux2)
                .mergeWith(flux3)
                .subscribe(CommonUtils.subscriber());

        CommonUtils.timeOut(Duration.ofSeconds(60));
    }

    // Subscribe to all the publisher but emit the data respective to the publisher order
    public static void merge3(){

        var flux1 = Flux.range(1, 10).delayElements(Duration.ofMillis(500));
        var flux2 = Flux.range(11, 10).delayElements(Duration.ofMillis(500));
        var flux3 = Flux.range(21, 10).delayElements(Duration.ofMillis(500));

        Flux.mergeSequential(flux1, flux2, flux3)
                .subscribe(CommonUtils.subscriber());

        CommonUtils.timeOut(Duration.ofSeconds(60));
    }


}
