
public class Main {

    public static void main(String args[]){
        //number of people in each village
        int pplCount = 2;

        RoadController roadControl = new RoadController(pplCount);

        //creating arrays for each east and west villages
        eastVill [] eastPpl = new eastVill[pplCount];
        westVill [] westPpl = new westVill[pplCount];

        //filling each array with the people
        for (int i = 0; i < pplCount; i++){
            eastPpl[i] = new eastVill(i, roadControl);
            westPpl[i] = new westVill(i, roadControl);
        }
    }   
}
