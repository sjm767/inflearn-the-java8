package me.whitehip.java8to11.sec7;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

//@Chicken2
@DuplicateChicken("양념")
@DuplicateChicken("마늘간장")
public class App {
  public static void main(String[] args) throws @Chicken2 RuntimeException{
    /*
    애노테이션
     */
//    List<@Chicken2 String> names = Arrays.asList("jaeshim");
//
//    //애노테이션에서 값을 가져오는 2가지 방법
//    DuplicateChicken[] duplicateChickens = App.class.getAnnotationsByType(DuplicateChicken.class);
//    Arrays.stream(duplicateChickens).forEach(c-> System.out.println(c.value()));
//
//    ChickenContainer chickenContainer = App.class.getAnnotation(ChickenContainer.class);
//    Arrays.stream(chickenContainer.value()).forEach(c -> System.out.println(c.value()));
    
    /*
    배열, 정렬
     */

    int size=1500;
    int[] numbers = new int[size];
    Random random = new Random();
    IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());

    long start = System.nanoTime();
    Arrays.sort(numbers);
    System.out.println("serial sorting took " + (System.nanoTime() - start));

    IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());
    start = System.nanoTime();
    Arrays.parallelSort(numbers);
    System.out.println("parallel sorting took " + (System.nanoTime() - start));

  }

  static class FeelsLikeChicken<@Chicken T>{

    public static <@Chicken2 C> void print(@Chicken2 C c){
      System.out.println(c);

    }
  }

}
