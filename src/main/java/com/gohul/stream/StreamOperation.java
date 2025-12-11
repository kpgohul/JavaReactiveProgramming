package com.gohul.stream;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamOperation {

    public static void main(String[] args) {
        limitAndSkip();
    }

    public static void filter(){
        List<Integer> nums = List.of(1,2,3,4,5,6);

        nums.stream()
                .filter(n -> n % 2 == 0)
                .forEach(System.out::println);
    }

    public static void map(){
        List<String> names = List.of("gohul", "rahul", "arun");

        names.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }

    public static void sorted(){
        List<Integer> nums = List.of(5, 2, 8, 1);

        nums.stream()
                .sorted((a,b) -> b - a)   // reverse order
                .forEach(System.out::println);

    }

    public static void groupBy(){
        List<String> names = List.of("ram", "raj", "ravi", "sam", "sara");

        Map<Character, List<String>> map = names.stream()
                .collect(
                        Collectors.groupingBy(val -> val.charAt(0))
                );

        System.out.println(map);

    }

    public static void flatMap(){

        //using map()

//        List<List<String>> names = List.of(
//                List.of("Aish", "Gohul"),
//                List.of("Rahul", "Holly")
//        );
//
//        names.stream().map(val -> val.stream()).forEach(val -> System.out.println(val));

        //Output
//        java.util.stream.ReferencePipeline$Head@2e817b38
//        java.util.stream.ReferencePipeline$Head@c4437c4

        List<List<String>> names = List.of(
                List.of("Aish", "Gohul"),
                List.of("Rahul", "Holly")
        );

        names.stream().flatMap(val -> val.stream()).forEach(val -> System.out.println(val));
    }

    public static void count(){
        List<String> names = List.of("Rahul", "Aish", "Gohul");

         long count = names.stream().count();
        System.out.println(count);

    }

    public static void limitAndSkip(){
        List<Integer> nums = List.of(10, 20, 30, 40, 50);

        nums.stream().limit(3).forEach(System.out::println);
        System.out.println("---");
        nums.stream().skip(2).forEach(System.out::println);

    }








}
