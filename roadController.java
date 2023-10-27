import java.util.concurrent.Semaphore;
import java.util.Random;

/**
 * RoadController.java
 *
 *
 */
 
public class roadController{  
    private static  int carLimit = 1;
    private static Semaphore semaphore = new Semaphore(carLimit);
    private static boolean villTurn = new Random().nextBoolean();
   public static void main(String args[]){
      eastVill [] eastPpl = new eastVill[6];
      westVill [] westPpl = new westVill[6];
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
