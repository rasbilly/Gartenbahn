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
		 
		Log.Track(getClass().getName(), "Heartbeat gestartet", device.getId());
			
		// EMPFANGEN
		while (true) {
			String s = device.empfangeDaten();
			String[] splits = s.split("#");
			Command command = getCommandoFromString(splits[0]);
			//System.out.println(s);

			switch (command) {
				case TEMPO:
					if(device instanceof Zug) {
						Zug zug = (Zug) device;
						int j = Integer.parseInt(splits[1]);
						zug.setTempo(j);
						Log.Track(getClass().getName(),"Neues Tempo empfangen", zug.getId()+": "+zug.getTempo());
					} else if(device instanceof Regler) {
						Regler regler = (Regler) device;
						int tempo = Integer.parseInt(splits[1]);
						regler.getZug().setTempo(tempo);
						Log.Track(getClass().getName(),"Neues Tempo empfangen", regler.getId()+": "+regler.getZug().getTempo());
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
					Log.Track(getClass().getName(), "Tempoabfrage", zug.getId());
					ZugManager.INSTANCE.sendeAnZug(zug, zug.getTempoKommando());	//Senden
					break;
					
				default:
					Log.Warning(getClass().getName(), "Daten fehlerhaft: \""+s+"\"",device.getId(),false);
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