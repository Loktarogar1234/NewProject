package may19;

import java.math.BigInteger;
import java.util.Scanner;

public class Factorial {
    public static BigInteger factorial(int n) {
        if (n == 0) {
            return BigInteger.ONE; // Базовый случай: факториал 0 равен 1
        } else {
            return BigInteger.valueOf(n).multiply(factorial(n - 1)); // Рекурсивный случай: n * (n-1)!
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите целое число для вычисления факториала или 'exit' для выхода:");
            String input = scanner.next();

            // Для выхода
            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            try {
                int n = Integer.parseInt(input);
                if (n < 0) {
                    System.out.println("Ошибка: введите неотрицательное целое число.");
                } else {
                    // Вывод результата
                    System.out.println("Факториал " + n + " равен " + factorial(n));
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число.");
            }
        }

        scanner.close();
        System.out.println("Программа завершена.");
    }
}
