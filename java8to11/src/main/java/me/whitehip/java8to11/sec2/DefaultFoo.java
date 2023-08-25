package me.whitehip.java8to11.sec2;

public class DefaultFoo implements Foo, Bar2{

  String name;

  public DefaultFoo(String name) {
    this.name = name;
  }

  /**
   * default 메서드를 재정의하는 것도 가능하다.
   * 여러 인터페이스를 구현하는데, default 메서드명이 같은 경우 반드시 override 해줘야 한다.
   */
  @Override
  public void printNameUpperCase() {
    System.out.println(this.name.toUpperCase());
  }

  @Override
  public void printName() {
    System.out.println("DefaultFoo");
  }

  @Override
  public String getName() {
    return this.name;
  }
}
