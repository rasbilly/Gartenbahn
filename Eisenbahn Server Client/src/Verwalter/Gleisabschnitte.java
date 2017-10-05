package Verwalter;

import java.awt.Color;

import GUI.GuiHandler;

public class Gleisabschnitte {
	public static final Gleisabschnitte INSTANCE = new Gleisabschnitte();

	boolean[] gleisabschnitte = new boolean[8];

	public boolean[] getGleisabschnitte() {
		return gleisabschnitte;
	}

	public void ausgabe() {
		for (int i = 1; i < gleisabschnitte.length; i++) {
			System.out.println("Abschnitt " + i + ": " + gleisabschnitte[i]);
		}
	}

	/*
	 * Gleisabbschnitt 1 = Bahnhof: --- 1,2,3 Gleisabbschnitt 2 = Umfahrung Bahnhof
	 * ---4,5 Gleisabbschnitt 3 = Kurve 1: --- 6,7 Gleisabbschnitt 4 = Gerade (1):
	 * --- 8,9 Gleisabbschnitt 5 = Gerade (2): --- 10,11 Gleisabbschnitt 6 = Kurve
	 * 2: --- 12,13 Gleisabbschnitt 7 = Abstellgleis: --- 14,15
	 */

	/**
	 * Aktualisiert die Gleisabschnitte und sperrt sie bzw andersrum
	 * 
	 * @param tags
	 */
	public void gleisStatusAktuallisieren(String[] tags) {
		try {
			if ((tags[1] != null) || (tags[2] != null) || (tags[3] != null)) {
				gleisabschnitte[1] = true;
				GuiHandler.h.canvasAbschnitt1.setBackground(Color.RED);
			} else {
				gleisabschnitte[1] = false;
				GuiHandler.h.canvasAbschnitt1.setBackground(Color.GREEN);
			}
			if ((tags[4] != null) || (tags[5] != null)) {
				gleisabschnitte[2] = true;
				GuiHandler.h.canvasAbschnitt2.setBackground(Color.RED);
			} else {
				gleisabschnitte[2] = false;
				GuiHandler.h.canvasAbschnitt2.setBackground(Color.GREEN);
			}
			if ((tags[6] != null) || (tags[7] != null)) {
				gleisabschnitte[3] = true;
				GuiHandler.h.canvasAbschnitt3.setBackground(Color.RED);
			} else {
				gleisabschnitte[3] = false;
				GuiHandler.h.canvasAbschnitt3.setBackground(Color.GREEN);
			}
			if ((tags[8] != null) || (tags[9] != null)) {
				gleisabschnitte[4] = true;
				GuiHandler.h.canvasAbschnitt4.setBackground(Color.RED);
			} else {
				gleisabschnitte[4] = false;
				GuiHandler.h.canvasAbschnitt4.setBackground(Color.GREEN);
			}
			if ((tags[10] != null) || (tags[11] != null)) {
				gleisabschnitte[5] = true;
				GuiHandler.h.canvasAbschnitt5.setBackground(Color.RED);
			} else {
				gleisabschnitte[5] = false;
				GuiHandler.h.canvasAbschnitt5.setBackground(Color.GREEN);
			}
			if ((tags[12] != null) || (tags[13] != null)) {
				gleisabschnitte[6] = true;
				GuiHandler.h.canvasAbschnitt6.setBackground(Color.RED);
				GuiHandler.h.canvasAbschnitt6_2.setBackground(Color.RED);
			} else {
				gleisabschnitte[6] = false;
				GuiHandler.h.canvasAbschnitt6.setBackground(Color.GREEN);
				GuiHandler.h.canvasAbschnitt6_2.setBackground(Color.GREEN);
			}
			if ((tags[14] != null) || (tags[15] != null)) {
				gleisabschnitte[7] = true;
				GuiHandler.h.canvasAbschnitt7.setBackground(Color.RED);
			} else {
				gleisabschnitte[7] = false;
				GuiHandler.h.canvasAbschnitt7.setBackground(Color.GREEN);
			}
		} catch (Exception e) {
			System.out.println("Gleisabschnitee??");
		}
	}

}
