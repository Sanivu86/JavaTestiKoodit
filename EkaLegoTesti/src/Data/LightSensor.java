package Data;

import java.io.File;

import lejos.hardware.BrickFinder;
import lejos.hardware.Sound;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

//extends MotorFunctions
public class LightSensor extends MotorFunctions implements Runnable{
	
	EV3ColorSensor sensor;
	SampleProvider colorProvider;
	float[] colorSample;
	Sound soundPlayer;
	
	public LightSensor()
	{
		
		
	}
	
	public void LaskeAika()
    {
        long tm = System.currentTimeMillis();
        try {
            //T�h�n tulee metodi mink� aika lasketaan
        	
        	run();
        } finally {
            tm = System.currentTimeMillis()-tm;
            System.out.println("it has taken time to go around the track" + tm + "ms");
        }
       
          
          
    }

	@Override
	public void run() {
		
		 try
		{
			Thread.sleep(1);
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
			
		}

		//Tehd��n moottorit testi� varten, jotta niit� voidaan k�ytt�� k��ntymisess�
		//UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);  //Oikea py�r�
		EV3MediumRegulatedMotor motorB = new EV3MediumRegulatedMotor(MotorPort.B);     // Lippu
		//UnregulatedMotor motorD = new UnregulatedMotor(MotorPort.D);  //Vasen py�r�
		
		//Luodaan portti s1
       Port s1 = BrickFinder.getLocal().getPort("S1");
		
       //Luodaan sensori ja sille RGB arvojen hakija sek� taulukko, johon arvot tallennetaan
		sensor = new EV3ColorSensor(s1);
		colorProvider = sensor.getRGBMode();
		colorSample = new float[colorProvider.sampleSize()];

		//��nitiedostot
		 File soundFileEnd = new File("C:/temp/loppu.wav"); //Vaihda 8bit
		 
		
		
		
		while(Button.ESCAPE.isUp())
		{
			
			//Haetaan eri v�rien arvot
			//Tummilla v�reill� on pienempi arvo, kuin vaaleilla
			//V�rin arvon mukaan voidaan m��ritt�� ollaanko mustalla vai valkoisella alueella
			//Kun ollaan viivan reunalla light intensity on 35%
			colorProvider.fetchSample(colorSample, 0);
			System.out.println("V�risensori");
			float colorLine = colorSample[2]*1000;
			
			
			//S�ie nukutetaan 
			
			try
			{
				Thread.sleep(1);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
				
			}
			//Haetaan my�s punainen v�ri pys�ytyst� varten
			
			int colorRed = Color.RED;
			int currentColor = sensor.getColorID();
		
		
			//Tehd��n if-lauseet, jotta robotti pysyisi viivan tuntumassa
			//Jos v�ri on punainen pys�ytet��n robotti
			
			if(colorLine > 70)  //Jos valkoisella
			{
				/*motorA.setPower(30);
				motorD.setPower(10);
				motorA.forward();
				motorD.forward(); */
				loivaVasen();
			}
			else if(colorLine < 30)  //Jos mustalla
			{
				/*motorA.setPower(10);
				motorD.setPower(30);
				motorA.forward();
				motorD.forward(); */
				
				loivaOikea();
			}
			else if(currentColor == colorRed)  //Jos v�ri on punainen, pys�hdyt��n ja lopetetaan loop
			{								   //Lis�ksi soitetaan ��ni ja py�ritet��n lippua
				motorA.setPower(0);
				motorD.setPower(0);
				motorA.close();
				motorD.close();
				motorB.setSpeed(100);
				motorB.rotate(45);  //lippu py�rii kierroksen. Voi py�ritt�� my�s esim 720 eli kaksi kierrosta
				motorB.rotate(-45);
				motorB.rotate(45);  
				motorB.rotate(-45);
				motorB.rotate(45);  
				motorB.rotate(-45);
				Sound.systemSound(false, 3); //Soitetaan ��ni
				Delay.msDelay(40);
				Sound.twoBeeps();
			    Delay.msDelay(40);
				Sound.systemSound(false, 3);
				sensor.close();  //Sensori kiinni
				motorB.close();
				break;           //Poistutaan while-loopista
			} 
			else if(colorLine < 70 && colorLine > 30)  //Jos viivalla
			{
				motorA.setPower(30);
				motorD.setPower(30);
				motorA.forward();
				motorD.forward();
			}
		
		} // while-sulje 
		
		/*for(int i = 0; i <5; i++)
		{
			System.out.println("Olen v�risensori");
		} */
		

	} //run-sulje 

}
