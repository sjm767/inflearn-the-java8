package me.whitehip.java8to11.sec4;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Supplier;

public class App {

  public static void main(String[] args) {
//    List<OnlineClass> springClassses = new ArrayList<>();
//    springClassses.add(new OnlineClass(1, "spring boot", true));
//    springClassses.add(new OnlineClass(2, "spring data jpa", true));
//    springClassses.add(new OnlineClass(3, "spring mvc", false));
//    springClassses.add(new OnlineClass(4, "spring core", false));
//    springClassses.add(new OnlineClass(5, "rest api development", false));


//    OnlineClass springboot = new OnlineClass(1, "spring boot", true);
//    springboot.getProgress().ifPresent(s -> System.out.println(s.getStudyDuration()));

    //------------------------------------------------

    List<OnlineClass> springClassses = new ArrayList<>();
    springClassses.add(new OnlineClass(1, "spring boot", true));
    springClassses.add(new OnlineClass(5, "rest api development", false));

    Optional<OnlineClass> optional = springClassses.stream()
        .filter(oc -> oc.getTitle().startsWith("spring"))
        .findFirst();

    //Option 값 존재여부 판단하기
    boolean present = optional.isPresent();
    boolean empty = optional.isEmpty();
    System.out.println("present = " + present);

    //Optional에 있는 값 가져오기
    OnlineClass onlineClass = optional.get(); //값이 있을 때는 get할 때 아무 이슈가 없다.

    //Optional이 제공하는 다야한 메서드들로 get을 사용하지 않고 처리할 수 있다.
    //ifPresent
    optional.ifPresent(oc->{
      System.out.println(oc.getTitle());
    });

    //있으면 꺼내오고 없으면 파라미터 안의 인스턴스를 반환받는다. 근데 이 방식은 있든 없든 무조건 새로운 인스턴스를 만든다는 단점이 있다.
    OnlineClass onlineClass1 = optional.orElse(createNewOnlineclass());

    //orElse의 단점을 제거하기 위해 orElseGet 사용.
    OnlineClass onlineClass2 = optional.orElseGet(App::createNewOnlineclass);

    //뭔가 만들어 줄 수 없다면 에러를 던진다.
    optional.orElseThrow(IllegalStateException::new);

    //값을 걸러낼 때는 filter를 사용한다. 값이 있는 경우에 그 중에서 필터링을 한 후 Optional을 리턴한다. 없는 경우 Empty Optional을 리턴한다.
    Optional<OnlineClass> onlineClass3 = optional.filter(oc -> oc.getId() > 10);

    //map으로 타입을 변환하여 Optional로 리턴해줄 수 있다.
    Optional<Integer> classId = optional.map(OnlineClass::getId);

    //map이 복잡해지는 경우가 있는데, Progress의 경우 Optional의 Optional이 된다. 이 경우 flatmap을 사용한다.
    Optional<Progress> progress = optional.flatMap(OnlineClass::getProgress);
  }

  private static OnlineClass createNewOnlineclass() {
    System.out.println("creating new online class");
    return new OnlineClass(10, "New Class", false);
  }

}
