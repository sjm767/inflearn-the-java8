package me.whitehip.java8to11.sec3;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

  public static void main(String[] args) {
//    List<String> names = new ArrayList<>();
//    names.add("jaeshim");
//    names.add("whiteship");
//    names.add("toby");
//    names.add("foo");
//
//    //스트림 처리를 해도 SOURCE 데이터는 바뀌지 않는다.
//    names.stream()
//        .map(s->s.toUpperCase());
//
//    names.forEach(System.out::println);
//
//    //중개 operation은 기본적으로 lazy하다. 터미널 operation이 와야 중개 operation이 구동된다.
//    //아래는 중개형 operation이라 실행이 안된다.
//    names.stream().map(s->{
//      System.out.println(s);
//      return s.toUpperCase();
//    });
//    System.out.println("=============");
//
//    //손쉽게 병렬처리 할 수도 있다. 데이터가 정말 방대하게 많을 경우 parallelStream이 권장되며, 평상시에는 일반 stream으로 처리하는 것을 더 권장한다.
//    List<String> collect = names.parallelStream()
//        .map(s -> {
//          System.out.println(s+" "+Thread.currentThread().getName());
//          return s.toUpperCase();
//        })
//        .collect(Collectors.toList());
//
//    collect.forEach(s -> System.out.println("s = " + s));

//    ====================================

    List<OnlineClass> springClassses = new ArrayList<>();
    springClassses.add(new OnlineClass(1, "spring boot", true));
    springClassses.add(new OnlineClass(2, "spring data jpa", true));
    springClassses.add(new OnlineClass(3, "spring mvc", false));
    springClassses.add(new OnlineClass(4, "spring core", false));
    springClassses.add(new OnlineClass(5, "rest api development", false));

    List<OnlineClass> javaClasses = new ArrayList<>();
    javaClasses.add(new OnlineClass(6, "The Java, Test", true));
    javaClasses.add(new OnlineClass(7, "The Java, Code manipulation", true));
    javaClasses.add(new OnlineClass(8, "The Java, 8 to 11", false));

    List<List<OnlineClass>> jaeshimEvents = new ArrayList<>();
    jaeshimEvents.add(springClassses);
    jaeshimEvents.add(javaClasses);

    System.out.println("spring 으로 시작하는 수업");
    springClassses.stream()
        .filter(oc -> oc.getTitle().startsWith("spring"))
        .forEach(oc -> System.out.println(oc.getTitle()));

    System.out.println("close 되지 않은 수업");
    springClassses.stream()
        .filter(Predicate.not(OnlineClass::isClosed))
        .forEach(oc -> System.out.println(oc.getTitle()));

    System.out.println("수업 이름만 모아서 스트림 만들기");
    springClassses.stream()
        .map(OnlineClass::getTitle)
        .forEach(System.out::println);

    System.out.println("두 수업 목록에 들어있는 모든 수업 아이디 출력");
    jaeshimEvents.stream().flatMap(Collection::stream)
        .forEach(oc -> System.out.println(oc.getId()));

    System.out.println("10부터 1씩 증가하는 무제한 스트림 중에서 앞에 10개 빼고 최대 10개 까지만");
    Stream.iterate(10, i -> i + 1)
        .skip(10)
        .limit(10)
        .forEach(System.out::println);

    System.out.println("자바 수업 중에 Test가 들어있는 수업이 있는지 확인");
    boolean test = javaClasses.stream()
        .anyMatch(oc -> oc.getTitle().contains("Test"));
    System.out.println("test = " + test);

    System.out.println("스프링 수업 중에 제목에 spring이 들어간 제목만 모아서 List로 만들기");
    List<String> springTitles = springClassses.stream()
        .filter(oc -> oc.getTitle().contains("spring"))
        .map(OnlineClass::getTitle)
        .collect(Collectors.toList());

    springTitles.forEach(System.out::println);

  }

}
