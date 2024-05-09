package Threads;

import java.util.Scanner;

class BankAccount {
    private Integer balance;  // Приватное хранение баланса счета.
    // INTEGER - способ определения НЕ примитивного типа. !! integer.fromstring

    public BankAccount(int initialBalance) {
        balance = initialBalance;  // Инициализация баланса при создании объекта
    }

    // Синхронизированный метод для пополнения баланса (депозит)
    public synchronized void deposit(int amount) {
        balance += amount;  // Увеличиваем баланс на сумму депозита
        System.out.println("Внесено: " + amount + ", Баланс: " + balance);
        notifyAll();  // Уведомляем все ожидающие потоки о возможном изменении состояния
    }

    // Синхронизированный метод для снятия денег
    public synchronized void withdraw(int amount) throws InterruptedException {
        while (balance < amount) {
            // Вывод информации о недостатке средств и предложение уменьшить сумму
            System.out.println("Недостаточно средств для снятия: " + amount + ", текущий баланс: " + balance);
            System.out.println("Попробуйте уменьшить сумму снятия или дождитесь пополнения счета.");
            wait();  // Ожидаем, пока баланс не увеличится
        }
        // После выхода из цикла `while` баланс достаточен для снятия
        balance -= amount;  // Снимаем указанную сумму
        System.out.println("Снятие: " + amount + ", баланс после снятия: " + balance);
        notifyAll();  // Уведомляем другие потоки об изменении состояния
    }


    // Публичный метод для получения текущего баланса
    public synchronized int getBalance() {
        return balance;  // Возвращаем текущий баланс
    }
}

class BankAccountThread extends Thread {
    private BankAccount account;
    private boolean deposit;
    private int amount;

    public BankAccountThread(BankAccount account, boolean deposit, int amount) {
        this.account = account;  // Устанавливаем ссылку на аккаунт
        this.deposit = deposit;  // Определяем тип операции (внесение/снятие)
        this.amount = amount;  // Устанавливаем сумму операции
    }

    public void run() {
        try {
            if (deposit) {
                account.deposit(amount);  // Если операция депозит, вносим сумму
            } else {
                account.withdraw(amount);  // Если операция снятие, снимаем сумму
            }
            Thread.sleep(3000);  // Поток спит 3 секунды
        } catch (InterruptedException e) { //Ловим ошибки
            e.printStackTrace();
        }
    }
}

public class Somethreads2 {
    public static void main(String[] args) throws InterruptedException {
        BankAccount account = new BankAccount(100);  // Создаем аккаунт с начальным балансом 100

        Scanner scan = new Scanner(System.in);

        System.out.println("Введите сумму пополнения");
        int a = scan.nextInt();
        BankAccountThread thread1 = new BankAccountThread(account, true, a);  // Создаем поток для депозита

        System.out.println("Сколько хотите снять?");
        int b = scan.nextInt();
        BankAccountThread thread2 = new BankAccountThread(account, false, b);  // Создаем поток для снятия

        thread1.start();  // Запускаем поток 1
        thread2.start();  // Запускаем поток 2

        thread1.join();  // Ожидаем завершения потока 1
        thread2.join();  // Ожидаем завершения потока 2

        System.out.println("Баланс: " + account.getBalance());  // Выводим итоговый баланс
    }
}
