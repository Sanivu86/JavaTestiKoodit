package Data;

 

import lejos.hardware.Sound;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import util.DATA;

import java.io.File;

 

public class UltraSensor implements Runnable{

 

        

 

            UltraSonicSensor uss = new UltraSonicSensor(SensorPort.S2);

 
            @Override
        	public void run() {
            while(Data.shouldRun)
            {
            	Data.range = uss.getRange();
                System.out.println("Ultrasensor going");
                System.out.println(Data.range);
                
            }
            
            
            
    
        }
}
