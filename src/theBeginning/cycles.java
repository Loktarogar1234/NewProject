package theBeginning;

public class cycles {
    public static void main(String[] args) {
        int value = 0;
        int myInt = 15;

        //while loop
            while(value<5){
            System.out.println("Hello");
            value++;
        }

        //for loop
        for(int i = 10; i >= 0; i--){           //(счетчик; условие выполнения; действие со счетчиком))
           System.out.println("Hello " + i);
           }

        //if loop
        
        if(myInt < 15){
                System.out.println("верно ");
            } else if(myInt > 20){
                System.out.println("неверно ");
            } else{
                System.out.println("ни один из предыдущих вариантов");
            }
    }
}