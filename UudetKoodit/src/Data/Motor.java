import java.io.File;

 

import lejos.hardware.Sound;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.Color;
import lejos.utility.Delay;

 

public class Motor implements Runnable{
    
    

 


    @Override
    public void run() {
        // TODO Auto-generated method stub
        
        //Luodaan moottorit
        UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);
        UnregulatedMotor motorD = new UnregulatedMotor(MotorPort.D);
        EV3MediumRegulatedMotor motorB = new EV3MediumRegulatedMotor(MotorPort.B);
        
        
        float colorLine = Data.colorSample[2]*1000;
        
        while (Data.shouldRun) {
            try {
            Thread.sleep(1);
            } catch (InterruptedException e) {
            e.printStackTrace();
            }
            if (Data.range<0.25) {
                
                motorA.setPower(10);
                motorD.setPower(50);
                Delay.msDelay(1000);
                motorD.setPower(30);
                motorA.setPower(50);
                Delay.msDelay(50);
                motorD.setPower(30);
                motorA.backward();
                motorA.setPower(30);
                Delay.msDelay(50);
            }
            if(colorLine > 70) //Jos valkoisella, k‰‰nnyt‰‰n vasemmalle
            {
                motorA.setPower(30);
                motorD.setPower(10);
                motorA.forward();
                motorD.forward();
            }
            else if(colorLine < 30)  //Jos mustalla
            {
                motorA.setPower(10);
                motorD.setPower(30);
                motorA.forward();
                motorD.forward();
            
            }
            else if(Data.currentColor == Color.RED)
            {
                Data.shouldRun = false;
                motorA.setPower(0);
                motorD.setPower(0);
                motorA.close();
                motorD.close();
                motorB.setSpeed(100);
                Sound.playSample(new File("loppu_1.wav"), 100);
                motorB.rotate(45);  //lippu pyˆrii edestakas. Voi pyˆritt‰‰ myˆs esim 720 eli kaksi kierrosta
                motorB.rotate(-45);
                motorB.rotate(45);  
                motorB.rotate(-45);
                motorB.rotate(45);  
                motorB.rotate(-45);
                motorB.close();
                
            }
            
            else if(colorLine < 70 && colorLine > 30)
            {
                motorA.setPower(30);
                motorD.setPower(30);
                motorA.forward();
                motorD.forward();
            }
        } //while-sulje        
    } // Run-sulje

 

} //Classin sulje
