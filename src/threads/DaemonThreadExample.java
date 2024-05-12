package threads;

public class DaemonThreadExample {
    public static void main(String[] args) {
        Thread daemonThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);  // Имитация полезной офисной работы
                    System.out.println("Демон работает...");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Восстановление статуса прерывания
                    System.out.println("Демон прерван");
                }
            }
        });

        daemonThread.setDaemon(true);  // Важно! Необходимо вызвать перед start()
        daemonThread.start();

        System.out.println("Основной поток завершен.");
    }
}