package me.whitehip.java8to11.sec7;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE_USE) //TYPE_PARAMETER를 포괄해서 쓸 수 있다.
public @interface Chicken2 {

}
