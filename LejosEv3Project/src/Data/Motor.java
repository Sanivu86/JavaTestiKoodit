package Data;

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
		
		//Aloitetaan ajanlasku
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
			
			//Jos esteeseen on matkaa alle 25cm, kutsutaan esteen kiert‰v‰‰ metodia
			if (Data.range <= 0.25) 
			{
				
			  obstacle();
			}
			
			//Jos robotti koskettaa estett‰, peruutetaan
			if(Data.isTouch)
			{
				motorA.backward();
				motorD.backward();
				motorA.setPower(30);
				motorD.setPower(30);
				Delay.msDelay(2000);
			}
			
			//Jos valkoisella, k‰‰nnyt‰‰n vasemmalle
			if(Data.colorline > 70) 
			{
				motorA.setPower(40);
				motorD.setPower(10);
				motorA.forward();
				motorD.forward();
			}
			
			//Jos mustalla, k‰‰nnyt‰‰n oikealle
			else if(Data.colorline < 30)  
			{
				motorA.setPower(10);
				motorD.setPower(40);
				motorA.forward();
				motorD.forward();
			
			}
			
			//Jos v‰ri on punainen, pys‰hdyt‰‰n
			else if(Data.currentColor == Color.RED) 
			{
				end();
			}
			
			//Jos ollaan viivalla, menn‰‰n suoraa	
			else if(Data.colorline < 70 && Data.colorline > 30 && Data.range > 0.25) 		
			{
				motorA.setPower(40);
				motorD.setPower(40);
				motorA.forward();
				motorD.forward();
			}
			
		} //while-sulje		
		
		//Lasketaan radan aika ja tulostetaan se, sek‰ esteiden m‰‰r‰
	    } finally {
	        tm = System.currentTimeMillis()-tm;
	        tm=tm/1000;
	        System.out.println("It has taken time to go around the track " + tm + " second");
	        System.out.println("Obstacles detected " + Data.counter);
	    }//Aika sulje
	} // Run-sulje
	
	//Metodi, joka kiert‰‰ esteen ja lis‰‰ estelaskuriin yhden
	public void obstacle()
	{
		motorA.setPower(Data.SLOWDOWN);  //Hidastetaan
		motorD.setPower(Data.SLOWDOWN);
		Data.counter++; //Kun kohdataan este, counterin m‰‰r‰ kasvaa
		Sound.systemSound(false, 3); //Soitetaan ‰‰ni, kun kohdataan este
		motorA.setPower(Data.ANOTHERTIREGOSLOWER);  //K‰‰nnyt‰‰n pois viivalta
		motorD.setPower(Data.ANOTHERTIREGOFASTER);
		Delay.msDelay(3800);
		motorA.setPower(Data.SLOWDOWN);  //Hetki suoraa
		motorD.setPower(Data.SLOWDOWN);
		Delay.msDelay(3400);
		motorD.setPower(Data.ANOTHERTIREGOSLOWER);  //Suoristetaan
		motorA.setPower(Data.ANOTHERTIREGOFASTER);
		Delay.msDelay(3100);
		motorA.setPower(Data.SLOWDOWN);  //Hetki suoraa
		motorD.setPower(Data.SLOWDOWN);
		Delay.msDelay(3000);
		motorD.setPower(Data.ANOTHERTIREGOSLOWER);  //K‰‰nnyt‰‰n viivalle takaisin
		motorA.setPower(Data.ANOTHERTIREGOFASTER);
		Delay.msDelay(2900);
		motorA.setPower(Data.SLOWDOWN);  //Hetki suoraa
		motorD.setPower(Data.SLOWDOWN);
		Delay.msDelay(2800);
		motorD.setPower(Data.ANOTHERTIREGOFASTER);  //Korjataan viivalle tuloa
		motorA.setPower(Data.ANOTHERTIREGOSLOWER);
		Delay.msDelay(2500);
		motorA.setPower(Data.SLOWDOWN);
		motorD.setPower(Data.SLOWDOWN);
	}
	
	//Ohjelman lopetus-metodi. Suljetaan moottorit ja heilutetaan lippua + ‰‰ni
	public void end()
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
		Sound.systemSound(false, 3); //Soitetaan ‰‰ni
		Delay.msDelay(40);
		Sound.twoBeeps();
	    Delay.msDelay(40);
		Sound.systemSound(false, 3);
		motorB.close();
		Data.shouldRun = false;
		
	}
	
} //Classin sulje
