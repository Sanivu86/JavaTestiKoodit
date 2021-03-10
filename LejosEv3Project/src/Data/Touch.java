package Data;

import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.SensorMode;

public class Touch implements Runnable {

	EV3TouchSensor sensor;
	 SampleProvider sp;
	 
	Data.sensorMode= sensor.getTouchMode();
	
	
	        sensor = new EV3TouchSensor(SensorPort.S3);
	        sp = sensor.getTouchMode();
	    

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(Data.shouldRun)
		{
			IsTouched();
			System.out.println("Touchsensor going");
		    
		}
		
		uss.close();
		
	}
	
	 public void IsTouched()
	    {
	       float [] sample = new float[sp.sampleSize()];

	       sp.fetchSample(sample, 0);

	       if (sample[0] == 0)
	           Data.isTouch==false;
	       else
	           Data.isTouch==true;
	    }
}
