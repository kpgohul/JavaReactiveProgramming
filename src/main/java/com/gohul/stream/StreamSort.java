package com.gohul.stream;

import java.util.Comparator;
import java.util.List;

public class StreamSort {

    public static void sorted1(){
        List<Integer> nums = List.of(5, 2, 8, 1);

        nums.stream()
                .sorted((a,b) -> b - a)   // reverse order
                .forEach(System.out::println);

    }

    public static void sorted2() {
        record Person(String name, Integer age) {}

        List<Person> personList = List.of(
                new Person("Gohul", 23),
                new Person("Rahul", 21),
                new Person("Aish", 25)
        );

        personList.stream()
                .sorted((a, b) -> b.age().compareTo(a.age())) // sort by age descending
                .forEach(per -> System.out.println("Person name: %s, age: %d".formatted(per.name(), per.age())));
    }

    public static void sorted3(){
        record Person(String name, Integer age) {}

        List<Person> personList = List.of(
                new Person("Gohul", 23),
                new Person("Rahul", 21),
                new Person("Aish", 25)
        );

        personList.stream()
                .sorted(Comparator.comparing(val -> val.age)) // sort by age descending
                .forEach(per -> System.out.println("Person name: %s, age: %d".formatted(per.name(), per.age())));
    }

    public static void sorted4(){
        record Person(String name, Integer age) {}

        List<Person> personList = List.of(
                new Person("Gohul", 23),
                new Person("Rahul", 21),
                new Person("Aish", 25)
        );

        personList.stream()
                .sorted(
                        Comparator.comparing((Person p) -> p.age)
                                .thenComparing( (Person val) -> val.name)
                                .reversed()
                ) // sort by age descending
                .forEach(per -> System.out.println("Person name: %s, age: %d".formatted(per.name(), per.age())));
    }
}
