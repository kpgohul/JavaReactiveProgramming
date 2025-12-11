package com.gohul.stream;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamToMap {

    public static void main(String[] args) {
        map3();
    }

    public static void toMap1(){

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

    public static void map2(){
        List<Integer> nums = List.of(5, 2, 2, 8, 5);

        Map<Integer, Integer> map =
                nums.stream()
                        .collect(Collectors.toMap(
                                k -> k,
                                v -> v,
                                (oldVal, newVal) -> oldVal
                        ));

        System.out.println(map);
    }

    //count the number of occurrence

    public static void map3(){
        List<Integer> nums = List.of(5, 2, 2, 8, 5);

        Map<Integer, Integer> map =
                nums.stream()
                        .collect(Collectors.toMap(
                                k -> k,
                                v -> 1,
                                (oldVal, newVal) -> oldVal + newVal
                        ));

        System.out.println(map);
    }



}
