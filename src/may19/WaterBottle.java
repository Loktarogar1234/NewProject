package may19;

public class WaterBottle {
    private String brand;
    private boolean empty;

    public static void main(String[] args) {
        WaterBottle wb = new WaterBottle();
        System.out.print(wb.empty);
        System.out.print(", " + wb.brand);
    }
}