/**
 * RoadController.java
 *
 *
 */

 
public class RoadController
{  
   public static void main(String args[]){
      eastVill [] eastPpl = new eastVill[6];
      westVill [] westPpl = new westVill[6];
      for (int i = 1; i < 6; i++){
         eastPpl[i] = new eastVill(i);
         westPpl[i] = new westVill(i);
      }
   }
}
