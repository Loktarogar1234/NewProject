package threads;

public class LifeCicles {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new DemoBlockedRunnable());
        Thread t2 = new Thread(new DemoBlockedRunnable());

        t1.start();
        t2.start();

        Thread.sleep(3000);
        System.out.println(t2.getState());
        // System.exit(0);
        // тут будет блокед
        Thread.currentThread().interrupt();

        ThreadSome task = new ThreadSome();
        Thread t3 = new Thread(task);

        t2.join(); // ждем окончания работы потока т2
        t3.start(); // запускаем код таска в новом потоке
        task.run(); // запускаем код ран в текущем потоке
        Thread.sleep(3000);

    }
}

class DemoBlockedRunnable implements Runnable {
    public static synchronized void commonResource() {
        while (true) {

        }
    }

    @Override
    public void run() {
        commonResource();
    }

    /*
    Мы создали два разных потока – t1 и t2
    t1 запускается и вводит синхронизированный метод commonResource(); это означает, что только один поток
    может получить к нему доступ; все остальные последующие потоки, которые попытаются получить доступ к этому
    методу, будут заблокированы от дальнейшего выполнения до тех пор, пока текущий не завершит обработку.
    Когда t1 входит в этот метод, он сохраняется в бесконечном цикле while; Это сделано для имитации
    интенсивной обработки, чтобы все остальные потоки не могли войти в этот метод.
    Теперь, когда мы запускаем t2, он пытается ввести метод commonResource(), к которому уже обращается t1,
    таким образом, t2 будет сохранен в состоянии BLOCKED.
    Вызовем t2.getState() и получим результат "BLOCKED"
    */
}


class ThreadSome implements Runnable {
    public void run() {
        System.out.println("Запуск кода " + Thread.currentThread().getName());
    }
}