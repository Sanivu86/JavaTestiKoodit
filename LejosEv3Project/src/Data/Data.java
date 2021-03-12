package Data;

/**
 * 
 * @author Jenni Lehtonen, Liisa Vuorenmaa, Alina Rouvinen, Sanna Nieminen-Vuorio
 * 
 * Anturit tallentavat tiedot Data-luokan staattisiin muuttujiin
 *
 */
public class Data {
	
	/**
	 * K‰ytet‰‰n while-looppien ehtona
	 */
	public static boolean shouldRun = true;  
	/**
	 * Touch-sensorin ehto. Asetetaan trueksi, jos anturi aktivoituu
	 */
	public static boolean isTouch = false;  
	/**
	 * UltraSensor-luokassa ultrasensori mittaa et‰isyyden ja tallettaa sen t‰h‰n
	 */
	public static float range = 100; 
	/**
	 * V‰risensorilta tuleva v‰riarvo, joka on kerrottu 1000:lla
	 */
	public static float colorline = 45;  
	/**
	 * Tiedot tulevat ColorSensor-luokasta, v‰risensorin hakema v‰riID, 0=punainen
	 */
	public static int currentColor = 7;  
	/**
	 * Lis‰t‰‰n lukemaa, kun robotti havaitsee esteen
	 */
	public static int counter = 0; 
	
	/**
	 * Robotin renkaiden nopeudet eri tilanteissa:
	 */
	public static final int AVARAGEVELOCITY = 30;
	public static final int SLOWDOWN = 20;
	public static final int STOPDRIVING = 0;
	public static final int ANOTHERTIREGOSLOWER = 5;
	public static final int ANOTHERTIREGOFASTER= 25;

}
