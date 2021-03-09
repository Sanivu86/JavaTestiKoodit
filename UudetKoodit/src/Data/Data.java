package Data;

 

import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;

 

public class Data {
    
    public static boolean shouldRun = true;
    public static float range = 100; //Tiedot tulevat UltraSensor-luokasta
    public static float[] colorSample = {30,50,75};  //Tiedot tulevat ColorSensor-luokasta
    public static int colorRed = Color.RED;
    public static int currentColor = 75;  //Tiedot tulevat ColorSensor-luokasta
    //public static  SensorMode sensorMode;

 

}
