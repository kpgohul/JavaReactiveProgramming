package com.gohul.stream;

import java.util.List;

public class StreamMatch {

    public static void main(String[] args) {

        match1();
        match2();
        match3();


    }

    public static void match1(){
        List<String> names = List.of("Rahul", "Gohul", "Aish");
        boolean is = names.stream().anyMatch(val -> val.startsWith("G"));
        System.out.println("AnyMatch: "+is);
    }

    public static void match2(){
        List<String> names = List.of("Rahul", "Gohul", "Aish");
        boolean is = names.stream().allMatch(val -> val.startsWith("G"));
        System.out.println("AllMatch: "+is);
    }

    public static void match3(){
        List<String> names = List.of("Rahul", "Gohul", "Aish");
        boolean is = names.stream().noneMatch(val -> val.startsWith("G"));
        System.out.println("NoneMatch: "+is);
    }
}
