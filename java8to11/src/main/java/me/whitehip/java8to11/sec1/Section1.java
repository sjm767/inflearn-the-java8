package me.whitehip.java8to11.sec1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class Section1 {

  public static void main(String[] args) {
    //익명 내부 클래스 anonymous inner class
    RunSomething anonySomething = new RunSomething() {
      @Override
      public void doIt() {
        System.out.println("hello");
      }
    };

    //람다
    RunSomething runSomething = () -> System.out.println("hello");
    runSomething.doIt();

    //순수 함수 (같은 값을 줬을 때 항상 동일한 결과가 리턴되는 것을 보장함)
    PureFunction pureFunction = (number) -> {
      return number+10;
    };
    System.out.println(pureFunction.doIt(10));
    System.out.println(pureFunction.doIt(10));
    System.out.println(pureFunction.doIt(10));

    //순수하지 않은 경우 (순수함수는 오로지 함수에서 전달받은 값만으로 처리를 해야한다)
    PureFunction pureFunction1 = new PureFunction() {
      int baseNumber = 10;
      @Override
      public int doIt(int number) {
        return number+baseNumber;
      }
    };

    //자바에서 제공하는 함수형 인터페이스들

    //Function<T,R>
    Plus10 plus10 = new Plus10();
    System.out.println(plus10.apply(10));

    Function<Integer,Integer> plus100 = (integer) -> integer + 100;
    System.out.println(plus100.apply(10));
    
    //함수 조합
    Function<Integer,Integer> multiply2 = (i) -> i*2;
    Function<Integer, Integer> multiply2AndPlus10 = plus100.compose(multiply2);
    System.out.println(multiply2AndPlus10.apply(2));

    Function<Integer, Integer> plus100Andmultiply2 = plus100.andThen(multiply2);
    System.out.println(plus100Andmultiply2.apply(2));

    //Consumer
    Consumer<Integer> printT = integer -> System.out.println("integer = " + integer);
    printT.accept(10);

    //Supplier
    Supplier<Integer> get10 = () -> 10;
    System.out.println(get10.get());

    //Predicate
    Predicate<String> startsWithJaeshim = (str) -> {
      return str.startsWith("jaeshim");
    };
    System.out.println(startsWithJaeshim.test("jaeshim"));

    //UnaryOperator
    UnaryOperator<Integer> plus10unary = (number) -> number+10;
    System.out.println(plus10unary.apply(10));

    /*
    람다 표현식
     */

    //body가 한 줄이면 {}를 생략할 수 있다.
    Supplier<Integer> get100 = () -> 100;

    //인자가 2개인 경우
    BinaryOperator<Integer> sum = (a,b) -> a+b;

    //변수 캡쳐 (람다를 감싸고 있는 영역에 있는 로컬 변수)
    Section1 section1 = new Section1();
    section1.run();


    /*
    메소드 레퍼런스
     */
    UnaryOperator<String> hi = Greeting::hi; //static 메서드 레퍼런스
    System.out.println(hi.apply("jaeshim"));

    Greeting greeting = new Greeting();
    UnaryOperator<String> hello = greeting::hello; //인스턴스 메서드 레퍼런스
    System.out.println(hello.apply("jaeshim"));

    Supplier<Greeting> newGreeting = Greeting::new; //생성자 레퍼런스
    Greeting greeting1 = newGreeting.get();

    Function<String,Greeting> jaeshimGreeting = Greeting::new; //파라미터가 있는 생성자 레퍼런스
    Greeting jaeshim = jaeshimGreeting.apply("jaeshim");
    System.out.println(jaeshim.getName());
    
    String[] names = {"jaeshim","whiteship","toby"};
    Arrays.sort(names, String::compareToIgnoreCase); //임의 객체의 인스턴스 메서드 레퍼런스

    for (String name : names) {
      System.out.println("name = " + name);
    }
  }

  private void run() {
    //final을 생략할 수 있는 경우가 있는데, 그건 사실상 final인 경우이다. (effective final)
    int baseNumber = 10;

    //로컬 클래스
    class LocalClass{
      void printBaseNumber() {
        int baseNumber = 11;
        System.out.println(baseNumber); //11
      }
    }

    //익명 클래스
    IntConsumer integerConsumer = new IntConsumer() {
      @Override
      public void accept(int value) {
        int baseNumber = 11;
        System.out.println(baseNumber); //11
      }
    };

    //람다
    IntConsumer printInt = (number) -> {
      //int baseNumber=11; //쉐도잉이 발생하지 않는다. 그래서 람다 영역내에서 baseNumber를 다시 선언할 수 없다.
      System.out.println(number+baseNumber);
    };
    printInt.accept(10);

  }

}
