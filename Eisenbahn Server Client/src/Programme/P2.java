package Programme;

import ServerHandler.Log;

public class P2 extends ProgrammHandler {

	boolean status = false;

	public void start() {
		Log.Track(getClass().getName(), "Programm 2 gestartet", null);
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (status) {

		
				}
				Log.Track(getClass().getName(), "Programm 2 ist beendet", null);
			}
		}).start();
	}


}
