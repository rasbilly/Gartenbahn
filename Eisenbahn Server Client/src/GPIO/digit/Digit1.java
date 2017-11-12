package GPIO.digit;


import ServerHandler.Zug;
import ServerHandler.ZugManager;

public class Digit1 extends DigitHandler implements Runnable {

	@Override
	public void run() {
		String zugName = "Anna";
		Zug zug = ZugManager.INSTANCE.findZugByName(zugName);
		int tempo = 100;

		while (true) {
			if (zug == null) {
				resetDigit1();
				punktDigit1.low();
				//GUI.PositionZuege.INSTANCE.posZug1(0);
				zug = ZugManager.INSTANCE.findZugByName(zugName);

			} else if (zug != null) {
				if (zug.isAlive() == false) {
					resetDigit1();
					zug = ZugManager.INSTANCE.findZugByName(zugName);
					// } else if (tempoAnna != zugAnna.getTempo() && (zugAnna.isAlive() == true)) {
				} else if ((zug.isAlive() == true)) {
					tempo = zug.getTempo();
					zahlAusgeben1(tempo);
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
			punktDigit1.low();
		}
		// Digit
		if (i == 0) {
			aDigit1.low();
			bDigit1.low();
			cDigit1.low();
			eDigit1.low();
			dDigit1.low();
			fDigit1.low();
		} else if (i == 1 || i == -1) {
			bDigit1.low();
			cDigit1.low();
		} else if (i == 2 || i == -2) {
			aDigit1.low();
			bDigit1.low();
			gDigit1.low();
			eDigit1.low();
			dDigit1.low();
		} else if (i == 3 || i == -3) {
			aDigit1.low();
			bDigit1.low();
			gDigit1.low();
			cDigit1.low();
			dDigit1.low();
		} else if (i == 4 || i == -4) {
			fDigit1.low();
			bDigit1.low();
			gDigit1.low();
			cDigit1.low();
		} else if (i == 5 || i == -5) {
			aDigit1.low();
			fDigit1.low();
			gDigit1.low();
			cDigit1.low();
			dDigit1.low();
		}
	}

	/**
	 * Schaltet alle Abschnitte aus
	 */
	public static void resetDigit1() {
		aDigit1.high();
		bDigit1.high();
		cDigit1.high();
		dDigit1.high();
		eDigit1.high();
		fDigit1.high();
		gDigit1.high();
		punktDigit1.high();
		ledRichtung1.low();
	}

}
