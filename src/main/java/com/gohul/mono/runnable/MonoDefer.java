package com.gohul.mono.runnable;

import com.gohul.common.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class MonoDefer {

    private static final Logger log = LoggerFactory.getLogger(MonoDefer.class);

    public static void main(String[] args) {
        demo2();
    }

    public static void demo1(){
        mono();
//                .subscribe(CommonUtils.subscriber("Sub2"));
    }

    public static void demo2(){
        Mono.defer(MonoDefer::mono)
                .subscribe(CommonUtils.subscriber("Sub2"));
    }

    public static Mono<String> mono(){
        log.info("Entered into the Name Generator!");
        return Mono.fromSupplier(() -> {
            log.info("Generating name...");
            CommonUtils.timeOut(Duration.ofSeconds(3));
            return CommonUtils.fullName();
        });
    }
}
