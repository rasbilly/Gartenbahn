package GPIO.digit;


import ServerHandler.Zug;
import ServerHandler.ZugManager;

public class Digit2 extends DigitHandler implements Runnable {

	@Override
	public void run() {
		String zugName = "Lgb";
		Zug zug = ZugManager.INSTANCE.findZugByName(zugName);
		int tempo = 100;

		while (true) {
			if (zug == null) {
				resetDigit2();
				punktDigit2.low();
				zug = ZugManager.INSTANCE.findZugByName(zugName);
				//GUI.PositionZuege.INSTANCE.posZug2(0);
			} else if (zug != null) {
				if (zug.isAlive() == false) {
					resetDigit2();
					zug = ZugManager.INSTANCE.findZugByName(zugName);
					// } else if (tempoAnna != zugAnna.getTempo() && (zugAnna.isAlive() == true)) {
				} else if ((zug.isAlive() == true)) {
					tempo = zug.getTempo();
					zahlAusgeben2(tempo);
				}
			}
		}

	}
	
	
	public void zahlAusgeben2(int i) {
		resetDigit2();
		
		// Richtungs LED
		if (i < 0) {
			ledRichtung2.high();
			punktDigit2.low();
		}
		// Digit
		if (i == 0) {
			aDigit2.low();
			bDigit2.low();
			cDigit2.low();
			eDigit2.low();
			dDigit2.low();
			fDigit2.low();
		} else if (i == 1|| i == -1) {
			bDigit2.low();
			cDigit2.low();
		} else if (i == 2 || i == -2) {
			aDigit2.low();
			bDigit2.low();
			gDigit2.low();
			eDigit2.low();
			dDigit2.low();
		} else if (i == 3 || i == -3) {
			aDigit2.low();
			bDigit2.low();
			gDigit2.low();
			cDigit2.low();
			dDigit2.low();
		} else if (i == 4 || i == -4) {
			fDigit2.low();
			bDigit2.low();
			gDigit2.low();
			cDigit2.low();
		} else if (i == 5 || i == -5) {
			aDigit2.low();
			fDigit2.low();
			gDigit2.low();
			cDigit2.low();
			dDigit2.low();
		}
	}
	
	public static void resetDigit2() {
		aDigit2.high();
		bDigit2.high();
		cDigit2.high();
		dDigit2.high();
		eDigit2.high();
		fDigit2.high();
		gDigit2.high();
		punktDigit2.high();
		ledRichtung2.low();
	}

}
