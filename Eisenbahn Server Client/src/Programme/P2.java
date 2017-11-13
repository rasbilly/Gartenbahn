package Programme;

public class P2 extends ProgrammHandler {

	boolean status = false;

	public void start() {
		System.out.println("Programm 2 ist gestartet");
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (status) {

		
				}
				System.out.println("Programm 2 ist beendet");
			}
		}).start();
	}


}
