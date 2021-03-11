package Data;

import java.io.File;

import lejos.hardware.Sound;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.Color;
import lejos.utility.Delay;

public class Motor implements Runnable{

	//Luodaan moottorit
	UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A); //Takaa katsottuna oikean pyˆr‰n moottori
	UnregulatedMotor motorD = new UnregulatedMotor(MotorPort.D);  //Takaa katsottuna vasemman pyˆr‰n moottori
	EV3MediumRegulatedMotor motorB = new EV3MediumRegulatedMotor(MotorPort.B); //Lipun moottori
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		long tm = System.currentTimeMillis();
	    try {
		while (Data.shouldRun) {
			
			//Nukutetaan s‰ie
			try {
			Thread.sleep(1);
			} 
			catch (InterruptedException e) 
			{
			e.printStackTrace();
			}
			
			if (Data.range <= 0.25) 
			{
				motorA.setPower(Data.SLOWDOWN);  //Hidastetaan
				motorD.setPower(Data.SLOWDOWN);
				Data.counter++; //Kun kohdataan este, counterin m‰‰r‰ kasvaa
				Sound.systemSound(false, 3); //Soitetaan ‰‰ni, kun kohdataan este
				motorA.setPower(Data.ANOTHERTIREGOSLOWER);  //K‰‰nnyt‰‰n pois viivalta
				motorD.setPower(Data.ANOTHERTIREGOFASTER);
				Delay.msDelay(1600);
				motorA.setPower(Data.AVARAGEVELOCITY);  //Hetki suoraa
				motorD.setPower(Data.AVARAGEVELOCITY);
				Delay.msDelay(1000);
				motorD.setPower(Data.ANOTHERTIREGOSLOWER);  //Suoristetaan
				motorA.setPower(Data.ANOTHERTIREGOFASTER);
				Delay.msDelay(2800);
				motorA.setPower(Data.AVARAGEVELOCITY);  //Hetki suoraa
				motorD.setPower(Data.AVARAGEVELOCITY);
				Delay.msDelay(2800);
				motorD.setPower(Data.ANOTHERTIREGOSLOWER);  //K‰‰nnyt‰‰n viivalle takaisin
				motorA.setPower(Data.ANOTHERTIREGOFASTER);
				Delay.msDelay(2800);
				motorA.setPower(Data.AVARAGEVELOCITY);  //Hetki suoraa
				motorD.setPower(Data.AVARAGEVELOCITY);
				Delay.msDelay(1000);
				motorD.setPower(Data.ANOTHERTIREGOFASTER);  //Korjataan viivalle tuloa
				motorA.setPower(Data.ANOTHERTIREGOSLOWER);
				Delay.msDelay(1500);
				motorA.setPower(Data.SLOWDOWN);
				motorD.setPower(Data.SLOWDOWN);
			}
			
			if(Data.isTouch)
			{
				motorA.backward();
				motorD.backward();
				motorA.setPower(30);
				motorD.setPower(30);
				Delay.msDelay(2000);
			}
			
			if(Data.colorline > 70) //Jos valkoisella, k‰‰nnyt‰‰n vasemmalle
			{
				motorA.setPower(30);
				motorD.setPower(10);
				motorA.forward();
				motorD.forward();
			}
			
			else if(Data.colorline < 30)  //Jos mustalla, k‰‰nnyt‰‰n oikealle
			{
				motorA.setPower(10);
				motorD.setPower(30);
				motorA.forward();
				motorD.forward();
			
			}
			
			else if(Data.currentColor == Color.RED) //Jos v‰ri on punainen, pys‰hdyt‰‰n
			{
				motorA.setPower(0);
				motorD.setPower(0);
				motorA.close();
				motorD.close();
				motorB.setSpeed(100);
				motorB.rotate(45);  //lippu pyˆrii edestakas. Voi pyˆritt‰‰ myˆs esim 720 eli kaksi kierrosta
				motorB.rotate(-45);
				motorB.rotate(45);  
				motorB.rotate(-45);
				motorB.rotate(45);  
				motorB.rotate(-45); 
				//Sound.playSample(new File("loppu_1.wav"), 100);  //Soitetaan loppu‰‰ni
				Sound.systemSound(false, 3); //Soitetaan ‰‰ni
				Delay.msDelay(40);
				Sound.twoBeeps();
			    Delay.msDelay(40);
				Sound.systemSound(false, 3);
				motorB.close();
				Data.shouldRun = false;
				
			}
			
			else if(Data.colorline < 70 && Data.colorline > 30 && Data.range > 0.25) //Jos ollaan viivalla, menn‰‰n suoraa			
			{
				motorA.setPower(30);
				motorD.setPower(30);
				motorA.forward();
				motorD.forward();
			}
			
		} //while-sulje		
		
	    } finally {
	        tm = System.currentTimeMillis()-tm;
	        tm=tm/1000;
	        System.out.println("It has taken time to go around the track " + tm + " second");
	        System.out.println("Obstacles detected " + Data.counter);
	    }//Aika sulje
	} // Run-sulje
	
	public void obstacle()
	{
		motorA.setPower(Data.SLOWDOWN);  //Hidastetaan
		motorD.setPower(Data.SLOWDOWN);
		Data.counter++; //Kun kohdataan este, counterin m‰‰r‰ kasvaa
		Sound.systemSound(false, 3); //Soitetaan ‰‰ni, kun kohdataan este
		motorA.setPower(Data.ANOTHERTIREGOSLOWER);  //K‰‰nnyt‰‰n pois viivalta
		motorD.setPower(Data.ANOTHERTIREGOFASTER);
		Delay.msDelay(1600);
		motorA.setPower(Data.AVARAGEVELOCITY);  //Hetki suoraa
		motorD.setPower(Data.AVARAGEVELOCITY);
		Delay.msDelay(1000);
		motorD.setPower(Data.ANOTHERTIREGOSLOWER);  //Suoristetaan
		motorA.setPower(Data.ANOTHERTIREGOFASTER);
		Delay.msDelay(2800);
		motorA.setPower(Data.AVARAGEVELOCITY);  //Hetki suoraa
		motorD.setPower(Data.AVARAGEVELOCITY);
		Delay.msDelay(2800);
		motorD.setPower(Data.ANOTHERTIREGOSLOWER);  //K‰‰nnyt‰‰n viivalle takaisin
		motorA.setPower(Data.ANOTHERTIREGOFASTER);
		Delay.msDelay(2800);
		motorA.setPower(Data.AVARAGEVELOCITY);  //Hetki suoraa
		motorD.setPower(Data.AVARAGEVELOCITY);
		Delay.msDelay(1200);
		motorD.setPower(Data.ANOTHERTIREGOFASTER);  //Korjataan viivalle tuloa
		motorA.setPower(Data.ANOTHERTIREGOSLOWER);
		Delay.msDelay(1500);
		motorA.setPower(Data.SLOWDOWN);
		motorD.setPower(Data.SLOWDOWN);
	}
    
	
} //Classin sulje
