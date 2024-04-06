package Exeptions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Exeption1 {
    public static void main(String[] args) throws FileNotFoundException {
        // обрабатываем ошибки через FileNotFoundException по умолчанию, в красном тексте
        File file = new File("c:\\1234.txt"); //файл 123.txt
        Scanner scanner = new Scanner(file); //пытаемся считать файл, которого нет
    }

}
