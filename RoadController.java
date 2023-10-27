/**
 * RoadController.java
 *
 *
 */
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class RoadController
{
    private int pplCount;
    private int pplTracker;
    private Lock lock;
    private Condition condition;
    private char sideCrossing;
    private boolean inUse;

    RoadController(int pplCount) {
        this.pplCount = pplCount;
        this.pplTracker = 0;
        this.lock = new ReentrantLock();
        this.condition = lock.newCondition();
        this.sideCrossing = 'e';
        this.inUse = false;
    }

    public boolean sideValidation(char driverSide) {
        if (driverSide == sideCrossing) return true;

        return false;
    }

    public void useRoad(int pplId, char ogSide) {
         //locks the useRoad method
        lock.lock();
        try {
            while (inUse == true) {
                //person waits for the signal that the road is not being used
                condition.await();
            }
            
            //checks if person is from the side that is allowed to cross
            if (sideValidation(ogSide) == false) return;
            System.out.println(pplId + " " + sideValidation(ogSide));

            //marks road as in use
            inUse = true;
            //keeps track of which side is crossing
            roadOrganizer();
            
            //determines the side that is driving
            String side;
            if (ogSide == 'e') {
                side = "East";
            } else {
                side = "West";
            }
            
            //print villager who is driving
            System.out.println(side + " Villager " + pplId + " is driving on the road");

        } catch (InterruptedException e) {
            //catches any interuptions
            System.out.println(e);
        } finally {
            //after the person is done using the method, it unlocks it
            lock.unlock();
        }
    }

    public void exitRoad(int pplId, char villageSide, String action) {
        //locks the exitRoad method
        lock.lock();

        String side;
        if (villageSide == 'e') {
            side = "East";
        } else {
            side = "West";
        }

        try {
            System.out.println(side + " Villager " + pplId + action);
            //road is not in use, so signals to people it is free
            inUse = false;
            condition.signal();
        } finally {
            System.out.println(side + " Villager " + pplId + " has finished the exchange");
            //after the person is done using the method, it unlocks it
            lock.unlock();
        }
    }

    private void roadOrganizer() {
        //keeps track of how many people have crossed
        pplTracker++;
        
        //once number of villages has finished using road, it'll move to next part
        if (pplTracker != pplCount) return;
       
        //tracker counter is reset
        pplTracker = 0;
        
        //side allowed to cross is adjusted
        if (sideCrossing == 'e') {
            this.sideCrossing = 'w';
        } else if (sideCrossing == 'w') {
            this.sideCrossing = 'w';
        }
    }
}
