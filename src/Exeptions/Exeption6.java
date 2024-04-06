package Exeptions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Exeption6 {

    public static void main(String[] args) {

        //Checked Exeptions - проверяемые исключения, возникают в процессе компиляции
        //Unchecked Exeptions - возникают при выполнении программы

        File file = new File("c:\\1234.txt"); //файл 123.txt

        /*Checked Exeptions
        try {
            Scanner scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }*/

        //Runtime Exeprion
        int a = 1/0;


    }
}
