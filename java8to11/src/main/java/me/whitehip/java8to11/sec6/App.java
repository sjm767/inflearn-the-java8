package me.whitehip.java8to11.sec6;

public class App {

  public static void main(String[] args) throws InterruptedException {

//    //스레드를 만드는 2가지 방법
//    //1. Thread를 상속받아 구현하기.
//    MyThread myThread = new MyThread();
//    myThread.start();
//
//    //2. Runnable을 구현하기
//    Thread runnableThread = new Thread(() -> System.out.println("Runnable Thread"));
//    runnableThread.start();



    /*
    스레드의 주요기능
    1. sleep
    2. interrupt
    3. join
     */

    //sleep
    Thread runnableThread2 = new Thread(() -> {
      try {
        Thread.sleep(1000L);
      } catch (InterruptedException e) {
        System.out.println("Interrupted");
      }
      System.out.println("Runnable Thread2");
    });
    runnableThread2.start();

    //interrupt를 호출하면 InterrupedException으로 간다.
    Thread sleepThread = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          while (true) {
            Thread.sleep(1000L);
            System.out.println("sleep Thread");
          }
        } catch (InterruptedException e) { //누군가 깨우면 Interrupted Exception이 걸린다.
          System.out.println("exit");
          return;
        }
      }
    });
    sleepThread.start();

    System.out.println("Main Thread!");
    Thread.sleep(3000L);
    sleepThread.interrupt();

    //join
    Thread joinThread = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          Thread.sleep(3000L); //3초 쉬고 종료
          System.out.println("joinThread finished");
        } catch (InterruptedException e) {

        }
      }
    });
    joinThread.start();
    joinThread.join(); //메인스레드가 joinThread를 기다리도록 한다.
    System.out.println("Main Thread waiting joinThread");

  }

  static class MyThread extends Thread {

    @Override
    public void run() {
      System.out.println("MyThread: " + Thread.currentThread().getName());
    }
  }
}
