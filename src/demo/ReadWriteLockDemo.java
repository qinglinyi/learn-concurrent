package demo;


import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {

    private double value;

    public double read(Lock lock) throws InterruptedException {
        lock.lock();
        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getId() + ": read: " + value);
            return value;
        } finally {
            lock.unlock();
        }
    }

    public void write(Lock lock, double value) throws InterruptedException {
        lock.lock();
        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getId() + ": write!");
            this.value = value;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final ReadWriteLockDemo d = new ReadWriteLockDemo();
        final ReentrantLock lock = new ReentrantLock();
        final ReadWriteLock rwLock = new ReentrantReadWriteLock();

        Runnable readRunnable = new Runnable() {
            @Override
            public void run() {
                try {
//                    d.read(lock);
                    d.read(rwLock.readLock());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable writeRunnable = new Runnable() {
            @Override
            public void run() {
                try {
//                    d.write(lock, new Random().nextInt());
                    d.write(rwLock.writeLock(), new Random().nextInt());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        for (int i = 18; i < 20; i++) {
            new Thread(writeRunnable).start();
        }
        for (int i = 0; i < 18; i++) {
            new Thread(readRunnable).start();
        }

    }

}
