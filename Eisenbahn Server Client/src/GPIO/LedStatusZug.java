package GPIO;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

import ServerHandler.Zug;
import ServerHandler.ZugManager;

public class LedStatusZug extends GpioHandler {

	public LedStatusZug() {
		super();
	}
	
//	final	  GpioPinDigitalOutput faRiLedZugAnna =	  gpio.provisionDigitalOutputPin(RaspiPin.GPIO_30, "Fahrtrichtung Anna",	  PinState.LOW); 
//	  final GpioPinDigitalOutput faRiLedZugZwei =	  gpio.provisionDigitalOutputPin(RaspiPin.GPIO_30, "Fahrtrichtung Zug 2",	  PinState.LOW);
//	

	// TODO bisher nur für einen Zug

	// Regler dem Zug zuweisen
	Zug zugAnna = ZugManager.INSTANCE.findZugByName("Anna");

	

	public void statusLedZugAnna(int tempo) {
		if (tempo < 0 && tempo > -10) {

		} else if (tempo >= 0 && tempo < 10) {

		} else {

		}

	}

	/**
	 * Fahrtrichtungs LED für Zug Anna. Vorwärts: Aus Rückwärts: Blinken
	 * 
	 * @throws InterruptedException
	 */
	public void fahrtrichtungLed() throws InterruptedException {
		while (zugAnna.getTempo() < 0) {
		//	faRiLedZugAnna.low();
			Thread.sleep(800);
		//	faRiLedZugAnna.high();
			Thread.sleep(1000);
		}

	}
/**
	@Override
	public void run() {
		while (zugAnna.getTempo() < 0) {

			try {
				faRiLedZugAnna.low();
				Thread.sleep(800);
				faRiLedZugAnna.high();
				Thread.sleep(1000);

			} catch (InterruptedException e) {
				e.printStackTrace();
				System.err.println("Fehler --- LED Zug Status Blinken");
			}

		}

	}
	*/
}
