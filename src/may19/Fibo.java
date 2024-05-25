package may19;

import java.math.BigInteger;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Fibo extends RecursiveTask<BigInteger> {
    private final BigInteger number;

    public Fibo(BigInteger number) {
        this.number = number;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите целое число для вычисления числа Фибоначчи или 'exit' для выхода:");
            String input = scanner.next();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            try {
                BigInteger n = new BigInteger(input);
                ForkJoinPool pool = new ForkJoinPool();
                Fibo task = new Fibo(n);
                BigInteger result = pool.invoke(task);
                System.out.println("Result: " + result);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите допустимое целое число.");
            }
        }

        scanner.close();
        System.out.println("Программа завершена.");
    }

    @Override
    protected BigInteger compute() {
        if (number.equals(BigInteger.ZERO) || number.equals(BigInteger.ONE)) {
            return number;
        }

        Fibo task1 = new Fibo(number.subtract(BigInteger.ONE));
        Fibo task2 = new Fibo(number.subtract(BigInteger.TWO));

        task1.fork();
        BigInteger result2 = task2.compute();
        BigInteger result1 = task1.join();

        return result1.add(result2);
    }
}
