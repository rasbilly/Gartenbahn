package Programme;

public class P3 extends ProgrammHandler  {

	boolean status = false;

	public void start() {
		System.out.println("Programm 3 ist gestartet");
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (status) {

		

				}
				System.out.println("Programm 3 ist beendet");
			}
		}).start();
	}


}
