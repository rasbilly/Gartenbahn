package ServerHandler;

public class Heartbeat implements Runnable {

	Zug zug;

	public Heartbeat(Zug zug) {
		this.zug = zug;
	}

	@Override
	public void run() {
		
		long timeStart = System.currentTimeMillis(); 
		long timeEnd = 0;
		
		System.out.println("start " + timeStart);
		
		while((timeEnd-timeStart) < 10000) {
			
			timeEnd = System.currentTimeMillis(); 
			
			
			
			zug.sendeDaten("heartbeat");	
			
		}
			

		
				

				
	}

}
