package may19;

public class ThreadInterruptExample {
    // Прерывание потока (Thread.interrupt()) используется для уведомления потока о необходимости остановки.
    // Это не заставляет поток немедленно остановиться, но устанавливает флаг прерывания, который поток может
    // проверять и соответствующим образом реагировать
    // Основные методы для работы с прерыванием потока:
    // interrupt(): Устанавливает флаг прерывания потока.
    // isInterrupted(): Проверяет, установлен ли флаг прерывания для потока.
    // Thread.interrupted(): Проверяет, установлен ли флаг прерывания для текущего потока, и сбрасывает этот флаг.

    public static void main(String[] args) {
        Thread worker = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Работаю...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Прервали во сне.");
                    Thread.currentThread().interrupt(); // Сохраняем статус прерывания
                }
            }
            System.out.println("Рабочий поток завершен.");
        });

        worker.start();

        try {
            Thread.sleep(5000); // Главный поток спит 5 секунд
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        worker.interrupt(); // Прерываем рабочий поток
    }
}