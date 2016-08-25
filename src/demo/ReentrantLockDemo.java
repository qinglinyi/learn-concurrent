package demo;


import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo implements Runnable {

    private static int a;

    private static ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {

        for (int i = 0; i < 10000; i++) {
            lock.lock();

            try {
                a++;
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new ReentrantLockDemo());
        Thread t2 = new Thread(new ReentrantLockDemo());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(a);
    }
}
