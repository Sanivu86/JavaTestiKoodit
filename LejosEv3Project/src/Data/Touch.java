package Data;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.SampleProvider;
/**
 * @author liisa
 * T‰m‰ luokka komentaa Touch- sensoria, luokka k‰ytt‰‰ runnable rajapintaa ja sis‰lt‰‰ run- metodin ja isTouched-metodin
 * Touch- sensori saa arvot Data-luokan stattisesta muuttujasta isTouch
 * Touch- sensoriin liittyv‰ toiminto asetetaan moottoriluokasta
 * Touch- sensoria k‰ytet‰‰n s‰ikeen‰
 */
public class Touch implements Runnable {

	EV3TouchSensor sensor;
	SampleProvider sp;

	
	@Override
	public void run() {
		// TODO Auto-generated method stub
	/**
	 * Luodaan Touch- sensori
	 */
		sensor = new EV3TouchSensor(SensorPort.S3);
		sp = sensor.getTouchMode();
		/**
		 * While- Looppi touch- sensorin k‰ynniss‰ pit‰miseen, jossa k‰ytet‰‰n ehtona Data-luokan shouldRun muuttujaa ja kutsutaan isTouched- metodia.
		 */
		while(Data.shouldRun)
		{
			IsTouched();
			System.out.println("Touchsensor going");
		    
		}
		/**
		 * Suljetaan touch-sensori
		 */
		sensor.close();
		
	}
		/**
		 * IsTouched- metodi jossa verrataan Touch-sensorin saamia arvoja
		 */
	 public void IsTouched()
	    {
	       float [] sample = new float[sp.sampleSize()];

	       sp.fetchSample(sample, 0);

	       if (sample[0] == 0)
	           Data.isTouch=false;
	       else
	           Data.isTouch=true;
	    }
}
