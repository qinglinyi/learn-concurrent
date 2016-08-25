package demo;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TimeLock implements Runnable {

    private static ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {

        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                Thread.sleep(7000);
            } else {
                System.out.println("Lock get fail");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new TimeLock());
        Thread t2 = new Thread(new TimeLock());
        t1.start();
        t2.start();
    }
}
