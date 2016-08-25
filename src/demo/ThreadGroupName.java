package demo;


public class ThreadGroupName implements Runnable {


    @Override
    public void run() {
        String name = Thread.currentThread().getThreadGroup().getName() + "-" + Thread.currentThread().getName();
        while (true) {
            if (Thread.currentThread().isInterrupted()) break;
            System.out.println("I am " + name);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadGroup tg = new ThreadGroup("PrintGroup");
        Thread t1 = new Thread(tg, new ThreadGroupName(), "T1");
        Thread t2 = new Thread(tg, new ThreadGroupName(), "T2");

        t1.start();
        t2.start();

        System.out.println(tg.activeCount());
        tg.list();

//        Thread.sleep(4000);
//        tg.interrupt();

    }
}
