package com.gohul.mono.runnable;

import com.gohul.common.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class FromRunnable {
    private static final Logger log = LoggerFactory.getLogger(FromRunnable.class);

    public static void main(String[] args) {
        fromRunnable1();
    }

    public static void fromRunnable1(){

        var list = List.of(1, 2, 4, 6, 8);
        Mono<Object> objectMono = Mono.fromRunnable(() -> sum(list));
                objectMono.subscribe(CommonUtils.subscriber("Sub1"));
    }

    private static int sum(List<Integer> list){
        log.info("Sum method executed!...");
        return list.stream().mapToInt(a -> a).sum();
    }
}
