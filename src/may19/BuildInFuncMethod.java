package may19;

import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.Consumer;

public class BuildInFuncMethod {

    // Методы по умолчанию (default methods) в Java были введены в версии Java 8 и позволяют добавлять
    // реализацию методов в интерфейсы. Это важное изменение, так как до Java 8 интерфейсы могли содержать
    // только абстрактные методы. Методы по умолчанию дают возможность обновлять интерфейсы с новыми методами
    // без необходимости изменять все классы, которые их реализуют.

    // Пример нескольких встроенных функциональных интерфейсов в пакете java.util.function
    // Predicate<T>: содержит метод boolean test(T t). Используется для проверки условий.
    // Function<T, R>: содержит метод R apply(T t). Применяется для преобразования одного объекта в другой.
    // Supplier<T>: содержит метод T get(). Используется для поставки значений.
    // Consumer<T>: содержит метод void accept(T t). Применяется для операций над объектами.

    public static void main(String[] args) {
        // Пример Predicate
        Predicate<String> isEmpty = String::isEmpty;
        System.out.println(isEmpty.test("")); // true
        System.out.println(isEmpty.test("Hello")); // false

        // Пример Function
        Function<String, Integer> length = String::length;
        System.out.println(length.apply("Hello")); // 5

        // Пример Supplier
        Supplier<String> supplier = () -> "Hello, World!";
        System.out.println(supplier.get()); // Hello, World!

        // Пример Consumer
        Consumer<String> printer = System.out::println;
        printer.accept("Hello, World!"); // Hello, World!
    }
}
