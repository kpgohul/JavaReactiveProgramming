package com.gohul.PubSubModel;

import com.gohul.common.CommonUtils;

public class MainClass {
    public static void main(String[] args) {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);

        var subscription = subscriber.getSubscription();
        subscription.request(CommonUtils.randInt(5,15));
        subscription.request(CommonUtils.randInt(5,15));


    }
}
