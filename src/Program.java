interface Drivable {
    void drive();
}

class Car implements Drivable {
    @Override
    public void drive() {
        System.out.println("Машина едет");
    }
}

class Programma {
    public static void main(String[] args) {
        System.out.println("\nПривет Мир!\n");

        hello();
        welcome();

        for (int i = 1; i <= 5; i++) {
            System.out.println("i = " + i);
        }

        // Массивы
        int[] numbers = new int[5];
        numbers[0] = 10;
        numbers[1] = 20;

        int[] numbers1 = {10, 20, 30, 40, 50};

        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        String greeting = "Hello, World!";
    }

    static void hello() {
        System.out.println("Hello World");
    }

    static void welcome() {
        System.out.println("Welcome 2 Java");
    }
}
