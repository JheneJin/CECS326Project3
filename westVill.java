import java.util.Random;
import java.util.concurrent.Semaphore;

public class westVill implements Runnable
{
   //initialize the pplNum, semaphore, thread, and array of task for villagers, and random variable
   int pplNum;
   public Semaphore semaphore;
   public Thread thread;
   String [] op = {" drinking water ", " talking to the boys ", " working out ", " asking for directions", " looking for baddies"};
   Random randOp = new Random();

   //creates random amount of secs for sleep
   public static int randSecs(){
        Random r = new Random();
        int rand = r.nextInt(3000);
        return rand;
   }
  
   //constructor for westVill starts the thread
   public westVill(int pplIndex, Semaphore semaphore){
        //each villager is given an id and the semaphore 
        pplNum = pplIndex;
        this.semaphore = semaphore;
        //this implements the Runnable for the thread
        thread = new Thread(this);
        thread.start();
   }

  public void drive() {
    try {
        //makes all the villagers sleep so not all the villagers will try to get the semapgore
        Thread.sleep(randSecs());
        // the villager acquires the semaphore
        semaphore.acquire();

        //runs only when the boolean value is true
        while (roadController.villFlag()) {
            //if its not the right villagers turn, it will rellease the semaphore
            semaphore.release();
            //makes it sleep, so another villager who has the permission to acquire
            Thread.sleep(randSecs()); 
            //then villager tries again
            semaphore.acquire();
        }

        //displays the west villlager driving and completing the task
        System.out.println("West Villager " + pplNum + " is driving on the road");
        //tells thread or villager to go sleep to simulate the road and task being done in real time
        Thread.sleep(randSecs()); 
        System.out.println("West Villager " + pplNum + op[randOp.nextInt(op.length)]);
        Thread.sleep(randSecs());
        System.out.println("West Villager " + pplNum + " has finished the exchange");
        Thread.sleep(10);
        //changes the boolean here so the counterpart villager can use the semaphore
        boolean randSwitch = new Random().nextBoolean();
        if (randSwitch) roadController.switchTurn();
        //releases the current village semaphore right afgter
        semaphore.release();

    } catch (InterruptedException e) {
        System.out.println("Error happened.");
    }
}

   //runs the drive function
   public void run(){
        while(true) {
            drive();
        }
   }
}
