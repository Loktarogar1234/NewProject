package june23;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// Метод parallelStream является частью Stream API в Java и позволяет работать с потоками данных в многопоточном режиме.
// Использование parallelStream может значительно повысить производительность при обработке больших объемов данных,
// за счет распараллеливания операций над элементами потока.
// Основные характеристики parallelStream
// - Параллельное выполнение: Операции над элементами потока выполняются параллельно, используя несколько потоков.
// - Объединение результатов: Результаты параллельных операций автоматически объединяются.
// - Использование общего пула потоков: Для выполнения операций используется общий пул потоков ForkJoinPool.
// - Порядок выполнения: В параллельных потоках порядок выполнения операций может быть непредсказуемым.

public class ParallelStreamExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Параллельная обработка списка чисел для вычисления квадратов
        List<Integer> squares = numbers.parallelStream()
                .map(n -> {
                    System.out.println("Thread: " + Thread.currentThread().getName() + " processing " + n);
                    return n * n;
                })
                .collect(Collectors.toList());

        System.out.println("Squares: " + squares);
    }
}
