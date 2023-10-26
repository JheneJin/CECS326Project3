import java.util.Random;

public class westVill implements Runnable
{
   int pplNum;
   public Thread thread;
   String [] op = {" drinking water ", " talking to the boys ", " working out ", " asking for directions", " looking for baddies"};
   Random randOp = new Random();

   public static int randSecs(){
      Random r = new Random();
      int rand = r.nextInt(3000);
      return rand;
   }
  
   public westVill(int pplIndex){
      pplNum = pplIndex;
      //this implements the Runnable for the thread
      thread = new Thread(this);
      thread.start();
   }

   public void drive(){
      try{
         Thread.sleep(randSecs());
         System.out.println("West Villager " + pplNum + " is driving on the road");
         System.out.println("West Villager " + pplNum + op[randOp.nextInt(op.length)]);


      }
      catch(InterruptedException e){
         System.out.println("Error happened.");
      }
   }

   public void run(){
      drive();
      System.out.println("West Villager " + pplNum + " has finished the exchange");
   }
}
