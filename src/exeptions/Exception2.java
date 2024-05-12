package exeptions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Exception2 {
    public static void main(String[] args) {
        File file = new File("c:\\123.txt"); //файл 123.txt
        try { //сами ловим ошибки и обрабатываем исключение
            Scanner scanner = new Scanner(file);
            System.out.println(scanner.nextLine());
        } catch (FileNotFoundException e) { // е - переменная исключения
            //throw new RuntimeException(e); // аналогичен реализации предыдущего файла
            System.out.println("Обработка исключения. ERROR: File not found");
        }
        System.out.println("выполнение кода после try & catch");
    }
}
