package Data;


import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;

import java.io.File;

public class UltraSensor implements Runnable{
	
	UltraSonicSensor uss = new UltraSonicSensor(SensorPort.S2);

		@Override
		public void run() {

			while(Data.shouldRun)
			{
			  //Nukutetaan säie
			    try
				{
				Thread.sleep(1);
				}
				catch(InterruptedException e)
				{
				e.printStackTrace();
						
				}
				Data.range = uss.getRange();
				System.out.println("Ultrasensor going");
			    System.out.println(Data.range);

			}
			
			uss.close();

		}
}
