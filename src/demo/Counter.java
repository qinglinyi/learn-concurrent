package demo;

import java.util.ArrayList;
import java.util.List;

public class Counter {

//    static class MyObject {
//        static int mycount = 0;
//    }
//
//    public volatile static int count = 0;
//
//    public static void inc() {
//
//        //这里延迟1毫秒，使得结果明显
//        try {
//            Thread.sleep(1);
//        } catch (InterruptedException e) {
//        }
//        MyObject.mycount++;
//        count++;
//    }
//
//    public static void main(String[] args) {
//
//        //同时启动1000个线程，去进行i++计算，看看实际结果
//
//        for (int i = 0; i < 1000; i++) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    Counter.inc();
//                }
//            }).start();
//        }
//
//        while(Thread.activeCount()>1)  //保证前面的线程都执行完
//            Thread.yield();
//
//        //这里每次运行的值都有可能不同,可能为1000
//        System.out.println("运行结果:Counter.count=" + Counter.count);
//        System.out.println("运行结果:MyObject.mycount=" + MyObject.mycount);
//    }



    public  int inc = 0;

    public synchronized void increase() {
        inc++;
    }

    public static void main(String[] args) throws InterruptedException {
        final Counter test = new Counter();
        List<Thread> list=new ArrayList<>();
        for(int i=0;i<10;i++){
            Thread thread = new Thread() {
                public void run() {
                    for (int j = 0; j < 1000; j++)
                        test.increase();
                }
            };
            thread.start();
            list.add(thread);

        }

        for (Thread thread : list) {
            thread.join();
        }

        System.out.println(test.inc);
    }
}
