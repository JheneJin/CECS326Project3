import java.util.Random;

public class westVill implements Runnable
{
   int pplNum;
   RoadController road;
   public Thread thread;
   String [] op = {" drinking water ", " talking to the boys ", " working out ", " asking for directions", " looking for baddies"};
   Random randOp = new Random();
   private char villageSide = 'w';

   public static int randSecs(){
      Random r = new Random();
      int rand = r.nextInt(3000);
      return rand;
   }
  
   public westVill(int pplIndex, RoadController road){
      pplNum = pplIndex;
      this.road = road;
      //this implements the Runnable for the thread
      thread = new Thread(this);
      thread.start();
   }

   public void drive(){
      try{
         road.useRoad(pplNum, villageSide); 
         Thread.sleep(randSecs());
         String action = op[randOp.nextInt(op.length)];
         road.exitRoad(pplNum, villageSide, action);
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
