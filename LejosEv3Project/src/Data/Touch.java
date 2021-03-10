package Data;

import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.SensorMode;

public class Touch implements Runnable {

	EV3TouchSensor sensor;
	
	Data.sensorMode= sensor.getTouchMode();

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
