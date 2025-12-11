package com.gohul.scheduler;

import com.gohul.common.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class SchedulerUsage {

    private static final Logger log = LoggerFactory.getLogger(SchedulerUsage.class);

    public static void main(String[] args) {

//        Mono.empty().publishOn(Schedulers.parallel()).subscribe(); // Decide where the downstream should run the at the point (point to downstream)
//        Mono.empty().subscribeOn(Schedulers.parallel()).subscribe(); // Decide where the entire source should run the at the point (from upstream)


        single();
    }

    //  Run on current source thread, no scheduling and thread switching
    public static void immediate(){
        Mono.just("Hello Guys!")
                .subscribeOn(Schedulers.immediate())
                .doOnNext(val -> log.info("Received: {}",val))
                .subscribe();
    }

    // It will use a dedicated thread for the whole process
    public static void single(){
        Mono.just("Hello Guys!")
                .subscribeOn(Schedulers.single())
                .doOnNext(val -> log.info("Received: {}",val))
                .subscribe();

        CommonUtils.timeOut(Duration.ofSeconds(2));
    }

    //will create number threads = no. of CPU cores,  allow thread scheduling and switching. Used when dealing with CPU bound task!
    public static void parallel(){
        Mono.just("Hello Guys!")
                .subscribeOn(Schedulers.parallel())
                .doOnNext(val -> log.info("Received: {}",val))
                .subscribe();

        CommonUtils.timeOut(Duration.ofSeconds(2));
    }

    // will create 10 * no. of CPU cores, allow thread scheduling and switching. Mainly used in delayed task (The task include delay!)
    public static void boundedElastic(){
        Mono.just("Hello Guys!")
                .subscribeOn(Schedulers.boundedElastic())
                .doOnNext(val -> log.info("Received: {}",val))
                .subscribe();

        CommonUtils.timeOut(Duration.ofSeconds(2));
    }

    // Custom scheduler

    public static void customSchedulers(){

        Scheduler scheduler1 = Schedulers.newParallel("Parallel");
        Scheduler scheduler2 = Schedulers.newSingle("Single"); //etc...
        Scheduler scheduler3 = Schedulers.newBoundedElastic(
                20,        // max threads
                200,       // max queued tasks
                "custom-elastic"
        );


        Mono.just("Hello Guys!")
                .subscribeOn(scheduler1)
                .doOnNext(val -> log.info("Received: {}",val))
                .subscribe();

        CommonUtils.timeOut(Duration.ofSeconds(2));
    }


}
