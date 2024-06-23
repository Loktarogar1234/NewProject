package june23;

import java.util.concurrent.Semaphore;

public class ResourcePoolExample {
    private static final int MAX_AVAILABLE = 5;
    private final Semaphore available = new Semaphore(MAX_AVAILABLE, true);
    // Семафор создается с максимальным количеством разрешений (MAX_AVAILABLE). Параметр true (параметр справедливости)
    // указывает на справедливый порядок выдачи разрешений, т.е. потоки получат доступ к ресурсу в порядке их
    // поступления запросов. Это справедливый семафор.

    public static void main(String[] args) {
        ResourcePoolExample pool = new ResourcePoolExample();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    pool.useResource();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    public void useResource() throws InterruptedException {
        available.acquire();
        try {
            System.out.println(Thread.currentThread().getName() + " получил доступ к ресурсу");
            // Симуляция работы
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " освобождает ресурс");
        } finally {
            available.release();
        }
    }
}
