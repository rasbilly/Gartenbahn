package Programme;

import ServerHandler.Log;

public class ProgrammHandler {
	
	public static final ProgrammHandler INSTANCE = new ProgrammHandler();
	P1 p1;
	P2 p2;
	P3 p3;
	Thread manu;
	
	public void proErsteller() {
	p1 = new P1();
	p2 = new P2();
	p3 = new P3();
	
	manu = new Thread(new ManuellHelper());
	manu.start();
	}
	
	/**
	 * Aktiviert das jeweilige Programm
	 * @param p
	 */
	public void programmWaehlen(int p){
		
		p1.status = false;
		p2.status = false;
		p3.status = false;
		if(p == 1){
			p1.status = true;
			p1.start();
		}else if(p ==2){
			p2.status = true;
			p2.start();
		}else if(p == 3){
			p3.status = true;
			p3.start();
		}else{
			Log.Error(getClass().getName(), "Kein Programm gefunden", null);
		}
	}
	
	
	
	/**
	 * Fragt ab welches Programm gerade aktive ist
	 * @return 
	 */
	public String proStatusAbfrage(){
		if(p1.status==true){
			return "Programm 1 Aktiv";
		}else if (p2.status==true){
			return"Programm 2 Aktiv";
		}else if (p3.status==true){
			return"Programm 3 Aktiv";
		}
		return "Manuelle Fahrt";
	}

}
