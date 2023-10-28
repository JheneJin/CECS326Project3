import java.util.Random;
import java.util.concurrent.Semaphore;

public class eastVill implements Runnable
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
  
   public eastVill(int pplIndex, Semaphore semaphore){
      pplNum = pplIndex;
      this.semaphore = semaphore;
      //this implements the Runnable for the thread
      thread = new Thread(this);
      thread.start();
   }

   public void drive() {
    try {
        //for the first essential villager
        //makes all the villagers sleep so not all the villagers will try to get the semapgore
        Thread.sleep(randSecs());
        // the villager acqiiores tjhe semaphore
        semaphore.acquire();
        
        //runs only when the boolean is false
        while (!roadController.villFlag()) {
            //if its not the right villagers turn, it will rellease the semaphore
            semaphore.release();
            //makes it sleep, so another villager who has the permission to acquire
            Thread.sleep(randSecs()); 
            semaphore.acquire();
        }

        //displays the east villlager driving and completing the task
        System.out.println("East Villager " + pplNum + " is driving on the road");
        //tells thread or villager to go sleep to simulate the road and task being done in real time
        Thread.sleep(randSecs()); 
        System.out.println("East Villager " + pplNum + op[randOp.nextInt(op.length)]);
        Thread.sleep(randSecs());
        System.out.println("East Villager " + pplNum + " has finished the exchange");
        Thread.sleep(10);
        //changes the boolean here so the counterpart villager can use the semaphore
        roadController.switchTurn();
        //releases the current village semaphore right afgter
        semaphore.release();
        
    } catch (InterruptedException e) {
        System.out.println("Error happened.");
    }
}

   public void run(){
      drive();
   }
}
