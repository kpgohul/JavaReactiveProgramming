package com.gohul.stream;

import java.util.List;
import java.util.Optional;

public class StreamReduce {

    public static void main(String[] args) {
            max();
    }

    public static void reduceWithDefault(){
        List<String> names = List.of("Rahul", "Aish", "Gohul");

        String val = names.stream()
                .reduce("", (a, b) -> a + ", "+ b);

        System.out.println(val);
    }

    public static void reduceWithOutDefault(){
        List<String> names = List.of("Rahul", "Aish", "Gohul");

        Optional<String> val = names.stream()
                .reduce((a, b) -> a + ", "+ b);

        System.out.println(val.get());
    }

    public static void reduce1(){
        List<Integer> nums = List.of(1, 2, 3, 4);
        int sum = nums.stream()
                .reduce(0, (a,b) -> a + b);

        System.out.println(sum);
    }

    public static void reduce2(){
        List<Integer> nums = List.of(1, 2, 3, 4);
        int max = nums.stream()
                .reduce(0, (a,b) -> (a > b)? a: b);

        System.out.println(max);
    }

    public static void max(){
        List<Integer> nums = List.of(1, 2, 3, 4);
        Integer max = nums.stream()
                .max((a,b) -> a - b)
                .get();

        System.out.println(max);
    }

    public static void min(){
        List<Integer> nums = List.of(1, 2, 3, 4);
        Integer max = nums.stream()
                .max((a,b) -> a - b)
                .get();

        System.out.println(max);
    }





}
