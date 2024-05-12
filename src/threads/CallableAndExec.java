package threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableAndExec {
    public static void main(String[] args) {
        Callable<String> callableTask = () -> { // Создаем задачу
            System.out.println("Выполняется в: " + Thread.currentThread().getName());
            Thread.sleep(1000);
            return "Результат выполнения задачи";
        };

        ExecutorService threadPool = Executors.newFixedThreadPool(4); // Создаем пул потоков

        try {
            Future<String> futureResult = threadPool.submit(callableTask); // Запуск задач
            String result = futureResult.get();  // Ожидание результата
            System.out.println("Результат: " + result); // Вывод результата
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();  // Остановка пула
        }
    }
}