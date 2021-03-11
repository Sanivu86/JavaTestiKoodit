package Data;

import java.io.File;
import lejos.hardware.Sound;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

/** 
 * 
 */

public class LoppuKaneetti implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A); //Takaa katsottuna oikean pyörän moottori
		UnregulatedMotor motorD = new UnregulatedMotor(MotorPort.D);  //Takaa katsottuna vasemman pyörän moottori
		EV3MediumRegulatedMotor motorB = new EV3MediumRegulatedMotor(MotorPort.B); //Lipun moottori
		
		motorA.setPower(80);
		motorD.setPower(0);
		motorA.forward();
		motorD.forward();
		Delay.msDelay(1000);
		motorA.setPower(0);
		motorD.setPower(0);
		motorA.close();
		motorD.close();
		motorB.setSpeed(100);
		motorB.rotate(45);  //lippu pyörii edestakas. Voi pyörittää myös esim 720 eli kaksi kierrosta
		motorB.rotate(-45);
		motorB.rotate(45);  
		motorB.rotate(-45);
		motorB.rotate(45);  
		motorB.rotate(-45); 
		motorB.setSpeed(0);
		motorB.close();
		Delay.msDelay(1);
		Sound.playSample(new File("pentti.wav"), 100);
		
	}

}
