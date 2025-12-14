package com.gohul.back_pressure;

import com.gohul.common.CommonUtils;
import reactor.core.publisher.Flux;

public class BackPressureHandle {

    public static void main(String[] args) {

    }

    // Use Unbounded queue/ buffer for storing the item - may cause out of memory error
    public static void onBackpressureBuffer(){

        Flux.range(1, 1000)
                .onBackpressureBuffer()
                .subscribe(CommonUtils.subscriber());
    }

    //Only hold the latest item by dropping the old one
    public static void onBackpressureLatest(){

        Flux.range(1, 1000)
                .onBackpressureLatest()
                .subscribe(CommonUtils.subscriber());
    }

    //Will throw the error immediately when the subscriber is slow
    public static void onBackpressureError(){

        Flux.range(1, 1000)
                .onBackpressureError()
                .subscribe(CommonUtils.subscriber());
    }

    // Will have a fixed buffer size and throw error when it exceeds the size
    public static void onBackpressureBufferWithFixedSize(){

        Flux.range(1, 1000)
                .onBackpressureBuffer(12)
                .subscribe(CommonUtils.subscriber());
    }
}
