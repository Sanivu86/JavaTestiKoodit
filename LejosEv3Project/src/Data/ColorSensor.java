package Data;

import lejos.hardware.BrickFinder;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;
import lejos.robotics.SampleProvider;

/**
 * 
 * @author Sanna Nieminen-Vuorio
 * ColorSensor-luokka k‰skytt‰‰ v‰risensoria. Luokka k‰ytt‰‰ Runnable-metodia, jotta siit‰ voidaan tehd‰ Main-luokassa s‰ie.
 * Sensori hakee sek‰ colorId:t‰, ett‰ rgb-arvoja.
 * ColorId haetaan, jotta robotti osaa pys‰hty‰ punaisella.
 * Rgb-arvoista otetaan sininen arvo, joka kerrotaan 1000:lla, jotta saadaa helppo arvo vertailuun.
 * Rgb-arvon mukaan robotti tiet‰‰, onko se liikaa mustalla tai valkoisella vai sopivasti viivalla.
 * 
 * Arvot syˆtet‰‰n Data-luokan staattisiin muuttujiin, josta Motor-luokka hakee ne.
 *
 */

public class ColorSensor implements Runnable{
	
	EV3ColorSensor sensor;
	SampleProvider colorProvider;

	 
	@Override
	public void run() {
		
		/**
		 * Luodaan portti
		 */
		Port s1 = BrickFinder.getLocal().getPort("S1");
				
		/**
		 * Luodaan sensori
		 */
		sensor = new EV3ColorSensor(s1);
		colorProvider = sensor.getRGBMode();
		float [] colorSample = new float[colorProvider.sampleSize()];
		
		/**
		 * Sensori toimii, kunnes se havaitsee punaista, jolloin Motor-luokka 
		 * asettaa shouldRun-booleanin falseksi
		 */
		while(Data.shouldRun)
		{
			/**
			 * Nukutetaan s‰ie, jotta muutkin s‰ikeet saavat tilaa
			 */
			 try
				{
					Thread.sleep(1);
				}
				catch(InterruptedException e)
				{
					e.printStackTrace();
					
				}
			 
			/**
			 * Haetaan rgb-arvot
			 */
			 colorProvider.fetchSample(colorSample, 0);
			 
			 /**
			  * Tallennetaan sinisen v‰rin arvo * 1000 staattiseen muuttujaan
			  */
			 
			 Data.colorline = colorSample[2]*1000;
			 
			 /**
			  * Tarkistetaan colorId:ll‰ onko punaista v‰ri‰
			  */
			
			Data.currentColor = sensor.getColorID();
			
			if(Data.currentColor == Color.RED)
			 {
				 System.out.println(Data.currentColor + " on punainen");
			 }
			 
			 
			 /**
			  * Printattiin testatessa sensorin nime‰ ja rgb-arvoja, 
			  * jotta n‰htiin s‰ikeen toiminta helposti consolista
			  * 
			  * System.out.println("Colorsensor going");
			  * System.out.println(colorSample[2]*1000);
			  * 
			  */

			 
		} 

		/**
		 * Kun ohjelma loppuu, suljetaan sensori
		 */
		sensor.close();

		
	} 

} 
