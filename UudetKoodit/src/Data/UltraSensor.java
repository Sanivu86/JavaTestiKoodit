package Data;

 

import lejos.hardware.Sound;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;

 

import java.io.File;

 

public class UltraSensor implements Runnable{

 

        @Override
        public void run() {

 

            UltraSonicSensor uss = new UltraSonicSensor(SensorPort.S2);

 

            
            while(Data.shouldRun)
            {
                Data.range = uss.getRange();
                System.out.println("Ultrasensor going");
                System.out.println(Data.range);
            
            }
            
    
        }
}
