public class Program {
    //класс - контейнер с кодом
    //public - модификатор доступа к классу
    //class - ключевое слово, признак класса
    //Main - имя класса, должно совпадать с именем файла класса
    public static void main(String[] args) {
        //public - модификатор доступа к методу
        //static - модификатор метода, создающий его без создания экземпляра класса
        //void - указатель типа возвращаемы
        //main - точка запуска кода на исполнение
        //(String[] args) - параметры метода
        //() - тело метода, сам код

        System.out.println("\nПривет Мир!\n");

        hello();
        welcome();
        welcome();

        static void hello () {
            System.out.println("Hello World");
        }

        static void welcome () {
            System.out.println("Welcome 2 Java");
        }
        for (int i = 1; i <= 5; i++) {
            System.out.println("i = " + i);
        }
    }


    public interface Drivable {
        void drive();
    }
    public class Car implements Drivable {
        @Override
        public void drive() {
            System.out.println("Машина едет");
        }
    }

}