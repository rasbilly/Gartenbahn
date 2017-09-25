package Verwalter;

public class Gleisabschnitte {
	public static final Gleisabschnitte INSTANCE = new Gleisabschnitte();

	boolean[] gleisabschnitte = new boolean[8];
	public void ausgabe() {
		for(int i =0; i<gleisabschnitte.length;i++){
			System.out.println("Abschnitt "+i+": "+gleisabschnitte[i]);
		}
	}

	// * Gleisabbschnitt 1 = Bahnhof: --- 1,2,3
	// * Gleisabbschnitt 2 = Umfahrung Bahnhof: --- 4,5
	// * Gleisabbschnitt 3 = Kurve 1: --- 6,7
	// * Gleisabbschnitt 4 = Gerade (1): --- 8,9
	// * Gleisabbschnitt 5 = Gerade (2): --- 10,11
	// * Gleisabbschnitt 6 = Kurve 2: --- 12,13
	// * Gleisabbschnitt 7 = Abstellgleis: --- 14,15
	/**
	 * Aktualisiert die Gleisabschnitte und sperrt sie bzw andersrum
	 * 
	 * @param tags
	 */
	public void gleisStatusAktuallisieren(String[] tags) {
		for (int i = 1; i < tags.length; i++) {

			if(tags[2]!=null) {
				System.out.println("hier 2");
			}if(tags[5]!=null) {
				System.out.println("hier 5");
			}
			
			if ((tags[1] != null) || (tags[2] != null) || (tags[3] != null)) {
				gleisabschnitte[1] = true;
			} 
			else {
				gleisabschnitte[1] = false;
			}
			if ((tags[4] != null) || (tags[5] != null)) {
				gleisabschnitte[2] = true;
			} else {
				gleisabschnitte[2] = false;
			}/*
			if ((tags[6] != null) || (tags[7] != null)) {
				gleisabschnitte[3] = true;
			} else {
				gleisabschnitte[3] = false;
			}
			if ((tags[8] != null) || (tags[9] != null)) {
				gleisabschnitte[4] = true;
			} else {
				gleisabschnitte[4] = false;
			}
			if ((tags[10] != null) || (tags[11] != null)) {
				gleisabschnitte[5] = true;
			} else {
				gleisabschnitte[5] = false;
			}
			if ((tags[12] != null) || (tags[13] != null)) {
				gleisabschnitte[6] = true;
			} else {
				gleisabschnitte[6] = false;
			}
			if ((tags[14] != null) || (tags[15] != null)) {
				gleisabschnitte[7] = true;
			} else {
				gleisabschnitte[7] = false;
			}*/
			System.out.println(gleisabschnitte[i]);
		}

	}
	
	

}
