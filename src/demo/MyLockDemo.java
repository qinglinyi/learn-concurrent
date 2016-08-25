package demo;

/**
 * Created by Evan on 2016/08/02.
 */
public class MyLockDemo {

    public synchronized void m1() {
        System.out.println("=====m1======");
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m2();
        m3();

    }

    public synchronized void m2() {
        System.out.println("=====m2======");
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m3();
    }

    public synchronized void m3() {
        System.out.println("=====m3======");
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final MyLockDemo demo = new MyLockDemo();
        for (int i = 0; i < 1000; i++) {
            final int j = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (j % 3 == 0) {
                        demo.m3();
                    } else if (j % 2 == 0) {
                        demo.m2();
                    } else {
                        demo.m1();
                    }
                }
            }).start();
        }
    }
}
