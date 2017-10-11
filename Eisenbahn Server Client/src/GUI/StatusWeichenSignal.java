package GUI;


public class StatusWeichenSignal extends Hauptmenu{

	public static void schaltenWeiche1Gui(char c) {
		if (c == 'r') {
			canvasWeiche1_oben.setVisible(false);
			canvasWeiche1_unten.setVisible(true);
		} else if (c == 'l') {
			canvasWeiche1_unten.setVisible(false);
			canvasWeiche1_oben.setVisible(true);
		}
	}
	public static void schaltenWeiche2Gui(char c) {
		if (c == 'l') {
			canvasWeiche2_oben.setVisible(false);
			canvasWeiche2_unten.setVisible(true);
		} else if (c == 'r') {
			canvasWeiche2_unten.setVisible(false);
			canvasWeiche2_oben.setVisible(true);
		}
	}
	public static void schaltenWeiche3Gui(char c) {
		if (c == 'l') {
			canvasWeiche3_oben.setVisible(false);
			canvasWeiche3_unten.setVisible(true);
		} else if (c == 'r') {
			canvasWeiche3_unten.setVisible(false);
			canvasWeiche3_oben.setVisible(true);
		}
	}
	

}
