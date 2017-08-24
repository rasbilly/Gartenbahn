package Programme;

public class ProgrammHandler {
	
	P1 p1 = new P1();
	P2 p2 = new P2();
	P3 p3 = new P3();
	
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
		}else if(p ==2){
			p2.status = true;
		}else if(p == 3){
			p3.status = true;
		}else{
			System.err.println("Kein Programm gefunden");
		}
	}
	
	
	
	/**
	 * Fragt ab welches Programm gerade aktive ist
	 * @return 
	 */
	public String proStatusAbfrage(){
		if(p1.status==true){
			return "Programm 1 Aktive";
		}else if (p2.status==true){
			return"Programm 2 Aktive";
		}else if (p3.status==true){
			return"Programm 3 Aktive";
		}
		return "Manuelle Fahrt";
	}
	
	
	
	
	

}
