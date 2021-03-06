package Data;

import java.util.Timer;
import lejos.utility.Delay;

/**
 * Moottoreille ohjelmoidut metodit, kuten viivan seuranta ja kääntymiset
 */

public class MotorFunctions extends MotorInitializer{

	public void avoidObstacle() {

		/**
		 * Toisen esteen kohdalla ohjelma pysähtyy
		 */
		if (stop) {
			motorV.setPower(0);
			motorO.setPower(0);
			Main.Program(false);
			return;
		}

		/**
		 *  Käännytään pois radalta
		 */
		motorV.setPower(85);
		motorO.setPower(25);
		Delay.msDelay(550);

		/**
		 * Estettä kierretään kunnes värisensori haistaa viivan
		 */
		while (Main.programRunning) {
			motorV.setPower(50);
			motorO.setPower(90);

			if (color.getRed() < 0.15f)
				break;
		}

		/**
		 * Korjaa viivalle saapumisen kulmaa
		 */
		while (Main.programRunning) {
			motorV.setPower(30);
			motorO.backward();
			motorO.setPower(30);

			if (color.getRed() > 0.15f)
				break;
		}

		Stop();
	}

	/**
	 * Funktio, joka suorittaa viivan seuraamista
	 * @param value värisensorilta saatu arvo
	 */
	public void followLine(float value) {

		/**
		 * Oikean renkaan moottorin pyörimissuunta eteenpäin
		 */
		motorO.forward();
		
		/**
		 * Vasemman renkaan moottorin pyörimissuunta eteenpäin
		 */
		motorV.forward();
		
		/**
		 * Oikean renkaan moottorin pyörimisteho prosentteina (0-100)
		 */
		motorO.setPower(60);
		
		/**
		 * Vasemman renkaan moottorin pyörimisteho prosentteina (0-100)
		 */
		motorV.setPower(60);

		/**
		 * Kääntymismetodien kutsut riippuen siitä, mitä värisensori havaitsee
		 */
		if (value > threshHigh) {
			tiukkaVasen();
		} else if (value < threshLow) {
			tiukkaOikea();
		} else if (value > threshLow && value < threshMedLow) {
			loivaOikea();
		} else if (value > threshMedLow && value < threshMedHigh) {
			suoraan();
		} else if (value > threshMedHigh && value < threshHigh) {
			loivaVasen();
		}		
	}

	/**
	 * Tiukasti vasempaan kääntymisen funktio
	 */
	public void tiukkaVasen() {
		motorO.setPower(85);
		motorV.setPower(5);
	}

	/**
	 * Tiukasti oikeaan kääntymisen funktio
	 */
	public void tiukkaOikea() {
		motorO.setPower(5);
		motorV.setPower(90);
	}

	/**
	 * Loivasti vasempaan kääntymisen funktio
	 */
	public void loivaVasen() {
		motorA.setPower(30);
		motorD.setPower(10);
	}

	/**
	 * Loivasti oikeaan kääntymisen funktio
	 */
	public void loivaOikea() {
		motorA.setPower(10);
		motorD.setPower(30);
	}

	/**
	 * Optimaalisesti suoraan kulkemisen funktio
	 */
	public void suoraan() {
		motorO.setPower(90);
		motorV.setPower(90);
	}

	/**
	 * Pysähtymisen funktio
	 */
	public static void Stop() {

		timer = new Timer();
		timer.schedule(new java.util.TimerTask() {
			@Override
			public void run() {
				stop = true;
			}
		
		/**
		 * 2s jälkeen suoritetaan yllä oleva funktio
		 */
		}, 2000
		);
	}
}
