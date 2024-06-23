package june23;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockExample {
    private final Lock lock = new ReentrantLock();
    // Объявление обычного замка ReentrantLock.
    // lock: Обычный замок ReentrantLock для синхронизации доступа к counter.
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    // Объявление замка ReadWriteLock.
    // readWriteLock: Замок ReadWriteLock, который предоставляет отдельные блокировки для чтения и записи.
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();
    // Разделение замка ReadWriteLock на замок для чтения и замок для записи.
    // readLock и writeLock: Замки для чтения и записи, полученные из readWriteLock.
    private int counter = 0;
    // Переменная-счетчик, доступ к которой нужно синхронизировать

    public static void main(String[] args) {
        LockExample example = new LockExample();

        // Создание и запуск потоков с использованием ReentrantLock
        Thread t1 = new Thread(example::incrementWithReentrantLock);
        Thread t2 = new Thread(example::incrementWithReentrantLock);

        // Создание и запуск потоков с использованием ReadWriteLock
        Thread t3 = new Thread(example::readWithReadWriteLock);
        Thread t4 = new Thread(example::writeWithReadWriteLock);

        // Создание и запуск потоков с использованием synchronized
        Thread t5 = new Thread(example::incrementWithSynchronized);
        Thread t6 = new Thread(() -> System.out.println("Synchronized - Read counter: " + example.getCounterWithSynchronized()));

        // Запуск всех потоков
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();

        try {
            // Ожидание завершения работы всех потоков
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
            t6.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Метод с инкрементом + ReentrantLock
    public void incrementWithReentrantLock() {
        lock.lock(); // Захват замка
        try {
            counter++;
            System.out.println("ReentrantLock - Incremented counter to " + counter);
        } finally {
            lock.unlock(); // Освобождение замка
        }
    }

    // Метод чтения с использованием ReadWriteLock
    public void readWithReadWriteLock() {
        readLock.lock(); // Захват замка для чтения
        try {
            System.out.println("ReadWriteLock - Read counter: " + counter);
        } finally {
            readLock.unlock(); // Освобождение замка для чтения
        }
    }

    // Метод записи с использованием ReadWriteLock
    public void writeWithReadWriteLock() {
        writeLock.lock(); // Захват замка для записи
        try {
            counter++;
            System.out.println("ReadWriteLock - Incremented counter to " + counter);
        } finally {
            writeLock.unlock(); // Освобождение замка для записи
        }
    }

    // Синхронизированный метод для увеличения значения счетчика и печати значения
    public synchronized void incrementWithSynchronized() {
        counter++;
        System.out.println("Synchronized - Incremented counter to " + counter);
    }

    // Синхронизированный метод получения значения счётчика
    public synchronized int getCounterWithSynchronized() {
        return counter; // Возврат значения счётчика
    }
}
