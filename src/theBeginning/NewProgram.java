package theBeginning;

import java.util.Scanner;
public class NewProgram  {
    public static void main(String[] args) {
        
        System.out.println("\nДобро пожаловать на наш опрос! Пожалуйста ответьте на следующие вопросы о себе,");
        System.out.println("только честно! После каждого ответа не забывайте нажимать ENTER.");

        Scanner scan = new Scanner(System.in);
        System.out.println("Введите ваше имя (латинскими буквами)");
        String name = scan.nextLine();

        System.out.println("Введите ваш возраст");
        int age = scan.nextInt();

        System.out.println("Введите ваш вес в килограммах");
        int weight = scan.nextInt();

        System.out.println("Введите ваш рост в сантиметрах");
        int height = scan.nextInt();
        
        System.out.println("Какой ваш пол? (мужской 1, женской 0)");
        int gender = scan.nextInt();
        System.out.println(gender);
        scan.close();

        System.out.println("\nИтак, вот что мы о тебе узнали.");
        System.out.println("Тебя зовут " + name + ". Тебе " + age + " лет. Твой рост " + height + " сантиметров. Твой вес " + weight + " килограмм.");
        
        if(gender == 1 && age < 18){
            System.out.println("Ты мальчик");
        } else if(gender == 1 && age > 18){
            System.out.println("Ты мужчина");
        } else if(gender == 0 && age < 18){
            System.out.println("Ты девочка");
        } else {
            System.out.println("Ты женщина");
        }
    }   
}