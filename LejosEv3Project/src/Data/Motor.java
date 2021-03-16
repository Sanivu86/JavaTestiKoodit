package Data;

import lejos.hardware.Sound;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.Color;
import lejos.utility.Delay;

/**
 * 
 * @author Alina Rouvinen, Jenni Lehtonen, Liisa Vuorenmaa, Sanna Nieminen-Vuorio
 * 
 * Luokka ohjaa moottoreiden toimintaa. Luokka hakee sensoreiden välittämät arvot
 * Data-luokasta ja suorittaa toimintoja niiden mukaan.
 *
 */

public class Motor implements Runnable{

		 /**
		* Luodaan moottorit
		*/
	/**
	 * Takaa katsottuna oikean pyörän moottori
	 */
	UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);
	/**
	 * Takaa katsottuna vasemman pyörän moottori
	 */
	UnregulatedMotor motorD = new UnregulatedMotor(MotorPort.D); 
	/**
	 * Lipun moottori
	 */
	EV3MediumRegulatedMotor motorB = new EV3MediumRegulatedMotor(MotorPort.B); 
	
		@Override
		public void run() {
			// TODO Auto-generated method stub
			/**
			* Ajanlaskun aloittaminen
			*/
			long tm = System.currentTimeMillis();
			try {
			while (Data.shouldRun) {
				/**
				* Säikeen nukuttaminen
				*/
				try {
				Thread.sleep(1);
				}
				catch (InterruptedException e)
				{
				e.printStackTrace();
				}
				/**
				* Esteenkierto-metodin kutsuminen, kun esteeseen 25cm tai alle
				*/
				if (Data.range <= 0.25)
				{
					obstacle();
				}
				/**
				* Robotin sensorin koskettaessa esinettä, peruutus
				*/
				if(Data.isTouch)
				{
					motorA.backward();
					motorD.backward();
					motorA.setPower(30);
					motorD.setPower(30);
					Delay.msDelay(2000);
				}
				
				/**
				* Kun sensori havaitsee valkoista, käännytään vasemmalle
				*/
				if(Data.colorline > 70)
				{
					motorA.setPower(25);
					motorD.setPower(0);
					motorA.forward();
					motorD.forward();
				}
				/**
				* Kun sensori havaitsee mustaa, käännytään oikealle
				*/
				else if(Data.colorline < 30)
				{
					motorA.setPower(0);
					motorD.setPower(25);
					motorA.forward();
					motorD.forward();
				}
				/**
				* Kun sensori havaitsee punaista, robotti suorittaa lopetusohjelman
				*/
				else if(Data.currentColor == Color.RED)
				{
					end();
				}
				
				/**
				* Kun sensori havaitsee olevansa viivalla, mennään suoraan
				*/
				else if(Data.colorline < 70 && Data.colorline > 30 && Data.range > 0.25)
				{
					motorA.setPower(20);
					motorD.setPower(20);
					motorA.forward();
					motorD.forward();
				}
				}
				/**
				* Ohjelma laskee ja tulostaa lopullisen radan suorittamiseen käytetyn ajan 
				* sekä ilmoittaa radan aikana havaittujen esteiden määrän
				*/
				} finally {
					tm = System.currentTimeMillis()-tm;
					tm=tm/1000;
					System.out.println("It has taken time to go around the track " + tm + " second");
					System.out.println("Obstacles detected " + Data.counter);
			}
		}
		/**
		* Esteenkierto-metodi
		*/
		public void obstacle()
		{
			/**
			* Hidastetaan
			*/
			motorA.setPower(Data.SLOWDOWN);
			motorD.setPower(Data.SLOWDOWN);
			/**
			* Esteen kohdatessa counterin määrä kasvaa
			*/
			Data.counter++;
			/**
			* Esteen kohdatessa soitetaan ääni
			*/
			Sound.systemSound(false, 3);
			/**
			* Robotti kääntyy pois viivalta
			*/
			motorA.setPower(Data.ANOTHERTIREGOSLOWER);
			motorD.setPower(Data.ANOTHERTIREGOFASTER);
			Delay.msDelay(2000);
			/**
			* Robotti menee hetken suoraan
			*/
			motorA.setPower(Data.SLOWDOWN);
			motorD.setPower(Data.SLOWDOWN);
			Delay.msDelay(3500);
			/**
			* Suoristetaan robotti
			*/
			motorD.setPower(Data.ANOTHERTIREGOSLOWER);
			motorA.setPower(Data.ANOTHERTIREGOFASTER);
			Delay.msDelay(2800);
			/**
			* Robotti menee hetken suoraan
			*/
			motorA.setPower(Data.SLOWDOWN);
			motorD.setPower(Data.SLOWDOWN);
			Delay.msDelay(4000);
			/**
			* Robotti kääntyy takaisin viivalle
			*/
			motorD.setPower(Data.ANOTHERTIREGOSLOWER);
			motorA.setPower(Data.ANOTHERTIREGOFASTER);
			Delay.msDelay(2800);
			/**
			* Robotti menee hetken suoraan
			*/
			motorA.setPower(Data.SLOWDOWN);
			motorD.setPower(Data.SLOWDOWN);
			Delay.msDelay(3800);
			/**
			* Korjataan robotin kulmaa viivalle tullessa takaisin
			*/
			motorD.setPower(Data.ANOTHERTIREGOFASTER);
			motorA.setPower(Data.ANOTHERTIREGOSLOWER);
			Delay.msDelay(2000);
			motorA.setPower(Data.SLOWDOWN);
			motorD.setPower(Data.SLOWDOWN);
		}
		/**
		* Ohjelman lopetus-metodi, jossa suljetaan moottorit ja heilutetaan lippua äänen kanssa
		*/
		public void end()
		{
			/**
			 * Suljetaan pyörien moottorit
			 */
			motorA.setPower(0);
			motorD.setPower(0);
			motorA.close();
			motorD.close();
			/**
			* Lippu pyörii edestakas
			*/
			motorB.setSpeed(100);
			motorB.rotate(45);
			motorB.rotate(-45);
			motorB.rotate(45);
			motorB.rotate(-45);
			motorB.rotate(45);
			motorB.rotate(-45);
			/**
			* Äänen soittaminen
			*/
			Sound.systemSound(false, 3);
			Delay.msDelay(40);
			Sound.twoBeeps();
			Delay.msDelay(40);
			Sound.systemSound(false, 3);
			motorB.close();
			/**
			 * Asetetaan staattinen shouldRun-boolean falseksi, jolloin tämän ja muiden luokkien 
			 *  while-loopit loppuvat
			 */
			Data.shouldRun = false;
		}
}