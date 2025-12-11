package com.gohul.PubSubModel;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PublisherImpl implements Publisher<String> {

    private Logger log = LoggerFactory.getLogger(PublisherImpl.class);

    @Override
    public void subscribe(Subscriber<? super String> subscriber) {

        log.info("Requested for subscribe!");
        subscriber.onSubscribe(new SubscriptionImpl(subscriber));
        log.info("Subscribed successfully!");

    }
}
