package ServerHandler;

import java.io.IOException;

public class Heartbeat implements Runnable {

	Zug zug;
	int helfer = 0;

	public Heartbeat(Zug zug) {
		this.zug = zug;
	}

	@Override
	public void run() {

		warten(5000);

		long timeStart = System.currentTimeMillis();

		while (true) {
			String temp = Integer.toString(zug.getTempo());
			zug.sendeDaten("h" + temp);
			zug.aliveHelper = false;

			while ((System.currentTimeMillis() - timeStart) < 1800) {

				if (zug.aliveHelper == true) {
					helfer = 0;
					warten(4000);
					break;
				}
			}
			timeStart = System.currentTimeMillis();

			if (zug.aliveHelper == false) {
				if (helfer < 5) {
					if (helfer > 2) {
						Log.Warning(getClass().getName(), "Verbindung zu " + zug.getId() + "verloren",
								"Neuversuch: " + helfer, false);
					}
					try {
						zug.sendeDaten("h" + temp);
					} catch (Exception e) {
						Log.Warning(getClass().getName(), "h konnte nicht an " + zug.getId() + "gesendet werden", e);
					}
					helfer++;
				} else if (helfer == 5) {
					zug.setAlive(false);
					zug.setPosition(0);
					Log.Warning(getClass().getName(), zug.getId()+" abgemeldet!","keine Verbindung mehr möglich",new IOException("Thread löschen"));
					ZugManager.INSTANCE.zugMap.remove(zug.getId(), zug); // TODO
																			// löscht
																			// altes
																			// tempo
																			// :((

					Thread.currentThread().interrupt();
					break;
				}
			}
		}

	}

	public void warten(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
