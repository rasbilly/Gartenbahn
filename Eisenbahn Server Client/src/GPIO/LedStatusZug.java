package GPIO;

import ServerHandler.Zug;
import ServerHandler.ZugManager;

public class LedStatusZug extends GpioHandler {

	public LedStatusZug() {
		super();
	}

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
			faRiLedZugAnna.low();
			Thread.sleep(800);
			faRiLedZugAnna.high();
			Thread.sleep(1000);
		}

	}

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
}
