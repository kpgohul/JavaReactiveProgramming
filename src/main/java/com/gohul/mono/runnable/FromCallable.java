package com.gohul.mono.runnable;

import com.gohul.common.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

public class FromCallable {

    private static final Logger log = LoggerFactory.getLogger(FromCallable.class);

    public static void main(String[] args) {
        fromCallable1();
    }

    public static void fromCallable1(){

        var list = List.of(1, 2, 4, 6, 8);
        Mono.fromCallable(() -> sum(list)).map(i -> i * 10)
                .subscribe(CommonUtils.subscriber("Sub1"));
    }

    private static int sum(List<Integer> list){
        log.info("Sum method executed!...");
        return list.stream().mapToInt(a -> a).sum();
    }




}
