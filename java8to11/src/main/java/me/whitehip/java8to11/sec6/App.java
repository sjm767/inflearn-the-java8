package me.whitehip.java8to11.sec6;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class App {

  public static void main(String[] args) throws InterruptedException, ExecutionException {

//    //스레드를 만드는 2가지 방법
//    //1. Thread를 상속받아 구현하기.
//    MyThread myThread = new MyThread();
//    myThread.start();
//
//    //2. Runnable을 구현하기
//    Thread runnableThread = new Thread(() -> System.out.println("Runnable Thread"));
//    runnableThread.start();

//    /*
//    스레드의 주요기능
//    1. sleep
//    2. interrupt
//    3. join
//     */
//
//    //sleep
//    Thread runnableThread2 = new Thread(() -> {
//      try {
//        Thread.sleep(1000L);
//      } catch (InterruptedException e) {
//        System.out.println("Interrupted");
//      }
//      System.out.println("Runnable Thread2");
//    });
//    runnableThread2.start();
//
//    //interrupt를 호출하면 InterrupedException으로 간다.
//    Thread sleepThread = new Thread(new Runnable() {
//      @Override
//      public void run() {
//        try {
//          while (true) {
//            Thread.sleep(1000L);
//            System.out.println("sleep Thread");
//          }
//        } catch (InterruptedException e) { //누군가 깨우면 Interrupted Exception이 걸린다.
//          System.out.println("exit");
//          return;
//        }
//      }
//    });
//    sleepThread.start();
//
//    System.out.println("Main Thread!");
//    Thread.sleep(3000L);
//    sleepThread.interrupt();
//
//    //join
//    Thread joinThread = new Thread(new Runnable() {
//      @Override
//      public void run() {
//        try {
//          Thread.sleep(3000L); //3초 쉬고 종료
//          System.out.println("joinThread finished");
//        } catch (InterruptedException e) {
//
//        }
//      }
//    });
//    joinThread.start();
//    joinThread.join(); //메인스레드가 joinThread를 기다리도록 한다.
//    System.out.println("Main Thread waiting joinThread");

    //------------------------------------------------
//    ExecutorService executorService = Executors.newSingleThreadExecutor(); //SingleThread
//    executorService.submit(() -> System.out.println("Thread " + Thread.currentThread().getName()));
//
//    executorService.shutdown();

    //스레드는 2개지만 작업을 5개 보내면 2개의 스레드가 작업을 나눠서 실행하게 된다.
//    ExecutorService executorService = Executors.newFixedThreadPool(2);
//    executorService.submit(getRunnable("Hello"));
//    executorService.submit(getRunnable("Jaeshim"));
//    executorService.submit(getRunnable("The"));
//    executorService.submit(getRunnable("Java"));
//    executorService.submit(getRunnable("Thread"));
//    executorService.shutdown();
//
//    ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor(); //Scheduled
//    scheduledExecutorService.schedule(getRunnable("Hello"), 3, TimeUnit.SECONDS);
//    scheduledExecutorService.shutdown();

    /*
    리턴 결과를 받고 싶다면 Callable을 사용해야 한다.
    그리고 이 결과를 받는것이 Future이다.
     */
//    ExecutorService executorService = Executors.newSingleThreadExecutor();
//
//    Callable<String> hello = () -> {
//      Thread.sleep(2000L);
//      return "Hello";
//    };
//
//    Future<String> future = executorService.submit(hello);
////    future.cancel(false); //진행중인 작업을 취소. true: 인터럽트 하면서 종료. false: 기다렸다가 취소. get을 할 수 없다.
//    System.out.println("Started!");
//    System.out.println(future.isDone());
//    future.get(); //get을 만나는 순간 결과가 올 때까지 대기한다. (Blocking)
//    System.out.println(future.isDone());
//    System.out.println("End");
//
//    executorService.shutdown();

//    //Callable을 여러개 줄 수 있다.
//    ExecutorService executorService = Executors.newSingleThreadExecutor();
//
//    Callable<String> hello = () -> {
//      Thread.sleep(2000L);
//      return "Hello";
//    };
//    Callable<String> java = () -> {
//      Thread.sleep(3000L);
//      return "java";
//    };
//    Callable<String> jaeshim = () -> {
//      Thread.sleep(1000L);
//      return "jaeshim";
//    };
//    List<Future<String>> futures = executorService.invokeAll(Arrays.asList(hello, java, jaeshim)); //invokeAll은 모두 다 기다린다.
//    for (Future<String> future :futures
//    ) {
//      System.out.println(future.get());
//    }
//    executorService.shutdown();
//
//    ExecutorService executorService2 = Executors.newFixedThreadPool(4);
//    String s = executorService2.invokeAny(Arrays.asList(hello, java, jaeshim)); //invokeAny는 먼저 온 것을 출력한다. 이 때 스레드수가 충분해야 한다.
//    System.out.println("s = " + s);
//
//    executorService2.shutdown();

    /*
    CompletableFuture
    명시적으로 ExecutorService를 만들 필요가 없다.
     */
//    CompletableFuture<String> future = new CompletableFuture<>();
//    future.complete("jaeshim"); //명시적으로 값을 준 것

//    CompletableFuture<Void> future = CompletableFuture.runAsync(() ->
//        System.out.println("Hello " + Thread.currentThread().getName())); //리턴 타입이 없는 경우 runAsync를 하면 된다.
//
//    CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "supply hello"); //리턴 타입이 있는 경우 supplyAsync를 사용하면 된다.
//
//    System.out.println(future.get());
//    System.out.println(future1.get());

    //콜백 제공하기
//    CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "supply hello")
//        .thenApply(s -> { //결과를 받아서 처리 후 리턴
//          System.out.println(Thread.currentThread().getName());
//          return s.toUpperCase();
//        });
//    CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> "supply hello")
//        .thenAccept((s) -> System.out.println(Thread.currentThread().getName())); //결과를 받아서 리턴 없이 처리

//    CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> "supply hello")
//        .thenRun(() -> System.out.println(Thread.currentThread().getName())); //값을 받을 필요도 없고, 그냥 수행만 하고 싶다.
//
//    System.out.println(future.get());

    /*
    CompletableFuture의 조합, 예외처리
     */

//    CompletableFuture<String> helloFuture = CompletableFuture.supplyAsync(() -> {
//      System.out.println("hello" + Thread.currentThread().getName());
//      return "Hello";
//    });

//    CompletableFuture<String> future = helloFuture.thenCompose(App::getWorld); //둘의 연관관계가 있는 경우
//    System.out.println(future.get());

    CompletableFuture<String> helloFuture = CompletableFuture.supplyAsync(() -> {
      System.out.println("Hello" + Thread.currentThread().getName());
      return "Hello";
    });
    CompletableFuture<String> worldFuture = CompletableFuture.supplyAsync(() -> {
      System.out.println("World" + Thread.currentThread().getName());
      return "World";
    });
//
//    CompletableFuture<String> stringCompletableFuture = helloFuture.thenCombine(worldFuture,
//        (s, s2) -> s + " " + s2);//둘의 연관관계가 없는 경우. 따로따로 실행하고 둘의 결과가 다 도착했을 때 합친다.
//    System.out.println(stringCompletableFuture.get());
//
//    //두개 이상의 task가 있을 때 allOf를 사용하면 모든 task가 끝났을 때 콜백을 수행할 수 있다.
//    //하지만 모든 task의 타입이 동일하다는 보장도 없고, task중 일부에서 예외가 올 수 있다. 그래서 결과가 null이다.
//    CompletableFuture.allOf(helloFuture, worldFuture)
//        .thenAccept(System.out::println);

    //그래서 이걸 제대로 받으려면 Collection으로 받아야 한다.
//    List<CompletableFuture<String>> futures = Arrays.asList(helloFuture, worldFuture);
//    CompletableFuture[] futuresArray = futures.toArray(new CompletableFuture[futures.size()]);
//
//    CompletableFuture<List<String>> results = CompletableFuture.allOf(futuresArray)
//        .thenApply(v -> futures.stream()
//            .map(CompletableFuture::join)
//            .collect(Collectors.toList())
//        );
//
//    results.get().forEach(System.out::println);

    //둘중에 먼저 실행되는 것 처리
//    CompletableFuture<Void> future = CompletableFuture.anyOf(helloFuture, worldFuture)
//        .thenAccept((s) -> {
//          System.out.println(s);
//        });
//
//    future.get();

    //예외처리
    boolean throwError = true;

//    CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
//      if (throwError) {
//        throw new IllegalArgumentException();
//      }
//      return "Hello";
//    }).exceptionally(ex -> {
//      return "Error!";
//    });

    //handle은 정상과 예외 둘다 처리
    CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
      if (throwError) {
        throw new IllegalArgumentException();
      }
      return "Hello";
    }).handle((result, ex) -> {
      if (ex != null) {
        System.out.println(ex);
        return "ERROR";
      }
      return result;
    });

    System.out.println(hello.get());
  }

  private static CompletableFuture<String> getWorld(String message) {
    CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
      System.out.println(message + Thread.currentThread().getName());
      return message + " World";
    });

    return future;
  }

  private static Runnable getRunnable(String message) {
    return () -> System.out.println(message + Thread.currentThread().getName());
  }

  static class MyThread extends Thread {

    @Override
    public void run() {
      System.out.println("MyThread: " + Thread.currentThread().getName());
    }
  }
}
