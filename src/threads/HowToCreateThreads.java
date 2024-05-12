package threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Наследование от класса Thread");
        // Этот способ включает создание нового класса, который наследуется от класса Thread и переопределяет
        // его метод run().
    }
}

class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Реализация интерфейса Runnable");
        // В этом методе создаётся класс, который реализует интерфейс Runnable, и экземпляр этого класса
        // передаётся в конструктор Thread.
          }
}


public class HowToCreateThreads {
    public static void main(String[] args) {
        // Наследование от класса Thread, просто и понятно, но менее гибко, чем реализация Runnable
        MyThread thread1 = new MyThread();
        thread1.start();

        // Реализация интерфейса Runnable
        Thread thread2 = new Thread(new MyRunnable());
        thread2.start();

        // Использование анонимного класса, реализующего Runnable, позволяет писать код быстро и компактно
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Использование анонимного класса, реализующего Runnable");
            }
        });
        thread3.start();

        // Использование лямбда-выражения, позволяет писать код быстро и компактно. Наш метод!
        Thread thread4 = new Thread(() -> System.out.println("Использование лямбда-выражения"));
        thread4.start();

        // Использование класса ExecutorService, предоставляет мощные средства для управления потоками
        // в многопоточных приложениях/
        ExecutorService executor = Executors.newFixedThreadPool(2);
        // Создаем пул из 2 потоков
        executor.submit(() -> System.out.println("Использование класса ExecutorService"));
        // Отправка задачи на выполнение через Лямбда-выражение - выводит строку в консоль. Это задача будет
        // выполнена асинхронно одним из потоков в пуле
        executor.shutdown();
        // shutdown() — метод, который инициирует плавное завершение работы ExecutorService, не принимая
        // новые задачи, но выполняя все ранее представленные задачи. После вызова shutdown(), исполнитель
        // переходит в состояние завершения, и после выполнения всех задач все потоки будут корректно
        // завершены. Это важный шаг для предотвращения утечек памяти, так как позволяет чистить ресурсы,
        // занятые потоками.
    }
}



