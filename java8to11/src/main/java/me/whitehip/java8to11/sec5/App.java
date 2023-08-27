package me.whitehip.java8to11.sec5;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class App {
  public static void main(String[] args) throws InterruptedException {
    //기존에 불편했던 기존 자바의 Date관련 클래스들
    Date date = new Date();
    Calendar calendar = new GregorianCalendar();
    SimpleDateFormat dateFormat = new SimpleDateFormat();

    //날짜에서 시간을 가져온다고..? 심지어 가져온 시간은 timestamp이다.
    Date date1 = new Date();
    long time = date.getTime();
    System.out.println("date1 = " + date1);
    System.out.println("time = " + time);

    Thread.sleep(3000);

    //3초전으로 시간을 바꿀 수 있다. mutable한 객체이다. 멀티스레드 환경에서 안전하게 사용하기 힘들다는 단점이 있다.
    Date after3Seconds = new Date();
    System.out.println("after3Seconds = " + after3Seconds);
    after3Seconds.setTime(time);
    System.out.println("after3Seconds = " + after3Seconds);

    //여기도 버그가 있다. month가 0부터 시작한다. 대신에 상수를 사용해서 표기해야 한다.
    //하지만 여기도 int값을 마음대로 넣을 수 있다 (ex: -1). type safe 하지 않다.
    //특정한 타입을 받을 수 있게 해야 한다. (ex: Month라는 타입을 만들어 받는다) (타입 안정성)
    GregorianCalendar myBirthday = new GregorianCalendar(1989, Calendar.JULY, 26);
    System.out.println(myBirthday.getTime());

    //------------------------------------------------
    /*
    시간은 인간용 시간 (Date)과 기계용 시간 (Instant)로 나눌 수 있다.
    기간을 표현할 때는 Duration(시간), Period(날짜)로 나눌 수 있다.
    DateTimeFormatter를 사용해서 일시를 특정한 포맷으로 표현할 수 있다.
     */

    //기계적인 시간을 나타내는 Instant
    Instant instant = Instant.now(); //기본적으로 UTC(GMT)로 나온다.
    System.out.println("instant = " + instant);
    ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault()); //현재 내 PC의 시간으로 설정해준다.
    System.out.println("zonedDateTime = " + zonedDateTime);

    //인간용 시간을 나타내는 LocalDateTime. 현재 내 시간을 참고해서 로컬시간을 가져온다.
    LocalDateTime localNow = LocalDateTime.now();
    System.out.println("localNow = " + localNow);

    LocalDateTime birthDay = LocalDateTime.of(1989, Month.JULY, 26, 0, 0, 0);
    System.out.println("birthDay = " + birthDay);

    ZonedDateTime zonedDateTime1 = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
    System.out.println("zonedDateTime1 = " + zonedDateTime1);

    //Instant를 이용해서도 ZonedDateTime을 추출할 수 있다.
    Instant nowInstant = Instant.now();
    ZonedDateTime zonedDateTime2 = nowInstant.atZone(ZoneId.of("Asia/Seoul"));

    //기간을 표현하는 방법. Duration/Period
    //날짜를 표현하는 Period
    LocalDate today = LocalDate.now();
    LocalDate thisYearBirthday = LocalDate.of(2024, Month.JULY, 26);

    Period period = Period.between(today, thisYearBirthday);
    System.out.println("period.getDays() = " + period.getDays());

    //Duration은 Instant로 비교를 한다고 생각하면 된다. (기계용)
    Instant instant1 = Instant.now();
    Instant plus = instant1.plus(10, ChronoUnit.SECONDS);

    Duration durationBetween = Duration.between(instant1, plus);
    System.out.println(durationBetween.getSeconds());

    //Formatting. 일시를 주어진 포맷으로 출력한다.
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter MMddyyyy = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    System.out.println(now.format(MMddyyyy));

    //Parsing
    LocalDate parse = LocalDate.parse("07/26/1989", MMddyyyy);
    System.out.println("parse = " + parse);
    
    //레거시 API지원. 구형인 Date를 Instant로 만들 수 있다.
    Date date2 = new Date();
    Instant instant2 = date.toInstant();

    Date newDate = Date.from(instant2);
    System.out.println("newDate = " + newDate);

    //GregorianCalender를 Instant로 바꿀 수 있다. Instant로만 바꾸면 모든 타입으로 다 바꿀 수 있다.
    Calendar calendar1 = new GregorianCalendar();
    LocalDateTime localDateTime = calendar1.toInstant().atZone(ZoneId.systemDefault())
        .toLocalDateTime();

    System.out.println("localDateTime = " + localDateTime);
  }
}
