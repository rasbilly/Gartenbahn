package Programme;

import ServerHandler.Log;

public class P1 extends ProgrammHandler {

	boolean status = false;

	public void start() {
		Log.Track(getClass().getName(), "Programm 1 gestartet", null);
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (status) {

					// TODO Stream um einen zug zu filtern

					// alle Z�ge finden und Runden und Ablauf definieren, nach ablauf neu W�rfel

					// ODER

					// Jede Runde aufs neue Losen

					// Zug zufaelligerZug = ZugManager.INSTANCE.getZugMap()

				}
				Log.Track(getClass().getName(), "Programm 1 ist beendet", null);
			}
		}).start();
	}

}
