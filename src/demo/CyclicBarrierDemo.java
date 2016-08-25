package demo;


import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

    static class WorkerThread implements Runnable {

        CyclicBarrier barrier;

        public WorkerThread(CyclicBarrier b) {
            this.barrier = b;
        }

        @Override
        public void run() {

            try {
                Thread.sleep(new Random().nextInt(10) * 1000);
//                barrier.await(10, TimeUnit.SECONDS);
                barrier.await();
                System.out.println(Thread.currentThread().getId() + ":Work!");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
//            catch (TimeoutException e) {
//                e.printStackTrace();
//                System.out.println(Thread.currentThread().getId() + ":TimeOut!");
//            }
        }
    }

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("========完成一波准备========");
            }
        });
        for (int i = 0; i < 20; i++) {
            new Thread(new WorkerThread(barrier)).start();
        }
    }
}
