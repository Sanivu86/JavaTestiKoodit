package Data;

import lejos.robotics.Color;

public class Data {
	
	public static boolean shouldRun = true;
	public static boolean isTouch = false;
	public static float range = 100; //Tiedot tulevat UltraSensor-luokasta
	public static float colorline = 45;
	public static int colorRed = Color.RED;
	public static int currentColor = 71;  //Tiedot tulevat ColorSensor-luokasta
	public static int counter = 0; //Laskee esteet
	
	//Robotin renkaiden nopeudet eri tilanteissa:
	public static final int AVARAGEVELOCITY = 30;
	public static final int SLOWDOWN = 20;
	public static final int STOPDRIVING = 0;
	public static final int ANOTHERTIREGOSLOWER = 8;
	public static final int ANOTHERTIREGOFASTER= 25;

}
