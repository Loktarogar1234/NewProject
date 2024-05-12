import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 10; i++) {
            int taskNumber = i;
            executor.submit(() -> {
                System.out.println("Выполнение задачи " + taskNumber + " потоком " + Thread.currentThread().getName());
            });
        }
        executor.shutdown();
    }
}

// Пулы потоков (Thread Pools) - это способ управления множеством потоков одновременно. Использование пулов
// потоков позволяет управлять количеством потоков, которые выполняют задачи, избегая создания новых потоков
// для каждой задачи, что может быть ресурсоемким и дорогим с точки зрения производительности.