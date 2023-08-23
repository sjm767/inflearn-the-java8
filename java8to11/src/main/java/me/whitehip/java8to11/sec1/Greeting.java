package me.whitehip.java8to11.sec1;

public class Greeting {

  private String name;

  public Greeting(){

  }
  public Greeting(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public String hello(String name) {
    return "hello "+name;
  }

  public static String hi(String name){
    return "hi " + name;
  }
}
