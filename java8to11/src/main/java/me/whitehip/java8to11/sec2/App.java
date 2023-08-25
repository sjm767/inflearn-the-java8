package me.whitehip.java8to11.sec2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;

public class App {

  public static void main(String[] args) {
//    Foo defaultFoo = new DefaultFoo("jaeshim");
//    defaultFoo.printNameUpperCase();
//
//    Foo.printAnything();

    //-----------------

    List<String> name = new ArrayList<>();
    name.add("jaeshim");
    name.add("whiteship");
    name.add("toby");
    name.add("foo");

    //Iterator.forEach
//    name.forEach(System.out::println);

    //Iterator.Spliterator
    Spliterator<String> spliterator = name.spliterator();
    Spliterator<String> spliterator1 = spliterator.trySplit();
    while (spliterator.tryAdvance(System.out::println));
    System.out.println("=======");
    while(spliterator1.tryAdvance(System.out::println));

    //Collection.stream
    long j = name.stream()
        .map(n -> n.toUpperCase())
        .filter(n -> n.startsWith("J"))
        .count();
    System.out.println("j = " + j);

    //Collection.removeIf
    name.removeIf(x -> x.startsWith("j"));

    //Comparater
    Comparator<String> compareToIgnoreCase = String::compareToIgnoreCase;

    name.sort(compareToIgnoreCase);
    name.sort(compareToIgnoreCase.reversed());
    name.forEach(System.out::println);

  }

}
