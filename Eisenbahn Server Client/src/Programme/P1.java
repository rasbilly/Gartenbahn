package Programme;

public class P1 extends ProgrammHandler {

	boolean status = false;

	public void start() {
		System.out.println("Programm 1 ist gestartet");
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
				System.out.println("Programm 1 ist beendet");
			}
		}).start();
	}

}
