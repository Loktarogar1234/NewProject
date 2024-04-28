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

}
