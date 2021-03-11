package App;

import java.io.File;

import Data.ColorSensor;
import Data.Data;
import Data.LoppuKaneetti;
import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;

public class Testaus {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LoppuKaneetti loppu = new LoppuKaneetti();
		
		Thread loppuThread = new Thread(loppu);
		
		loppuThread.start();
		Button.waitForAnyPress();


}
}
