package Programme;

import GPIO.Weichen;
import ServerHandler.Zug;
import ServerHandler.ZugManager;
import Verwalter.Gleisabschnitte;

public class ManuellHelper implements Runnable {

	@Override
	public void run() {
		try {
			while (true) {
				Thread.sleep(20);
				bahnhofBelegt();
				bahnhofUmfahrungBelegt();
			}
		} catch (InterruptedException e) {
			System.err.println("Fehler - Manuelle Automatik");
		}

	}
	
	//TODO evtl besser ausl�sen wenn zug �ber sensor f�hrt, statt st�ndiges abfragen

	/**
	 * Falls Bahnhof belegt UND Weiche in falscher Stellung UND beliegiger Zug f�hrt
	 * �ber Position 13 DANN ...
	 */
	public void bahnhofBelegt() {
		if (Gleisabschnitte.INSTANCE.getGleisabschnitte()[1] == true) {
			if (Weichen.WEICHEN.getStatusWeiche1() == 'r') {
				for (Zug zug : ZugManager.INSTANCE.getZugMap().values()) {
					if (zug.getPosition() == 13) {
						System.out.println("ACHTUNG Zusammenprall");
					}
				}
			}
		}
	}

	/**
	 * Falls Bahnhofsumfahrung belegt UND Weiche in falscher Stellung UND beliegiger
	 * Zug f�hrt �ber Position 13 DANN ...
	 */
	public void bahnhofUmfahrungBelegt() {
		if (Gleisabschnitte.INSTANCE.getGleisabschnitte()[2] == true) {
			if (Weichen.WEICHEN.getStatusWeiche1() == 'l') {
				for (Zug zug : ZugManager.INSTANCE.getZugMap().values()) {
					if (zug.getPosition() == 13) {
						System.out.println("ACHTUNG Zusammenprall");
					}
				}
			}
		}
	}
	
	public void abstellgleisBelegt() {
		if (Gleisabschnitte.INSTANCE.getGleisabschnitte()[7] == true) {
			
		}
	}
}