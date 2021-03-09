package App;
import Data.*;

 

public class main {

 

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        

 

        //Luodaan v‰risensori ja tehd‰‰n s‰ie
        
        ColorSensor sensor1 = new ColorSensor();
        
        Thread sensorThread = new Thread(sensor1);
        
        //Tehd‰‰n ultra‰‰nisensori ja siit‰ s‰ie
        
        UltraSensor ultraSensor = new UltraSensor();
        
        Thread ultraThread = new Thread(ultraSensor);
        
        
        //Moottori
        
       /* Motor motor1 = new Motor();
        
        Thread motorThread = new Thread(motor1);*/
        
        
        //K‰ynnistet‰‰n s‰ikeet
      
        CountTime();
        ultraThread.start();
        sensorThread.start();
        //motorThread.start();
        

 

    }
    
    public static void CountTime()
    {
        long tm = System.currentTimeMillis();
        try {
            //T‰h‰n tulee metodi mink‰ aika lasketaan
        	Motor motor1 = new Motor();
            
            Thread motorThread = new Thread(motor1);
            motorThread.start();
        } finally {
            tm = System.currentTimeMillis()-tm;
            tm = tm/1000;
            System.out.println("it has taken time to go around the track " + tm + " s");
        }
    
    } 

 

    
    
}

