package june23;
import java.util.ArrayList;
import java.util.List;

public class PrivitiveWrapperCasses {
    // В Java существуют примитивные типы данных и их классы-обертки (wrapper classes)boolean -> Boolean, int -> Integer
    // и т.п. Примитивные типы представляют базовые значения, тогда как классы-обертки предоставляют объектную репрезентацию
    // этих значений. Это полезно для работы с коллекциями, где требуется использование объектов, а не примитивных типов
    //
    // Примитивные типы:
    // 1. Хранят значения непосредственно в памяти.
    // 2. Они не могут быть null.
    // 3. Более эффективны по производительности и использованию памяти.
    //
    // Классы-обертки:
    // 1. Оборачивают примитивные значения в объект.
    // 2. Они могут принимать значения null.
    // 3. Предоставляют методы для работы с примитивными значениями.

    public static void main(String[] args) {
        Boolean flag1 = Boolean.TRUE; // Класс-обертка Boolean
        if (flag1) {
            System.out.println("Flag is true");
        } else {
            System.out.println("Flag is false");
        }

        Boolean nullFlag = null;
        if (nullFlag == null) {
            System.out.println("nullFlag is null");
        }

        Boolean flag2 = true;
        Integer number = 10;

        // Автоупаковка в коллекции
        List<Integer> numbers = new ArrayList<>();
        numbers.add(5); // Автоупаковка примитивного типа int в Integer

        // Распаковка: объект класса-обертки автоматически преобразуется в примитивный тип
        int num = number; // Автоупаковка объекта Integer в примитивный тип int

        // Вывод значений
        System.out.println("Boolean flag: " + flag2);
        System.out.println("Integer number: " + number);
        System.out.println("Unboxed int: " + num);
        System.out.println("List of numbers: " + numbers);


    }


}
