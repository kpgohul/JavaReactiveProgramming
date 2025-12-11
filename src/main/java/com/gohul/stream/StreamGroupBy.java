package com.gohul.stream;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamGroupBy {

    public static void main(String[] args) {
        groupBy4();
    }

    public static void groupBy1(){
        List<String> names = List.of("ram", "raj", "ravi", "sam", "sara");

        Map<Character, List<String>> map = names.stream()
                .collect(
                        Collectors.groupingBy(val -> val.charAt(0))
                );

        System.out.println(map);

    }

    public static void groupBy2(){
        List<Integer> nums = List.of(1,2,3,4,5,6);

        Map<String, List<Integer>> map = nums.stream()
                .collect(
                        Collectors.groupingBy(val -> (val % 2 == 0)? "Even": "Odd")
                );

        System.out.println(map);
    }

    //count the number of occurrence

    public static void groupBy3(){
        List<Integer> nums = List.of(1,2,3,4,5,6,4,5,6);

        Map<Integer, Long> map = nums.stream()
                .collect(
                        Collectors.groupingBy(
                                val -> val,
                                Collectors.counting()
                        )
                );

        System.out.println(map);
    }


    public static void groupBy4(){
        record Person(String name, Integer age) {}

        List<Person> personList = List.of(
                new Person("Gohul", 23),
                new Person("Rahul", 21),
                new Person("Aish", 25),
                new Person("Holly", 25)
        );


        Map<Integer, List<Person>> map = personList.stream()
                .collect(
                        Collectors.groupingBy(per -> per.age)
                );

        System.out.println(map);
    }

    public static void groupBy5(){
        record Person(String name, Integer age) {}

        List<Person> personList = List.of(
                new Person("Gohul", 23),
                new Person("Rahul", 21),
                new Person("Aish", 25),
                new Person("Holly", 25)
        );


        Map<Integer, List<String>> map = personList.stream()
                .collect(
                        Collectors.groupingBy(
                                per -> per.age,
                                Collectors.mapping(per -> per.name, Collectors.toList())
                        )
                );

        System.out.println(map);
    }

}
