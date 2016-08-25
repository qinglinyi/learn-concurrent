package demo;


import java.util.concurrent.Semaphore;

public class SemaphoreDemo implements Runnable {

   static Semaphore semaphore = new Semaphore(5);

    @Override
    public void run() {

        try {
            semaphore.acquire();
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getId() + ": Hello!");
            semaphore.release();
            System.out.println("-----------------"+semaphore.availablePermits());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
//        ExecutorService executor = Executors.newFixedThreadPool(20);
//        final SemaphoreDemo s = new SemaphoreDemo();
        for (int i = 0; i < 20; i++) {
//            executor.execute(s);
            new Thread(new SemaphoreDemo()).start();
        }
    }

}
