package Data;

import lejos.robotics.Color;

public class Data {
	
	public static boolean shouldRun = true;  //K‰ytet‰‰n while-looppien ehtona
	public static boolean isTouch = false;  //Touch-sensorin ehto
	public static float range = 100; //Tiedot tulevat UltraSensor-luokasta
	public static float colorline = 45;  //V‰risensorilta tuleva v‰riarvo, joka on kerrottu 1000:lla
	public static int currentColor = 7;  //Tiedot tulevat ColorSensor-luokasta, v‰risensorin hakema v‰riID, 0=punainen
	public static int counter = 0; //Laskee esteet
	
	//Robotin renkaiden nopeudet eri tilanteissa:
	public static final int AVARAGEVELOCITY = 30;
	public static final int SLOWDOWN = 20;
	public static final int STOPDRIVING = 0;
	public static final int ANOTHERTIREGOSLOWER = 5;
	public static final int ANOTHERTIREGOFASTER= 25;

}
