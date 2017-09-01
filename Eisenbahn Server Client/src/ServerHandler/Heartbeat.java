package ServerHandler;

public class Heartbeat implements Runnable{

	@Override
	public void run() {
		
		long zeit;
		
		for(Zug z : ZugManager.INSTANCE.getZugMap().values()){
			z.sendeDaten("heartbeat");
			
			
			
			
		}
		
	}

}
