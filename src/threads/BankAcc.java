package threads;

class BankAccounts {
    private int balance;

    public BankAccounts(int initialBalance) {
        this.balance = initialBalance;
    }

    // Метод для вклада средств, синхронизирован на уровне метода
    public synchronized void deposit(int amount) {
        System.out.println("Внесение " + amount);
        balance += amount;
        System.out.println("Новый баланс после вклада: " + balance);
    }

    // Метод для снятия средств, синхронизирован на уровне метода
    public synchronized void withdraw(int amount) {
        System.out.println("Снимаем " + amount);
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Новый баланс после снятия: " + balance);
        } else {
            System.out.println("Недостаточно средств для снятия");
        }
    }

    // Получение текущего баланса, также синхронизирован для согласованности
    public synchronized int getBalance() {
        return balance;
    }
}

public class BankAcc {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);

        // Создаем потоки для вклада и снятия средств
        Thread depositThread = new Thread(() -> {
            account.deposit(300);
        });

        Thread withdrawThread = new Thread(() -> {
            // Ругается на account.withdraw(200);
            try {
                account.withdraw(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        depositThread.start();
        withdrawThread.start();

        try {
            depositThread.join();
            withdrawThread.join();
        } catch (InterruptedException e) {
            System.err.println("Один из потоков был прерван.");
        }

        System.out.println("Конечный баланс: " + account.getBalance());
    }
}
