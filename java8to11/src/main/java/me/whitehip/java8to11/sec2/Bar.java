package me.whitehip.java8to11.sec2;

public interface Bar extends Foo{

  /**
   * 상위 인터페이스의 default 메서드를 사용하고 싶지 않으면 추상화를 다시 정의하면 된다.
   */
  void printNameUpperCase();

}
