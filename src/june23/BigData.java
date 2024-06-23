package june23;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

public class BigData {
    public static void main(String[] args) {
        System.out.println("Текущая директория: " + System.getProperty("user.dir"));
        String fileName = "1GBFile.bin";
        File file = new File(fileName);
        if (file.exists()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Файл " + fileName + " уже существует. Хотите пересоздать его? (yes/no)");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("yes")) {
                create1GBFile(fileName);
            }
        } else {
            create1GBFile(fileName);
        }
        processFile(fileName);
    }

    // Создаем файл размером 1 ГБ
    private static void create1GBFile(String fileName) {
        long size = 1024L * 1024L * 1024L; // 1 GB
        byte[] buffer = new byte[1024]; // 1 KB buffer

        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            Random random = new Random();
            for (long i = 0; i < size / buffer.length; i++) {
                for (int j = 0; j < buffer.length; j++) {
                    buffer[j] = (byte) (random.nextInt(9) + 1); // Случайные цифры от 1 до 9
                }
                fos.write(buffer);
            }
                        System.out.println("Создан файл: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Чтение файла и обработка данных с использованием parallelStream
    private static void processFile(String fileName) {
        File file = new File(fileName);

        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] data = new byte[(int) file.length()];
            fis.read(data);

            // Измерение времени выполнения
            long startTime = System.currentTimeMillis();

            // Используем parallelStream для возведения каждого числа в кубический корень
            double[] results = IntStream.range(0, data.length)
                    .parallel()
                    .mapToDouble(i -> Math.cbrt(data[i]))
                    .toArray();

            long endTime = System.currentTimeMillis();

            System.out.println("Длинна массива: " + results.length + " символов");
            System.out.println("Затрачено времени: " + (endTime - startTime) + " мс");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
