package com.gohul.stream;

import java.util.List;
import java.util.Optional;

public class StreamFind {

    public static void main(String[] args) {
        find2();
    }

    public static void find1(){
        List<String> list = List.of("a", "b", "c");

        Optional<String> optional = list.stream().findFirst();
        System.out.println(optional.get());

    }

    public static void find2(){
        List<String> list = List.of("a", "b", "c");

        String val = list.stream().findFirst().orElse("Dummy!");
        System.out.println(val);

    }

    public static void find3(){
        List<String> list = List.of("a", "b", "c");

        Optional<String> optional = list.stream().findAny();
        System.out.println(optional.get());

    }
}
