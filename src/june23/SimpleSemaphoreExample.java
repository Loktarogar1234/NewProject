package june23;

import java.util.concurrent.Semaphore;
// Класс, в котором реализованы семафоры

// Семафор — это синхронизационный примитив, используемый для управления доступом к общему ресурсу из нескольких потоков
// Счетчик разрешений: Семафор управляет набором разрешений. Потоки могут запрашивать разрешения (acquire()) и
// освобождать их (release()).
//Блокировка при отсутствии разрешений: Если поток запрашивает разрешение, а их нет, он блокируется до тех пор, пока
// другое разрешение не будет освобождено.
//Два типа семафоров:
//Семафор с разрешениями: Потоки могут одновременно выполнять операции до тех пор, пока не израсходуются все разрешения.
//Бинарный семафор: Семафор с одним разрешением, эквивалентен мьютексу

public class SimpleSemaphoreExample {

    private static final Semaphore semaphore = new Semaphore(3);
    // Семафор создается с 3 разрешениями, т.е. максимум 3 потока могут получить доступ одновременно

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Task()).start();
            // Создаются 10 потоков, которые запрашивают доступ к ресурсу через семафор
        }
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + " получил доступ");

                Thread.sleep(2000);
                // Симуляция работы
                System.out.println(Thread.currentThread().getName() + " освобождает доступ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
    }


}
