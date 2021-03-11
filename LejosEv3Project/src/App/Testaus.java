package App;

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

/**
 * 
 * @author Testaaja
 * T‰m‰ luokka on testej‰ varten, jotta voidaan testata eri s‰ikeit‰ yksitt‰in,
 * eik‰ tarvitse aina kommentoida main-luokassa muita pois.
 * T‰‰ll‰ voi testata myˆs toimintoja yksitt‰in.
 *
 */

public class Testaus {

	
	public static void main(String[] args) {
		
		/**
		 * T‰m‰ testi tehtiin, jotta saatiin n‰ytt‰‰ opettajalle,
		 * ett‰ ‰‰nentoistokin toimii
		 */
		
		LoppuKaneetti loppu = new LoppuKaneetti();
		
		Thread loppuThread = new Thread(loppu);
		
		loppuThread.start();
		
		Button.waitForAnyPress();


}
}
