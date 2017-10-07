package GUI;

import java.awt.Color;

public class StatusWeichenSignal extends Hauptmenu{

	public static void schaltenWeiche1Gui(char c) {
		if (c == 'o') {
			canvasWeiche1_oben.setBackground(Color.GREEN);
			canvasWeiche1_unten.setBackground(Color.RED);
		} else if (c == 'u') {
			canvasWeiche1_oben.setBackground(Color.RED);
			canvasWeiche1_unten.setBackground(Color.GREEN);
		}
	}
	public static void schaltenWeiche2Gui(char c) {
		if (c == 'o') {
			canvasWeiche2_oben.setBackground(Color.GREEN);
			canvasWeiche2_unten.setBackground(Color.RED);
		} else if (c == 'u') {
			canvasWeiche2_oben.setBackground(Color.RED);
			canvasWeiche2_unten.setBackground(Color.GREEN);
		}
	}
	public static void schaltenWeiche3Gui(char c) {
		if (c == 'o') {
			canvasWeiche3_oben.setBackground(Color.GREEN);
			canvasWeiche3_unten.setBackground(Color.RED);
		} else if (c == 'u') {
			canvasWeiche3_oben.setBackground(Color.RED);
			canvasWeiche3_unten.setBackground(Color.GREEN);
		}
	}
	public static void schaltenSignalGui(char c) {
		if (c == 'o') {
			butSignal.setBackground(Color.GREEN);
			butSignal.setBackground(Color.RED);
		} else if (c == 'u') {
			butSignal.setBackground(Color.RED);
			butSignal.setBackground(Color.GREEN);
		}
	}

}
