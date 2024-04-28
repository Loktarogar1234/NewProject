public class Monitors {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public synchronized void decrement() {
        count--;
    }

    public synchronized int getCount() {
        return count;
    }

/*
Методы increment, decrement и getCount синхронизированы, т.е. каждый из них использует монитор
объекта Counter, таким образом только один поток может изменять или читать значение count в один момент
времени
*/

    public class Counter {
        private volatile int count = 0;

        public void increment() {
            count++;
        }

        public int getCount() {
            return count;
        }
    }

    /*
    Ключевое слово volatile в Java используется для переменных, которые могут быть изменены различными потоками.
    Оно обеспечивает, что значение переменной всегда будет считываться из основной памяти, а не из кеша потока,
    что гарантирует видимость изменений между потоками.
     */
}
