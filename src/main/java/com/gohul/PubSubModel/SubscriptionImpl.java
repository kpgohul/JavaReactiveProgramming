package com.gohul.PubSubModel;

import com.gohul.common.CommonUtils;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubscriptionImpl implements Subscription {

    private final static Logger log = LoggerFactory.getLogger(SubscriptionImpl.class);
    private final Subscriber<? super String> subscriber;
    private boolean isCancelled;
    private long maxVal;
    private long count;

    public SubscriptionImpl(Subscriber<? super String> subscriber){
        this.subscriber = subscriber;
        isCancelled = false;
        maxVal = CommonUtils.randInt(5,15);
        log.info("MaxValue is assigned as: {}",maxVal);
        count = 0;
    }


    @Override
    public void request(long l) {

        log.info("Requested Number of Element: {}",l);

        if(isCancelled) {
            subscriber.onError(new RuntimeException("Subscription is cancelled"));
            return;
        }
        if(count >= maxVal) {
            subscriber.onError(new RuntimeException("No Element Left!"));
            return;
        }

        for(int i=1; i<=l; i++){
            if(count >= maxVal) break;;
            subscriber.onNext(CommonUtils.product());
            count++;
        }
        if(count >= maxVal){
            isCancelled = true;
            log.info("No Element Left!");
            subscriber.onComplete();
        }

    }

    @Override
    public void cancel() {
        isCancelled = true;
        log.info("Subscription Cancelled!");
    }

}
