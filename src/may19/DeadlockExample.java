package may19;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockExample {
    private static final Lock lock1 = new ReentrantLock();
    private static final Lock lock2 = new ReentrantLock();
    //  ReentrantLock с тайм-аутами для предотвращения блокировок

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            if (lock1.tryLock() && lock2.tryLock()) {
                try {
                    System.out.println("Thread 1: Holding lock 1 & 2...");
                } finally {
                    lock1.unlock();
                    lock2.unlock();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            if (lock2.tryLock() && lock1.tryLock()) {
                try {
                    System.out.println("Thread 2: Holding lock 1 & 2...");
                } finally {
                    lock2.unlock();
                    lock1.unlock();
                }
            }
        });

        t1.start();
        t2.start();
    }
}
