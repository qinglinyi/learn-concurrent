package demo;


import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo implements Runnable {
    static CountDownLatch end = new CountDownLatch(10);
    static CountDownLatch begin = new CountDownLatch(1);

    @Override
    public void run() {

        try {
            begin.await();
            Thread.sleep(new Random().nextInt(10) * 1000);
            System.out.println(Thread.currentThread().getId() + ": 跑完!");
            end.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        CountDownLatchDemo d = new CountDownLatchDemo();
        for (int i = 0; i < 10; i++) {
            executorService.execute(d);
        }
        System.out.println("预备");
        Thread.sleep(1000);
        begin.countDown();//
        System.out.println("跑");
        end.await();
        System.out.println("比赛结束!");
        executorService.shutdown();
    }
}
