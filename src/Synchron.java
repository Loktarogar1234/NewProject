public class Synchron {
    // Synchronized - монополия использования метода
    // Когда метод объекта объявлен как synchronized, монитор (или замок) объекта используется для контроля
    // доступа к методу. То есть, в один и тот же момент времени только один поток может выполнить любой
    // synchronized метод для данного экземпляра класса.

    class Counter {
        private int count = 0;

        public synchronized void increment() {
            count++;
        }

        public synchronized int getCount() {
            return count;
        }
        // Когда метод объекта объявлен как synchronized, монитор (или замок) объекта используется для
        // контроля доступа к методу. То есть, в один и тот же момент времени только один поток может
        // выполнить любой synchronized метод для данного экземпляра класса.
        // В этом примере, если один поток вызывает метод increment(), другой поток не сможет вызвать
        // increment() или getCount() для того же объекта, пока первый поток не завершит свой вызов.
    }

    class StaticCounter {
        private static int count = 0;

        public static synchronized void increment() {
            count++;
        }

        public static synchronized int getCount() {
            return count;

        }
        // Когда статический метод объявляется как synchronized, используется монитор соответствующего класса,
        // а не отдельного объекта. Это означает, что в один и тот же момент времени только один поток может
        // выполнить любой статический synchronized метод данного класса
    }
}