import java.util.Random;
import java.util.concurrent.Semaphore;

public class westVill implements Runnable
{
   int pplNum;
   public Semaphore semaphore;
   public Thread thread;
   String [] op = {" drinking water ", " talking to the boys ", " working out ", " asking for directions", " looking for baddies"};
   Random randOp = new Random();

   public static int randSecs(){
      Random r = new Random();
      int rand = r.nextInt(3000);
      return rand;
   }
  
   public westVill(int pplIndex, Semaphore semaphore){
      pplNum = pplIndex;
      this.semaphore = semaphore;
      //this implements the Runnable for the thread
      thread = new Thread(this);
      thread.start();
   }

  public void drive() {
    try {
        Thread.sleep(randSecs());
        semaphore.acquire();

        while (RoadController.villFlag()) {
            semaphore.release();
            Thread.sleep(randSecs()); 
            semaphore.acquire();
        }

        System.out.println("West Villager " + pplNum + " is driving on the road");
        System.out.println("West Villager " + pplNum + op[randOp.nextInt(op.length)]);
        Thread.sleep(randSecs());
        System.out.println("West Villager " + pplNum + " has finished the exchange");
        RoadController.switchTurn();
        semaphore.release();
    } catch (InterruptedException e) {
        System.out.println("Error happened.");
    }
}

   public void run(){
      drive();
   }
}
