package may19;

import java.util.concurrent.*;

public class FutureExample {

    public static void main(String[] args) {
        // Создание пула потоков
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // Задача, возвращающая результат
        Callable<Integer> task = () -> {
            Thread.sleep(2000); // имитация работы
            return 123; // возвращаем результатом целое число
        };

        // Запуск задачи и получение объекта Future
        Future<Integer> future = executor.submit(task);

        // Проверка состояния задачи
        while (!future.isDone()) {
            System.out.println("Задача не завершена...");
            try {
                Thread.sleep(500); // каждые полсекунды проверяем
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Задача завершена, получаем результат
        try {
            Integer result = future.get();
            System.out.println("Задача завершена. Результат: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // Завершаем работу пула потоков
        executor.shutdown();
    }
}
