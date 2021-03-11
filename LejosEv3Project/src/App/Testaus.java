package App;

import java.io.File;

import Data.ColorSensor;
import Data.Data;
import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;

public class Testaus {

private String message;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Testaus testi = new Testaus();
		
		testi.setMessage("vittu");
		
		System.out.println(testi.getMessage());
		Button.waitForAnyPress();
		
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {

		if(message.contains("vittu"))
		{
			this.message = "Älä puhu rumia!";
		}
		
	else
	{
		this.message = message;
	}
		
	}

}
