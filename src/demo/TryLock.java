package demo;


import java.util.concurrent.locks.ReentrantLock;

public class TryLock implements Runnable {

    private static ReentrantLock lock1 = new ReentrantLock();
    private static ReentrantLock lock2 = new ReentrantLock();
    private int lock;

    public TryLock(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {


        if (lock == 1) {
            while (true) {
                if (lock1.tryLock()) {
                    try {
                        System.out.println(Thread.currentThread().getId() + ":get Lock 1");
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        try {
                            if (lock2.tryLock()) {
                                System.out.println(Thread.currentThread().getId() + ":I Done 2");
                                return;
                            }
                        } finally {
                            if (lock2.isHeldByCurrentThread()) lock2.unlock();
                        }
                    } finally {
                        if (lock1.isHeldByCurrentThread()) lock1.unlock();
                    }
                }
            }

        } else {
            while (true) {
                if (lock2.tryLock()) {
                    try {
                        System.out.println(Thread.currentThread().getId() + ":get Lock 2");
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        try {
                            if (lock1.tryLock()) {
                                System.out.println(Thread.currentThread().getId() + ":I Done 1");
                                return;
                            }
                        } finally {
                            if (lock1.isHeldByCurrentThread()) lock1.unlock();
                        }
                    } finally {
                        if (lock2.isHeldByCurrentThread()) lock2.unlock();
                    }
                }
            }
        }

    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new TryLock(1));
        Thread t2 = new Thread(new TryLock(2));

        t1.start();
        t2.start();
    }
}
