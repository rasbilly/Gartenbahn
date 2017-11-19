package ServerHandler;



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

			zug.sendeDaten("heartbeat");
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
					if(helfer>2){
					System.out.println(zug.getId() + " Verbindung verloren! Neuversuch: " + helfer);
					}
					try {
						zug.sendeDaten("heartbeat");
					} catch (Exception e) {
						System.out.println(zug.getId()+" -- konte nicht gesendet werden");
					}
					helfer++;
				} else if (helfer == 5) {

					zug.setAlive(false);
					zug.setPosition(0);
					System.out.println("!! " +zug.getId()+" ist tot !!");
					ZugManager.INSTANCE.zugMap.remove(zug.getId(), zug); //TODO löscht altes tempo :((

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
