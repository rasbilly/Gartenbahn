package GPIO;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class GpioHandler {

	public GpioHandler() {
		System.out.println("GPIO Handler");
	}

	static GpioController gpio = GpioFactory.getInstance();

	  
	

	/**
	 * Threads für Taster und andere eingaben erstellen
	 */
	public void threadErstellerEingang() {
		System.out.println("Thread erstellen");
		try {
			//Weichen.WEICHEN.getStatusWeiche1();

			Thread tasterDrehregler = new Thread(new TasterSnifferDrehregler());
			Thread tasterSignalWeiche = new Thread(new TasterSnifferSignalWeiche());
			// Thread tasterProgramme = new Thread(new TasterSnifferProgramme());

			tasterDrehregler.start();
			// tasterProgramme.start();
			tasterSignalWeiche.start();
			
		} catch (Exception r) {
			r.printStackTrace();
			System.out.println("thrad nicht erstellt");
			gpio.shutdown();
			gpio.unexportAll();
		}

	}

}
