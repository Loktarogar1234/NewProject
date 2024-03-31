interface Displayable { //интерфейс с одним методом
    void displayClassName();
}

class Class1 implements Displayable { // реализация интерфейса в первом классе
    @Override
    public void displayClassName() {
        System.out.println("Class1"); //?
    }
}

class Class2 extends Class1 { // второй класс наследует первому
    @Override
    public void displayClassName() {
        System.out.println(Class2.class.getSimpleName());
    }
}

class Class3 extends Class2 { // третий класс наследует второму
    @Override
    public void displayClassName() {
        System.out.println(Class3.class.getSimpleName());
    }
}

public class NewClass {
    public static void main(String[] args) { //создаем кассы и выводим их имена
        Class1 a = new Class1();
        a.displayClassName();

        Class2 b = new Class2();
        b.displayClassName();

        Class3 c = new Class3();
        c.displayClassName();


        int i = 0;
        while (i < 3) {
            for (int j = 0; j < 5; j++) {
                if (j == 3) { //Стоп если j=3
                    break;
                }
                for (int k = 0; k < 3; k++) {
                    if (k == j) { //Продолжаем если k равен j
                        continue;
                    }
                    //System.out.println("i=" + i + ", j=" + j + ", k=" + k);
                    //перебор всех вариантов 3 х 3
                }
            }
            i++;
        }


        int month = 4;
        switch (month) {
            case 1:
                System.out.println("\nянварь\n");
                break;
            case 2:
                System.out.println("\nфевраль\n");
                break;
            default:
                System.out.println("\nлюбой другой месяц\n");
        }


        int num3 = (int) (Math.random() * 201 - 100);
        if (num3 == 0) {
            System.out.println("Число " + num3);
        } else {
            if (num3 % 2 == 0) {
                System.out.print("Число " + num3 + " чётное и ");
            } else {
                System.out.print("Число " + num3 + " нечётное и ");
            }
            if (num3 > 0) {
                System.out.println("положительное");
            } else {
                System.out.println("отрицательное");
            }
        }
    }
}


