package GPIO.digit;

import GPIO.LedStatusZugHandler;
import ServerHandler.Zug;
import ServerHandler.ZugManager;

public class Digit3 extends LedStatusZugHandler implements Runnable {

	@Override
	public void run() {
		String zugName = "DB";
		Zug zug = ZugManager.INSTANCE.findZugByName(zugName);
		int tempo = 100;

		while (true) {
			if (zug == null) {
				resetDigit3();
				punktDigit3.low();
				zug = ZugManager.INSTANCE.findZugByName(zugName);

			} else if (zug != null) {
				if (zug.isAlive() == false) {
					resetDigit3();
					zug = ZugManager.INSTANCE.findZugByName(zugName);
					// } else if (tempoAnna != zugAnna.getTempo() && (zugAnna.isAlive() == true)) {
				} else if ((zug.isAlive() == true)) {
					tempo = zug.getTempo();
					zahlAusgeben3(tempo);
				}
			}
		}

	}
	
	public void zahlAusgeben3(int i) {
		resetDigit3();
		
		// Richtungs LED
		if (i < 0) {
			ledRichtung3.high();
			punktDigit3.low();
		}
		// Digit
		if (i == 0) {
			aDigit3.low();
			bDigit3.low();
			cDigit3.low();
			eDigit3.low();
			dDigit3.low();
			fDigit3.low();
		} else if (i == 1 || i == -1) {
			bDigit3.low();
			cDigit3.low();
		} else if (i == 2 || i == -2) {
			aDigit3.low();
			bDigit3.low();
			gDigit3.low();
			eDigit3.low();
			dDigit3.low();
		} else if (i == 3 || i == -3) {
			aDigit3.low();
			bDigit3.low();
			gDigit3.low();
			cDigit3.low();
			dDigit3.low();
		} else if (i == 4 || i == -4) {
			fDigit3.low();
			bDigit3.low();
			gDigit3.low();
			cDigit3.low();
		} else if (i == 5 || i == -5) {
			aDigit3.low();
			fDigit3.low();
			gDigit3.low();
			cDigit3.low();
			dDigit3.low();
		}
	}
	
	public static void resetDigit3() {
		aDigit3.high();
		bDigit3.high();
		cDigit3.high();
		dDigit3.high();
		eDigit3.high();
		fDigit3.high();
		gDigit3.high();
		punktDigit3.high();
		ledRichtung3.low();
	}


}
