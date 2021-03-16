package Data;

import lejos.hardware.Sound;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.Color;
import lejos.utility.Delay;

public class Motor implements Runnable{

		 /**
		* Luodaan moottorit
		*/
	/**
	 * Takaa katsottuna oikean pyˆr‰n moottori
	 */
	UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);
	/**
	 * Takaa katsottuna vasemman pyˆr‰n moottori
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
				* S‰ikeen nukuttaminen
				*/
				try {
				Thread.sleep(1);
				}
				catch (InterruptedException e)
				{
				e.printStackTrace();
				}
				/**
				* Esteenkierto-metodin kutsuminen, kun esteeseen alle 25cm
				*/
				if (Data.range <= 0.25)
				{
					obstacle();
				}
				/**
				* Robotin sensorin koskettaessa esinett‰, peruutus
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
				* Kun sensori havaitsee valkoista, k‰‰nnyt‰‰n vasemmalle
				*/
				if(Data.colorline > 70)
				{
					motorA.setPower(25);
					motorD.setPower(0);
					motorA.forward();
					motorD.forward();
				}
				/**
				* Kun sensori havaitsee mustaa, k‰‰nnyt‰‰n oikealle
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
				* Kun sensori havaitsee olevansa viivalla, menn‰‰n suoraan
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
				* Ohjelma laskee ja tulostaa lopullisen radan suorittamiseen k‰ytetyn ajan sek‰ ilmoittaa radan aikana havaittujen esteiden m‰‰r‰n
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
			* Esteen kohdatessa counterin m‰‰r‰ kasvaa
			*/
			Data.counter++;
			/**
			* Esteen kohdatessa soitetaan ‰‰ni
			*/
			Sound.systemSound(false, 3);
			/**
			* Robotti k‰‰ntyy pois viivalta
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
			* Robotti k‰‰ntyy takaisin viivalle
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
		* Ohjelman lopetus-metodi, jossa suljetaan moottorit ja heilutetaan lippua ‰‰nen kanssa
		*/
		public void end()
		{
			motorA.setPower(0);
			motorD.setPower(0);
			motorA.close();
			motorD.close();
			motorB.setSpeed(100);
			/**
			* Lippu pyˆrii edestakas
			*/
			motorB.rotate(45);
			motorB.rotate(-45);
			motorB.rotate(45);
			motorB.rotate(-45);
			motorB.rotate(45);
			motorB.rotate(-45);
			/**
			* ƒ‰nen soittaminen
			*/
			Sound.systemSound(false, 3);
			Delay.msDelay(40);
			Sound.twoBeeps();
			Delay.msDelay(40);
			Sound.systemSound(false, 3);
			motorB.close();
			Data.shouldRun = false;
		}
}