package Exeptions;

import java.io.IOException;
import java.util.Scanner;

public class Exeption5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //вводим числа в строку
        while (true) {
            int x = Integer.parseInt(scanner.next()); //преобразуем вводимое в целое число в переменную
            if (x != 0) { //если юзер водит чтото отличное от 0, то выбрасывается исключение
                try {
                    throw new IOException();
                } catch (IOException e) {
                    System.out.println("Введено значение, отличное от 0");
                    return;
                }
            }
        }
    }
}
