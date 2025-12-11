package com.gohul.stream;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamCollect {

    public static void main(String[] args) {
        toMap();
    }

    public static void toList(){
        List<Integer> nums = List.of(1, 2, 3, 4);

        List<Integer> list = nums.stream()
                .collect(Collectors.toList());
//        .toList();
    }

    public static void toSet(){
        List<Integer> nums = List.of(1, 2, 3, 4);

        Set<Integer> list = nums.stream()
                .collect(Collectors.toSet());
    }

    public static void toUnmodifiableList(){
        List<Integer> nums = List.of(1, 2, 3, 4);

        List<Integer> collect = nums.stream()
                .collect(Collectors.toUnmodifiableList());

//        collect.remove(0); will throw exception!

    }

    public static void joining(){
        List<Integer> nums = List.of(1, 2, 3, 4);

        String result = nums.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ", "[", "]"));

        System.out.println(result);

    }

    public static void toMap(){

        List<String> names = List.of("java", "spring", "ai");

        Map<String, Integer> map = names.stream()
                .collect(
                        Collectors.toMap(
                                val -> val,
                                val -> val.length()
                        )
                );

        System.out.println(map);
    }
}
