package Data;

import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.utility.Delay;
import lejos.hardware.motor.*;
import lejos.hardware.Sound;

public class Distance extends MotorFunctions implements Runnable{
	
	UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);
	UnregulatedMotor motorD = new UnregulatedMotor(MotorPort.D);
	Sound soundPlayer;
	public int counter; //esteiden laskemiseen

	@Override
	public void run() {
		
		float range;
		
		UltraSonicSensor uss = new UltraSonicSensor(SensorPort.S4);

	    range = uss.getRange();
		
		while (range > .25)
        {
			//moi sanna
			//motorA.setPower(30);
			//motorD.setPower(30);
			//motorA.forward();
			//motorD.forward();
        	System.out.println(range);
        	System.out.println("Ultrasensori");
           
        	//Nukutetaan s�ie hetkeksi
        	try
			{
				Thread.sleep(1);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
				
			}

            range = uss.getRange();
        }
		
		if (range < .25) { //Kun este on tarpeeksi l�hell�, soitetaan ��ni
			Sound.systemSound(false, 3);
			counter++;  //Lis�t��n counterin m��r��, aina kun este on niin l�hell�, ett� se pit�� kiert��
			System.out.println("Esteit� kohdattu " + counter);
			tiukkaOikea();
		}
		
		uss.close();
		motorA.close();
        motorD.close(); 
		
		/*for(int i = 0; i <5; i++)
		{
			System.out.println("Olen ultrasensori");
		} */
		
	}
	


}
