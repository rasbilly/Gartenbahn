package Verwalter;

public class Gleisabschnitte {
	public static final Gleisabschnitte INSTANCE = new Gleisabschnitte();

	boolean[] gleisabschnitte = new boolean[6];
	public void ausgabe() {
		for(int i =0; i<gleisabschnitte.length;i++){
			System.out.println("Abschnitt "+i+": "+gleisabschnitte[i]);
		}
	}

	// * Gleisabbschnitt 0 = Bahnhof: --- 1,2,3
	// * Gleisabbschnitt 1 = Umfahrung Bahnhof: --- 4,5
	// * Gleisabbschnitt 2 = Kurve 1: --- 6,7
	// * Gleisabbschnitt 3 = Gerade (1): --- 8,9
	// * Gleisabbschnitt 4 = Gerade (2): --- 10,11
	// * Gleisabbschnitt 5 = Kurve 2: --- 12,13
	// * Gleisabbschnitt 6 = Abstellgleis: --- 14,15
	/**
	 * Aktualisiert die Gleisabschnitte und sperrt sie bzw andersrum
	 * 
	 * @param tags
	 */
	public void gleisStatusAktuallisieren(String[] tags) {
		for (int i = 0; i < tags.length; i++) {
			if ((tags[0] != null) || (tags[1] != null) || (tags[2] != null) || (tags[3] != null)) {
				gleisabschnitte[0] = true;
			} else {
				gleisabschnitte[0] = false;
			}
			if ((tags[4] != null) || (tags[5] != null)) {
				gleisabschnitte[1] = true;
			} else {
				gleisabschnitte[1] = false;
			}
			if ((tags[6] != null) || (tags[7] != null)) {
				gleisabschnitte[2] = true;
			} else {
				gleisabschnitte[2] = false;
			}
			if ((tags[8] != null) || (tags[9] != null)) {
				gleisabschnitte[3] = true;
			} else {
				gleisabschnitte[3] = false;
			}
			if ((tags[10] != null) || (tags[11] != null)) {
				gleisabschnitte[4] = true;
			} else {
				gleisabschnitte[4] = false;
			}
			if ((tags[12] != null) || (tags[13] != null)) {
				gleisabschnitte[5] = true;
			} else {
				gleisabschnitte[5] = false;
			}
			if ((tags[14] != null) || (tags[15] != null)) {
				gleisabschnitte[6] = true;
			} else {
				gleisabschnitte[6] = false;
			}
		}

	}
	
	

}
