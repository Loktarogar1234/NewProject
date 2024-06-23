package june23;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

// Stream API в Java позволяет обрабатывать коллекции и другие источники данных в функциональном стиле, упрощая операции
// фильтрации, сортировки и преобразования. Потоки (streams) обеспечивают высокоуровневое API для работы с данными,
// позволяя выполнять такие операции, как фильтрация, сортировка, отображение и агрегация.
//
// Основные компоненты Stream API:
// 1. Создание потока. Потоки можно создавать из коллекций, массивов, файлов и других источников данных.

// 2. Операции промежуточные (intermediate operations). Операции, которые возвращают новый поток и могут быть объединены
// в цепочку. Они выполняются лениво, то есть фактическое выполнение операций происходит только при вызове терминальной
// операции. Примеры:
// filter: Фильтрация элементов потока на основе предиката.
// map: Преобразование элементов потока.
// flatMap: Преобразование каждого элемента потока в другой поток, а затем объединение всех полученных потоков в один.
// sorted: Сортировка элементов потока.
// distinct: Удаление дубликатов из потока.
// limit: Ограничение количества элементов в потоке.
// skip: Пропуск первых N элементов потока.
//
// 3. Операции терминальные (terminal operations). Операции, которые завершают работу потока и возвращают результат
// выпонения. Примеры:
// forEach: Выполнение действия для каждого элемента потока.
// collect: Сбор элементов потока в коллекцию или другую структуру данных.
// reduce: Агрегация элементов потока в одно значение.
// count: Подсчет количества элементов в потоке.
// anyMatch, allMatch, noneMatch: Проверка, соответствуют ли элементы потока предикату.
// findFirst, findAny: Поиск первого или любого элемента потока.

public class StreamExample {
    // Пример использования Stream API для фильтрации, преобразования и сортировки списка имен
    public static void main(String[] args) {
        // Создаем список имен
        List<String> names = Arrays.asList("John", "Jane", "Jack", "Doe", "Mary", "Jane", "John");

        // Создаем поток и выполняем лямбда-операцию
        List<String> filteredNames = names.stream()
                .filter(name -> name.startsWith("J")) // Промежуточная операция фильтрации имен, начинающихся с J
                .map(String::toUpperCase)             // Промежуточная операция преобразования имен в верхний регистр
                .sorted()                             // Промежуточная операция прямой сортировки
                .toList();                            // Терминальная операция сбора результатов в список
        Consumer<String> printConsumer = name -> System.out.println("Name: " + name);
        filteredNames.forEach(printConsumer); // Терминальная операция печати имен с использованием Consumer
        //Можно так (System.out::println) или (name -> System.out.println(name))

        // Промежуточные операции

        // map: Преобразование элементов в верхний регистр
        List<String> upperCaseNames = names.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("Uppercase names: " + upperCaseNames);

        // flatMap: Разделение строк на символы и объединение в один поток
        List<Character> characters = names.stream()
                .flatMap(name -> name.chars().mapToObj(c -> (char) c))
                .collect(Collectors.toList());
        System.out.println("Characters: " + characters);

        // sorted: Сортировка имен
        List<String> sortedNames = names.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("Sorted names: " + sortedNames);

        // distinct: Удаление дубликатов
        List<String> distinctNames = names.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Distinct names: " + distinctNames);

        // limit: Ограничение количества элементов до 3
        List<String> limitedNames = names.stream()
                .limit(3)
                .collect(Collectors.toList());
        System.out.println("Limited names: " + limitedNames);

        // skip: Пропуск первых 2 элементов
        List<String> skippedNames = names.stream()
                .skip(2)
                .collect(Collectors.toList());
        System.out.println("Skipped names: " + skippedNames);

        // Терминальные операции

        // forEach: Печать каждого имени
        System.out.print("Names: ");
        names.stream()
                .forEach(name -> System.out.print(name + " "));
        System.out.println();

        // collect: Сбор имен в список
        List<String> collectedNames = names.stream()
                .collect(Collectors.toList());
        System.out.println("Collected names: " + collectedNames);

        // reduce: Конкатенация всех имен
        Optional<String> concatenatedNames = names.stream()
                .reduce((name1, name2) -> name1 + ", " + name2);
        concatenatedNames.ifPresent(s -> System.out.println("Concatenated names: " + s));

        // count: Подсчет количества имен
        long count = names.stream()
                .count();
        System.out.println("Count of names: " + count);

        // anyMatch: Проверка, есть ли имена, начинающиеся с "M"
        boolean anyMatch = names.stream()
                .anyMatch(name -> name.startsWith("M"));
        System.out.println("Any names start with 'M': " + anyMatch);

        // allMatch: Проверка, все ли имена начинаются с "J"
        boolean allMatch = names.stream()
                .allMatch(name -> name.startsWith("J"));
        System.out.println("All names start with 'J': " + allMatch);

        // noneMatch: Проверка, что ни одно имя не начинается с "Z"
        boolean noneMatch = names.stream()
                .noneMatch(name -> name.startsWith("Z"));
        System.out.println("No names start with 'Z': " + noneMatch);

        // findFirst: Поиск первого имени
        Optional<String> firstName = names.stream()
                .findFirst();
        firstName.ifPresent(name -> System.out.println("First name: " + name));

        // findAny: Поиск любого имени
        Optional<String> anyName = names.stream()
                .findAny();
        anyName.ifPresent(name -> System.out.println("Any name: " + name));
    }
}
