package demo;


public class WaitAndNotifyDemo {

    static final Object obj = new Object();
    private static T1 t1;
    private static T2 t2;

    static class T1 extends Thread {
        @Override
        public void run() {
            synchronized (obj) {
                System.out.println("===T1 begin===");
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("===T1 end===");
            }
        }
    }

    static class T2 extends Thread {
        @Override
        public void run() {
            synchronized (obj) {
                System.out.println("===T2 begin===");
                System.out.println("==2===" + t1.getState());
                obj.notifyAll();
                System.out.println("==3===" + t1.getState());
//                obj.notify();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("===T2 end===");
            }
        }
    }

    static class T3 extends Thread {
        @Override
        public void run() {
            synchronized (obj) {
                System.out.println("===T3 begin===");
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("===T3 end===");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        t1 = new T1();
        t1.start();
        new T3().start();
        t2 = new T2();
        t2.start();
        Thread.sleep(100);
        System.out.println("==1===" + t1.getState());
        System.out.println(t2.getState());


    }
}
