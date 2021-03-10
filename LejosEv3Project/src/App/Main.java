package App;
import Data.*;
import lejos.hardware.Button;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		//Luodaan v‰risensori ja tehd‰‰n s‰ie
		
		ColorSensor sensor1 = new ColorSensor();
		
		Thread sensorThread = new Thread(sensor1);
		
		//Tehd‰‰n ultra‰‰nisensori ja siit‰ s‰ie
		
		UltraSensor ultraSensor = new UltraSensor();
		
		Thread ultraThread = new Thread(ultraSensor);
		
		
		//Moottori s‰ikeen luonti
		
		Motor motor1 = new Motor();
		
		Thread motorThread = new Thread(motor1);
		
		
		//K‰ynnistet‰‰n s‰ikeet
      
	    //CountTime();

		//sensorThread.setPriority(8);
		//ultraThread.setPriority(4);
		sensorThread.start();
		ultraThread.start();
		motorThread.start();
		
		Button.waitForAnyPress();

	} //Mainin sulje

} //Classin sulje
