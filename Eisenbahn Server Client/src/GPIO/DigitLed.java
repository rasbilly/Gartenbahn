package GPIO;

import ServerHandler.Zug;
import ServerHandler.ZugManager;

public class DigitLed extends LedStatusZugHandler implements Runnable {

	@Override
	public void run() {

		Zug zugAnna = ZugManager.INSTANCE.findZugByName("Anna");
		int tempoAnna = 100;

		while (true) {
			if (zugAnna == null) {
				resetDigit1();
				zugAnna = ZugManager.INSTANCE.findZugByName("Anna");
				
			} else if (zugAnna != null) {
				if (zugAnna.isAlive() == false) {
					resetDigit1();
					zugAnna = ZugManager.INSTANCE.findZugByName("Anna");
				} else if (tempoAnna != zugAnna.getTempo() && (zugAnna.isAlive() == true)) {
					tempoAnna = zugAnna.getTempo();
					zahlAusgeben1(tempoAnna);
				}
			}
		}
	}

	/**
	 * Zeigt das Aktuelle Tempo auf dem 1 Segment Digit Display an. Schaltet die
	 * jeweiligen Abschnitte.
	 * 
	 * @param i
	 */
	public void zahlAusgeben1(int i) {
		resetDigit1();
		// Richtungs LED
		if (i < 0) {
			ledRichtung1.high();
		}
		// Digit
		if (i == 0) {
			aDigit1.high();
			bDigit1.high();
			cDigit1.high();
			eDigit1.high();
			dDigit1.high();
			fDigit1.high();
		} else if (i == 1 || i == -1) {
			bDigit1.high();
			cDigit1.high();
		} else if (i == 2 || i == -2) {
			aDigit1.high();
			bDigit1.high();
			gDigit1.high();
			eDigit1.high();
			dDigit1.high();
		} else if (i == 3 || i == -3) {
			aDigit1.high();
			bDigit1.high();
			gDigit1.high();
			cDigit1.high();
			dDigit1.high();
		} else if (i == 4 || i == -4) {
			fDigit1.high();
			bDigit1.high();
			gDigit1.high();
			cDigit1.high();
		} else if (i == 5 || i == -5) {
			aDigit1.high();
			fDigit1.high();
			gDigit1.high();
			cDigit1.high();
			dDigit1.high();
		}
	}

	/**
	 * Schaltet alle Abschnitte aus
	 */
	public void resetDigit1() {
		aDigit1.low();
		bDigit1.low();
		cDigit1.low();
		dDigit1.low();
		eDigit1.low();
		fDigit1.low();
		gDigit1.low();
		ledRichtung1.low();

	}

}
