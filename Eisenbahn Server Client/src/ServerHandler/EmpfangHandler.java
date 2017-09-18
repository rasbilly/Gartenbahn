package ServerHandler;

public class EmpfangHandler implements Runnable {

	Device device;

	public EmpfangHandler(Device device) {
		this.device = device;
	}

	@Override
	public void run() {
		//TODO hier keine Unterscheidung zwischen regler und zug ODER im Socket Server
		Thread heartbeat = new Thread(new Heartbeat((Zug) device));
		heartbeat.start();
		System.out.println("Heartbeat erfolgreich gestartet für "+device.getId());
			
		// EMPFANGEN
		while (true) {
			String s = device.empfangeDaten();
			String[] splits = s.split("#");
			Command command = getCommandoFromString(splits[0]);

			switch (command) {
				case TEMPO:
					if(device instanceof Zug) {
						Zug zug = (Zug) device;
						int j = Integer.parseInt(splits[1]);
						zug.setTempo(j);
					} else if(device instanceof Regler) {
						Regler regler = (Regler) device;
						int tempo = Integer.parseInt(splits[1]);
						regler.getZug().setTempo(tempo);
						System.out.println("Dreh: " + regler.getZug().getTempo());
						//Senden
						ZugManager.INSTANCE.sendeAnZug(regler.getZug(), regler.getZug().getTempoKommando());
						ZugManager.INSTANCE.sendeAnZug(regler.getZug(), regler.getZug().getTempoKommando());
					}
					break;
					
				case POSITION:
					Zug zug = (Zug) device;
					int i = Integer.parseInt(splits[1]); 
					zug.setPosition(i);
					break;
					
				case HEARTBEAT:
					zug = (Zug) device;
					zug.setAlive(true);
					zug.aliveHelper = true;
					break;
					
				case REQUEST_TEMPO:
					zug = (Zug) device;
					//Senden
					System.out.println("Tempoabfrage von " + zug.getId());
					ZugManager.INSTANCE.sendeAnZug(zug, zug.getTempoKommando());	
					break;
					
				default:
					System.out.println(s);
					System.out.println("Daten vom Zug: " + device.getId() + " waren fehlerhaft");
					break;
			}
			
		}

	}
	
	private Command getCommandoFromString(String commando) {
		switch (commando) {
			case "t":
				return Command.TEMPO;
			case "p":
				return Command.POSITION;
			case "r":
				return Command.REQUEST_TEMPO;
			case "h":
				return Command.HEARTBEAT;
			default:
				return Command.TEMPO;
		}
	}
	
	enum Command {
		TEMPO,
		POSITION,
		REQUEST_TEMPO,
		HEARTBEAT;
	}
}