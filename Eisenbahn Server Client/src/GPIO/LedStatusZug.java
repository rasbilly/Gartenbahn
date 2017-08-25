package GPIO;

import ServerHandler.Zug;
import ServerHandler.ZugManager;

public class LedStatusZug extends GpioHandler implements Runnable {

	public LedStatusZug() {
		super();
	}

	//TODO bisher nur f�r einen Zug
	
	// Regler dem Zug zuweisen
	Zug zugAnna = ZugManager.INSTANCE.findZugByName("Anna");

	public void statusLed() {

	}

	/**
	 * Fahrtrichtungs LED f�r Zug Anna. Vorw�rts: Aus R�ckw�rts: Blinken
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
