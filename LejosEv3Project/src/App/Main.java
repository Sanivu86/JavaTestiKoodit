package App;
import Data.*;
import lejos.hardware.Button;
import lejos.utility.Delay;

public class Main {

//Private-muuttuja, jolla voidaan v‰litt‰‰ k‰ytt‰j‰lle viesti
private String message;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Kirjoitetaan k‰ytt‰j‰lle viesti, jota k‰ytet‰‰n alussa
		
		Main main = new Main();
		main.setMessage("Welcome");
		System.out.println(main.getMessage());
		
		//Luodaan v‰risensori ja tehd‰‰n s‰ie
		//K‰ynnistet‰‰n s‰ie ja pidet‰‰n tauko, jotta v‰risensori varmasti k‰ynnistyy ensimm‰isen‰
		
		ColorSensor colorsensor1 = new ColorSensor();
		Thread colorsensorThread = new Thread(colorsensor1);
		colorsensorThread.start();
		Delay.msDelay(2000);
		
		//Tehd‰‰n ultra‰‰nisensori ja siit‰ s‰ie
		
		UltraSensor ultraSensor = new UltraSensor();
		
		Thread ultraThread = new Thread(ultraSensor);
		
		
		//Moottori s‰ikeen luonti
		
		Motor motor1 = new Motor();
		Thread motorThread = new Thread(motor1);
		
		
		//Touchin s‰ikeen luonti
		
		Touch touch1 = new Touch();
		
		Thread touchThread = new Thread(touch1);
		
		
		//Asetetaan s‰ikeille prioriteetit

		colorsensorThread.setPriority(8);
		ultraThread.setPriority(4);
		motorThread.setPriority(4);
		touchThread.setPriority(4);
		
		//K‰ynnistet‰‰n loput s‰ikeet
		
		touchThread.start();
		ultraThread.start();
		motorThread.start();
		
		Button.waitForAnyPress();

	} //Mainin sulje

	
	//Message-muuttujalle tehty getterit ja setterit
	
	public String getMessage() {
		return message;
	}

	//Estet‰‰n ohjelmoijaa laittamasta rumia viestej‰ k‰ytt‰j‰lle
	
	public void setMessage(String message) {
		if(message.contains("Tyhm‰") || message.contains("tyhm‰") || message.contains("Idiot") || message.contains("idiot"))
		{
			this.message = "Ohjelmoija yritt‰‰ haukkua sinua";
		}
		
		else
		{
			this.message = message;
		}
	}
} //Classin sulje
