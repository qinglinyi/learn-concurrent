package demo;


public class SynchronizedDemo implements Runnable {

    static int a;

    static final Object obj1 = new Object();
    final Object obj2 = new Object();

    @Override
    public void run() {
        lockWork();
    }

    public void lockWork() {
        synchronized (this) {
            doWork();
        }
    }

//    public synchronized void lockWork() {
//        doWork();
//    }


//    public static synchronized void lockWork() {
//        doWork();
//    }


//    public void lockWork() {
//        synchronized (SynchronizedDemo.class) {
//            doWork();
//        }
//    }

//    public void lockWork() {
//        synchronized (obj1) {
//            doWork();
//        }
//    }

//    public void lockWork() {
//        synchronized (obj1) {
//            doWork();
//        }
//    }

    private void doWork() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + (a++));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {

//        one();
        two();
    }

    static void one() {
        SynchronizedDemo demo = new SynchronizedDemo();
        new Thread(demo, "A").start();
        new Thread(demo, "B").start();
    }

    static void two() {
        new Thread(new SynchronizedDemo(), "A").start();
        new Thread(new SynchronizedDemo(), "B").start();
    }

}
