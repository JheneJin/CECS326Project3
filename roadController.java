import java.util.concurrent.Semaphore;
import java.util.Random;

/**
 * RoadController.java
 *
 *
 */
 
public class RoadController {
    //determines how many cars can use the road at a time
    private static  int carLimit = 1;
    //creates semaphore
    private static Semaphore semaphore = new Semaphore(carLimit);
    //determines which village can use road
    private static boolean villTurn = new Random().nextBoolean();


    public static void main(String args[]){
        //creates array for each village 
        eastVill [] eastPpl = new eastVill[6];
        westVill [] westPpl = new westVill[6];
        //each village array is filled
        for (int i = 1; i < 6; i++){
            eastPpl[i] = new eastVill(i, semaphore);
            westPpl[i] = new westVill(i, semaphore);
        }
   }

    //returns a boolean for the villager's turn
    public static boolean villFlag() {
        return villTurn;
    }

    //changes the flag to false
    public static void switchTurn() {
        villTurn = ! villTurn;
    }
}
