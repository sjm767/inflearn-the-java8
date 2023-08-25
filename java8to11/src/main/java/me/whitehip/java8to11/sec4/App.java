package me.whitehip.java8to11.sec4;

import java.util.ArrayList;
import java.util.List;

public class App {

  public static void main(String[] args) {
    List<OnlineClass> springClassses = new ArrayList<>();
    springClassses.add(new OnlineClass(1, "spring boot", true));
    springClassses.add(new OnlineClass(2, "spring data jpa", true));
    springClassses.add(new OnlineClass(3, "spring mvc", false));
    springClassses.add(new OnlineClass(4, "spring core", false));
    springClassses.add(new OnlineClass(5, "rest api development", false));


    OnlineClass springboot = new OnlineClass(1, "spring boot", true);
    springboot.getProgress().ifPresent(s -> System.out.println(s.getStudyDuration()));
  }

}
