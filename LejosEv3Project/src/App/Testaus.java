package App;

import java.io.File;

import Data.ColorSensor;
import Data.Data;
import lejos.hardware.BrickFinder;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;

public class Testaus {
	
	

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ColorSensor sensor1 = new ColorSensor();
		Thread sensorThread = new Thread(sensor1);
	    
		sensorThread.start();
		
	}

}
