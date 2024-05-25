package may19;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExample {
    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);

        // Задача через 5 секунд
        executor.schedule(() -> {
            System.out.println("Task executed after 5 seconds delay on thread " + Thread.currentThread().getName());
        }, 5, TimeUnit.SECONDS);

        // Периодическая задача, которая будет выполняться каждые 2 секунды
        executor.scheduleAtFixedRate(() -> {
            System.out.println("Periodic task executed on thread " + Thread.currentThread().getName());
        }, 0, 2, TimeUnit.SECONDS);

        // Периодическая задача, которая будет выполняться каждые 3 секунды после завершения предыдущей
        executor.scheduleWithFixedDelay(() -> {
            System.out.println("Task with fixed delay executed on thread " + Thread.currentThread().getName());
        }, 0, 3, TimeUnit.SECONDS);

        // Завершение пула через 10 секунд
        executor.schedule(() -> {
            executor.shutdown();
        }, 10, TimeUnit.SECONDS);
    }
}

// ScheduledThreadPool: Подходит для выполнения задач с фиксированной задержкой или для периодического
// выполнения задач, поддерживая фиксированное количество потоков