package Exeptions;

class MyException extends Exception {
    public MyException() {

    }
}

public class Main {
    public static void main(String[] args) {
        try {
            checkValue(-1);
        } catch (MyException e) {
            System.out.println("ERROR: значение должно быть положительным");
        }
    }

    public static void checkValue(int value) throws MyException {
        if (value < 0) {
            throw new MyException();
        }
    }
}

