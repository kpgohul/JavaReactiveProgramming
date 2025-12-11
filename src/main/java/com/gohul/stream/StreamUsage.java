package com.gohul.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamUsage {

    // source → intermediate operations → terminal operation

    public static void main(String[] args) {


    }

    public static void createStream(){
        //From List
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        Stream<Integer> stream = list.stream();

        //From Array
        String[] arr = {"A", "B"};
        Stream<String> s = Arrays.stream(arr);

        //Using Stream Class
        Stream<Integer> stream2 = Stream.of(1, 2, 3, 4, 5);

        Stream<Integer> infinite = Stream.iterate(1, n -> n + 1);



    }

    public static void initiate(){

        List.of("Gohul", "Rahul", "lasiya")
                .stream().filter(val -> val.startsWith("G"))
                .map(val -> val + " it me!")
                .map(val -> val.toUpperCase())
                .forEach(System.out::println);
    }

    public static void flow(){

        List<Integer> nums = List.of(1,2,3,4,5);

        nums.stream()            // source
                .filter(n -> n % 2 == 0) // intermediate
                .map(n -> n * 10)        // intermediate
                .forEach(System.out::println); // terminal

    }


}
