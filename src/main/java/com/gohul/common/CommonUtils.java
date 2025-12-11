package com.gohul.common;

import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Random;

public class CommonUtils {

    private final static Logger log = LoggerFactory.getLogger(CommonUtils.class);

    public static void timeOut(Duration duration){
        try{
            Thread.sleep(duration);
        }catch (Exception e){
            log.error("Interrupted");
        }

    }

    public static <T> DefaultSubscriber<T> subscriber(String name){
        return new DefaultSubscriber<T>(name);
    }

    public static <T> DefaultSubscriber<T> subscriber(){
        return new DefaultSubscriber<T>();
    }

    public static Faker faker(){
        return new Faker();
    }

    public static String email(){
        return faker().internet().emailAddress();
    }

    public static String ipAddress(){
        return faker().internet().ipV4Address();
    }

    public static String country(){
        return faker().country().name();
    }

    public static String capital(){
        return faker().country().capital();
    }

    public static String product(){
        return faker().commerce().productName();
    }

    public static String username(){
        return faker().name().username();
    }

    public static String fullName(){
        return faker().name().firstName() +" "+ faker().name().lastName();
    }

    public static int randInt(int start, int end){
        return new Random().nextInt(start, end);
    }

    public static double randDouble(int start, int end){
        return new Random().nextDouble(start, end);
    }



}
