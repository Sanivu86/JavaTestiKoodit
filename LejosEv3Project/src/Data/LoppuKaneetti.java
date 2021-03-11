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
 * Tämä luokka on tehty ylimääräistä toimintoa varten, jotta pystyttiin näyttämään äänen soitto opettajalle.
 * Robotti meni jumiin äänitiedoston soitosta,joten emme laittaneet sitä radankiertoon
 *
 */
public class LoppuKaneetti implements Runnable{

	@Override
	public void run() {		
		
		/** Luodaan moottorit
		 * 
		 * Takaa katsottuna oikean pyörän moottori
		 */
		UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A); 
		
		/**Takaa katsottuna vasemman pyörän moottori
		 * 
		 */
		UnregulatedMotor motorD = new UnregulatedMotor(MotorPort.D);  
		
		/**Lipun moottori
		 * 
		 */
		EV3MediumRegulatedMotor motorB = new EV3MediumRegulatedMotor(MotorPort.B); 
		
		/**
		 * Robotti kääntyy nopesti 90 astetta vasemmalle, pysähtyy, sulkee pyörien moottorit
		 * heiluttaa lippua kaksi kertaa edestakaisin, sulkee lipun moottorin ja
		 * sanoo lopuksi äänitiedoston
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
