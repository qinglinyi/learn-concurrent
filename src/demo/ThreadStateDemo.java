package demo;

public class ThreadStateDemo {

    static final Object obj = new Object();

    static class T1 extends Thread {

        private T2 t2;

        public T1() {
//            t2 = new T2();
//            t2.start();
        }


        private boolean suspend;

        public void suspendMe() {
            suspend = true;
        }

        public void resumeMe() {
            suspend = false;
            synchronized (this) {
                notify();
            }
        }

        @Override
        public void run() {

//            try {
//                t2.join(10000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//                return;
//            }

//            while (true) {
//
//                if (Thread.currentThread().isInterrupted()) {
//                    break;
//                }

//                synchronized (this) {
//                    if (suspend) {// 挂起
//                        try {
//                            wait(10000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                            interrupt();
//                        }
//                    }
//                }

                synchronized (obj) {
                    try {
                        obj.wait(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        interrupt();
                    }
                    System.out.println("====haha===");
                }

//            }
        }
    }

    static class T2 extends Thread {
        @Override
        public void run() {
            synchronized (obj) {
                try {
                    sleep(3000);
                    obj.notify();
                    System.out.println("notify ");
                    sleep(6000);
                    System.out.println("sleep end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        T1 t1 = new T1();
//        System.out.println(t1.getState());
//        t1.start();
//        System.out.println(t1.getState());
//        Thread.sleep(1000);
//        t1.suspendMe();
//        System.out.println(t1.getState());
//        Thread.sleep(1000);
//        System.out.println(t1.getState());
//
//        Thread.sleep(1000);
//        t1.resumeMe();
//        System.out.println(t1.getState());
//        Thread.sleep(1000);
//        System.out.println(t1.getState());

        T1 t1 = new T1();
        t1.start();
        T2 t2 = new T2();
        t2.start();

        Thread.sleep(1000);
        System.out.println(t1.getState());

        Thread.sleep(4000);
        System.out.println(t1.getState());

        Thread.sleep(6000);
        System.out.println(t1.getState());
    }
}
