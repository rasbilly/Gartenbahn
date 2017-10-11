package GPIO;

import ServerHandler.Zug;
import ServerHandler.ZugManager;

public class DigitLed extends LedStatusZugHandler implements Runnable {

	@Override
	public void run() {
		Thread lt = new Thread(new LedTest());
		lt.start(); //nicht start

		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Zug zugAnna = ZugManager.INSTANCE.findZugByName("Anna");
		int tempoAnna = 100;

		while (true) {
			
			
			if (zugAnna == null) {
				resetDigit1();
				punktDigit1.low();
				zugAnna = ZugManager.INSTANCE.findZugByName("Anna");
				
			} else if (zugAnna != null) {
				if (zugAnna.isAlive() == false) {
					resetDigit1();
					zugAnna = ZugManager.INSTANCE.findZugByName("Anna");
			//	} else if (tempoAnna != zugAnna.getTempo() && (zugAnna.isAlive() == true)) {
				} else if ((zugAnna.isAlive() == true)) {
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

	/**
	 * Schaltet alle Abschnitte aus
	 */
	public void resetDigit1() {
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
	public void resetDigit2() {
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
	public void resetDigit3() {
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
