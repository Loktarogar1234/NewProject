public class ifs {

    boolean isMoving = true;
    int currentSpeed = 20;
    void applyBrakes() {
        if (isMoving) //if true
            currentSpeed--; //decrement
    } else {
        System.err.println("The bicycle has already stopped!");
    }
}



