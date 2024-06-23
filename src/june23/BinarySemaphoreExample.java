package june23;

import java.util.concurrent.Semaphore;

public class BinarySemaphoreExample {
    private static final Semaphore binarySemaphore = new Semaphore(1);
    // Семафор с одним разрешением на все потоки, эквивалентен мьютексу

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Task()).start();
        }
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            try {
                binarySemaphore.acquire();
                System.out.println(Thread.currentThread().getName() + " получил доступ");
                // Симуляция работы
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + " освобождает доступ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                binarySemaphore.release();
            }
        }
    }
}
