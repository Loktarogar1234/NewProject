package exeptions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Exception3 {
    public static void main(String[] args) {

        try {
            readfile();
        } catch (FileNotFoundException e) {
            //throw new RuntimeException(e); // аналогичен реализации предыдущего файла
            System.out.println("Обработка исключения. ERROR: File not found");
        }
        System.out.println("выполнение кода после try & catch");
    }

    public static void readfile() throws FileNotFoundException { //перебрасывает исключение выше
        File file = new File("c:\\1234.txt"); //файл 123.txt
        Scanner scanner = new Scanner(file);
        System.out.println(scanner.nextLine());
    }
}
