package threads;

class DemoWaitingStateRunnable implements Runnable {
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
        System.out.println(States.t1.getState());
    }
}

public class States implements Runnable {
    public static Thread t1;

    public static void main(String[] args) {
        t1 = new Thread(new States());
        t1.start();

        Object lock = new Object();
        Thread waiter = new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println("Waiting thread is waiting.");
                    lock.wait();  // Поток останавливается и ждет notify
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Waiting thread resumed.");
            }
        });

        Thread notifier = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Notifier thread is notifying.");
                lock.notify();  // Уведомляем ожидающий поток
                System.out.println("Notifier thread completed.");
            }
        });

        waiter.start();
        try {
            Thread.sleep(1000);  // Убедимся, что waiter точно начал ожидание
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        notifier.start();
    }

    public void run() {
        Thread t2 = new Thread(new DemoWaitingStateRunnable());
        t2.start();

        try {
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}



