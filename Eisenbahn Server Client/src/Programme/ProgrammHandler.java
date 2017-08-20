package Programme;

public class ProgrammHandler {
	
	/**
	 * Aktiviert das jeweilige Programm
	 * @param p
	 */
	public void programmWaehlen(int p){
		P1.INSTANCE.status = false;
		P2.INSTANCE.status = false;
		P3.INSTANCE.status = false;
		if(p == 1){
			P1.INSTANCE.status = true;
		}else if(p ==2){
			P2.INSTANCE.status = true;
		}else if(p == 3){
			P3.INSTANCE.status = true;
		}else{
			System.err.println("Kein Programm gefunden");
		}
	}
	
	
	
	/**
	 * Fragt ab welches Programm gerade aktive ist
	 * @return 
	 */
	public String proStatusAbfrage(){
		if(P1.INSTANCE.status==true){
			return "Programm 1 Aktive";
		}else if (P2.INSTANCE.status==true){
			return"Programm 2 Aktive";
		}else if (P3.INSTANCE.status==true){
			return"Programm 3 Aktive";
		}
		return "Manuelle Fahrt";
	}
	
	
	
	
	

}
