package GUI;


public class StatusWeichenSignal extends Hauptmenu{

	public static void schaltenWeiche1Gui(char c) {
		if (c == 'o') {
			canvasWeiche1_oben.setVisible(true);
			canvasWeiche1_unten.setVisible(false);
		} else if (c == 'u') {
			canvasWeiche1_unten.setVisible(true);
			canvasWeiche1_oben.setVisible(false);
		}
	}
	public static void schaltenWeiche2Gui(char c) {
		if (c == 'o') {
			canvasWeiche2_oben.setVisible(true);
			canvasWeiche2_unten.setVisible(false);
		} else if (c == 'u') {
			canvasWeiche2_unten.setVisible(true);
			canvasWeiche2_oben.setVisible(false);
		}
	}
	public static void schaltenWeiche3Gui(char c) {
		if (c == 'o') {
			canvasWeiche3_oben.setVisible(true);
			canvasWeiche3_unten.setVisible(false);
		} else if (c == 'u') {
			canvasWeiche3_unten.setVisible(true);
			canvasWeiche3_oben.setVisible(false);
		}
	}
	

}
