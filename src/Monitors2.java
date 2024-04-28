import java.util.LinkedList;
import java.util.Queue;

// Класс Buffer предоставляет методы для взаимодействия между потоками Producer и Consumer.
class Buffer {
    private Queue<Integer> queue; // Очередь для хранения данных
    private int capacity; // Максимальная вместимость очереди

    // Конструктор инициализирует очередь и устанавливает её вместимость.
    public Buffer(int capacity) {
        this.queue = new LinkedList<>();
        this.capacity = capacity;
    }

    // Метод для добавления элемента в очередь. Синхронизирован для предотвращения конкурентного доступа.
    public synchronized void put(int value) throws InterruptedException {
        while (queue.size() == capacity) { // Пока очередь полна, ждём освобождения места.
            System.out.println("Очередь полна, ждем свободное место.");
            wait(); // Ожидание освобождения места в буфере.
        }
        queue.add(value); // Добавление элемента в очередь.
        System.out.println("Произведен: " + value);
        notifyAll(); // Уведомление ожидающих потоков о возможности действий.
    }

    // Метод для извлечения элемента из очереди. Синхронизирован для предотвращения конкурентного доступа.
    public synchronized int take() throws InterruptedException {
        while (queue.isEmpty()) { // Пока очередь пуста, ждём наличия данных.
            System.out.println("Buffer is empty. Consumer is waiting.");
            wait(); // Ожидание появления данных в буфере.
        }
        int value = queue.poll(); // Извлечение элемента из очереди.
        System.out.println("Потребление: " + value);
        notifyAll(); // Уведомление ожидающих потоков о возможности действий.
        return value;
    }
}

// Класс Producer определяет поток для производства данных.
class Producer extends Thread {
    private Buffer buffer; // Ссылка на общий буфер.

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        try {
            for (int i = 0; i < 10; i++) { // Производство 10 элементов.
                buffer.put(i); // Добавление элемента в буфер.
                Thread.sleep(3000); // Имитация времени производства.
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// Класс Consumer определяет поток для потребления данных.
class Consumer extends Thread {
    private Buffer buffer; // Ссылка на общий буфер.

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        try {
            for (int i = 0; i < 10; i++) { // Потребление 10 элементов.
                int value = buffer.take(); // Извлечение элемента из буфера.
                Thread.sleep(1000); // Имитация времени обработки.
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// Главный класс для запуска потоков Producer и Consumer.
public class Monitors2 {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(5); // Создание буфера на 5 элементов.
        Producer producer = new Producer(buffer); // Создание производителя.
        Consumer consumer = new Consumer(buffer); // Создание потребителя.
        producer.start(); // Запуск потока производителя.
        consumer.start(); // Запуск потока потребителя.
    }
}
