package Data;

import java.io.File;
import lejos.hardware.Sound;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

/**
 * 
 * @author Sanna Nieminen-Vuorio
 * T�m� luokka on tehty ylim��r�ist� toimintoa varten, jotta pystyttiin n�ytt�m��n ��nen soitto opettajalle.
 * Robotti meni jumiin ��nitiedoston soitosta,joten emme laittaneet sit� radankiertoon
 *
 */
public class LoppuKaneetti implements Runnable{

	@Override
	public void run() {		
		
		/** Luodaan moottorit
		 * 
		 * Takaa katsottuna oikean py�r�n moottori
		 */
		UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A); 
		
		/**Takaa katsottuna vasemman py�r�n moottori
		 * 
		 */
		UnregulatedMotor motorD = new UnregulatedMotor(MotorPort.D);  
		
		/**Lipun moottori
		 * 
		 */
		EV3MediumRegulatedMotor motorB = new EV3MediumRegulatedMotor(MotorPort.B); 
		
		/**
		 * Robotti k��ntyy nopesti 90 astetta vasemmalle, pys�htyy, sulkee py�rien moottorit
		 * heiluttaa lippua kaksi kertaa edestakaisin, sulkee lipun moottorin ja
		 * sanoo lopuksi ��nitiedoston
		 */
		
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
		motorB.rotate(45);  
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
