package theBeginning;

public class MultiplicationTable {
    public static void main(String[] args) {
        int size = 8; // размер таблицы (9x9)

        // Вывод заголовка таблицы
        System.out.println("ТАБЛИЦА ПИФАГОРА");

        // Вывод верхнего заголовка (числа от 2 до 9)
        for (int i = 2; i <= size + 1; i++) {
            System.out.print(i + "\t");
        }
        System.out.println();

        // Вывод прерывистой линии
        for (int i = 2; i <= size + 1; i++) {
            System.out.print("----");
        }
        System.out.println();

        // Вывод таблицы умножения
        for (int i = 2; i <= size + 1; i++) {
            for (int j = 2; j <= size + 1; j++) {
                System.out.print(i * j + "\t");
            }
            System.out.println(); // Переход на новую строку после каждой строки таблицы
        }
    }
}
