package GPIO;

import ServerHandler.Zug;
import ServerHandler.ZugManager;

public class LedStatusZug extends GpioHandler implements Runnable{
	
	public LedStatusZug() {
		super();
	}
		//Regler dem Zug zuweisen
		Zug zugAnna =ZugManager.INSTANCE.findZugByName("Anna");
		
		public void statusLed(){
			
		}
			
		
		/**
		 * Fahrtrichtungs LED für Zug Anna. 
		 * Vorwärts: Aus
		 * Rückwärts: Blinken
		 * @throws InterruptedException 
		 */
		public void fahrtrichtungLed() throws InterruptedException{
				while(zugAnna.getTempo()<0){
					faRiLedZugAnna.low();
					Thread.sleep(800);
					faRiLedZugAnna.high();
					Thread.sleep(1000);
				}
				
		}



		@Override
		public void run() {
			while(zugAnna.getTempo()<0){
				faRiLedZugAnna.low();
				try {
					Thread.sleep(800);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				faRiLedZugAnna.high();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
}
