package GPIO;

import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class TasterSnifferProgramme extends GpioHandler implements Runnable {

	public TasterSnifferProgramme() {
		super();
	}
	
//	final GpioPinDigitalInput programm1 =	  gpio.provisionDigitalInputPin(RaspiPin.GPIO_30,	  PinPullResistance.PULL_DOWN); 
//	  final GpioPinDigitalInput programm2 =	  gpio.provisionDigitalInputPin(RaspiPin.GPIO_30,	  PinPullResistance.PULL_DOWN); 
//	  final GpioPinDigitalInput programm3 =	  gpio.provisionDigitalInputPin(RaspiPin.GPIO_30,	  PinPullResistance.PULL_DOWN);
	 

	@Override
	public void run() {
	}		/**
		 * Startet Programm 1
		 
		programm1.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				System.out.println("Programm 1 gestartet!");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});

		programm2.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				System.out.println("Programm 2 gestartet!");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		

		programm3.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				System.out.println("Programm 3 gestartet!");
				try {
					Weichen.WEICHEN.schalteWeiche2('t');
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});

		while (true) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
*/
//	}
}
