package com.gohul.flux.operator;

import com.gohul.common.CommonUtils;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class ElementDelay {
    public static void main(String[] args) {

        Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(2))
                .subscribe(CommonUtils.subscriber());
    }
}
