package App;
import java.io.File;

import Data.*;
import lejos.hardware.Audio;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

public class ValoTestiApp {

	public static void main(String[] args) {
		
		//Värisensorin luonti
		LightSensor sensor1 = new LightSensor();
		
		Thread sensorThread = new Thread(sensor1);
/*
		//Ultrapään luonti
		Distance ultraDistance = new Distance();
		
		Thread ultraThread = new Thread(ultraDistance);
		
		ultraThread.start(); */
		sensorThread.start();
		//sensor1.LaskeAika();


	}

}
