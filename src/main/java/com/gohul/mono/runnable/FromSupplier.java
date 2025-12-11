package com.gohul.mono.runnable;

import com.gohul.common.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class FromSupplier {

    private static final Logger log = LoggerFactory.getLogger(FromSupplier.class);

    public static void main(String[] args) {
        fromSupplier1();
    }

    public static void fromSupplier1(){

        var list = List.of(1, 2, 4, 6, 8);
        Mono.fromSupplier(() -> sum(list)).map(i -> i * 10)
                .subscribe(CommonUtils.subscriber("Sub1"));
    }

    private static int sum(List<Integer> list){
        log.info("Sum method executed!...");
        return list.stream().mapToInt(a -> a).sum();
    }
}
