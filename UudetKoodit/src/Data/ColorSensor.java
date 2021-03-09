package Data;

 

import java.io.File;

 

import lejos.hardware.BrickFinder;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;
import lejos.robotics.SampleProvider;

 

public class ColorSensor implements Runnable{
    
    EV3ColorSensor sensor;
    SampleProvider colorProvider;
    

 

    @Override
    public void run() {
        // TODO Auto-generated method stub
        //Luodaan portti
                Port s1 = BrickFinder.getLocal().getPort("S1");
                
                //Luodaan sensori
                sensor = new EV3ColorSensor(s1);
                colorProvider = sensor.getRGBMode();
                Data.colorSample = new float[colorProvider.sampleSize()];
                
                //Äänitiedosto
                 File soundFileEnd = new File("C:/temp/loppu.wav");
        
        while(Data.shouldRun)
        {
            //Haetaan arvot
             colorProvider.fetchSample(Data.colorSample, 0);
             
             //Tarkistetaan onko punaista väriä
            
            Data.currentColor = sensor.getColorID();
             
             //Nukutetaan säie
             try
                {
                    Thread.sleep(1);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                    
                }
             
             //Printti harjoituksiin
             System.out.println("Colorsensor going");
             System.out.println(Data.colorSample[2]*1000);
             System.out.println(Data.currentColor);
        }

 

        
    }
    
    

 

}
