package com.gohul.re_subscribtion;

import com.gohul.common.CommonUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class RepeatUsage {

    public static void main(String[] args) {
        repeatWhen();
    }

    public static void repeat1(){
        Mono.fromSupplier(() -> CommonUtils.capital())
                .repeat()
                .takeUntil(val -> val.equalsIgnoreCase("new delhi"))
                .subscribe(CommonUtils.subscriber());
    }

    // It will repeat --> 1 + repeat count, For this case, 1(Initial) + 5(Repeat Count) = 6 times
    public static void repeat2(){
        Mono.fromSupplier(() -> CommonUtils.ipAddress())
                .repeat(5)
                .subscribe(CommonUtils.subscriber());
    }

    public static void repeatWhen(){
        Mono.fromSupplier(() -> CommonUtils.capital())
                .repeatWhen(flux -> flux.delayElements(Duration.ofSeconds(1)))
                .take(6)
                .subscribe(CommonUtils.subscriber());

        CommonUtils.timeOut(Duration.ofSeconds(30));
    }
}
