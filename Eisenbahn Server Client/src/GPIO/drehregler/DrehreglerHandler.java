package GPIO.drehregler;

import GPIO.GpioHandler;
import ServerHandler.Log;
import ServerHandler.Zug;

public class DrehreglerHandler extends GpioHandler {

	public DrehreglerHandler() {
	}

	public void drehreglerErsteller() {
		try {
			Thread drehregler1 = new Thread(new Drehregler1());
			Thread drehregler2 = new Thread(new Drehregler2());
			Thread drehregler3 = new Thread(new Drehregler3());
			drehregler1.start();
			drehregler2.start();
			drehregler3.start();
		} catch (Exception e) {
			Log.Error(getClass().getName(), "Drehregler Erstellen Threads", e);}
	}
//TODO syso in log wandeln (INFO)
	/**
	 * Gibt das neue Tempo auf der Console aus
	 * 
	 * @param zug
	 * @param tempo
	 */
	 public static void drehreglerausgabe(Zug zug, int tempo) {
		System.out.print(zug.getZugId());
		if (tempo > 0) {
			System.out.print(" - Vorw�rts - ");
		} else if (tempo < 0) {
			System.out.print(" - R�ckw�rts - ");
		} else if (tempo == 0) {
			System.out.print(" - Stopp - ");
		}
		System.out.println("Tempo: " + tempo / 2);
	}

	/**
	 * Sendet neues Tempo an den Zug
	 * 
	 * @param zug
	 * @param tempo
	 */
	public static void sendeReglerAnZug(Zug zug, int tempo) {
		zug.setTempo(tempo / 2);
		zug.sendeDaten("t" + tempo / 2);
		// zug.sendeDaten(zug.getTempoKommando());
	}

}
