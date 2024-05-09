package Threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class exec {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);  // Создаем пул с фиксированным числом потоков = 3

        Runnable world = () -> {
            String threadName = Thread.currentThread().getName();  // Получаем имя текущего потока
            System.out.println("Hello " + threadName);  // Хело поток
        };

        executor.submit(world);  // Отправляем задачу для выполнения в одном из потоков пула

        try {
            System.out.println("попытка завершить экзекутор");
            executor.shutdown();  // Инициируем остановку экзекутора, экзекутор перестает принимать новые задачи
            executor.awaitTermination(5, TimeUnit.SECONDS);  // Ожидаем завершения всех задач в пуле в течение 5 секунд
        } catch (InterruptedException e) {
            System.err.println("ожидание прервано");
        } finally {
            if (!executor.isTerminated()) {  // Проверяем, все ли задачи завершились
                System.err.println("не все задачи завершились");
            }
            executor.shutdownNow();  // Принудительное завершение всех оставшихся задач
            System.out.println("завершено");
        }
    }
}
