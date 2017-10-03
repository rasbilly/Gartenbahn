package GUI;

import javax.swing.ImageIcon;


public class PositionZuege {//extends Hauptmenu {	
	
	ImageIcon zug1zur = new ImageIcon("resources/lokRechts.gif");
	ImageIcon zug1vor= new ImageIcon("resources/lok.gif");
	
	ImageIcon zug2zur = new ImageIcon("resources/lokRechts.gif");
	ImageIcon zug2vor= new ImageIcon("resources/lok.gif");
	//TODO Bildrichtung beim Rückwärtsfahren
	
//	JLabel zug1 = GUI.GuiHandler.INSTANCE.h.zug1;
//	JLabel zug2 = GUI.GuiHandler.INSTANCE.h.zug2;
	
	public void posZug1(int pos) {

		switch (pos) {
		case 0:
			GuiHandler.h.zug1.setBounds(1300, 350, 225, 180);
			GuiHandler.h.zug1.setIcon(zug1vor);
			break;
		case 1:
			GuiHandler.h.zug1.setBounds(733, 11, 225, 180);
			GuiHandler.h.zug1.setIcon(zug1vor);
			break;
		case 2:
			GuiHandler.h.zug1.setBounds(573, 11, 225, 180);
			GuiHandler.h.zug1.setIcon(zug1vor);
			break;
		case 3:
			GuiHandler.h.zug1.setBounds(450, 11, 225, 180);
			GuiHandler.h.zug1.setIcon(zug1vor);
			break;
		case 4:
			GuiHandler.h.zug1.setBounds(733, 72, 225, 180);
			GuiHandler.h.zug1.setIcon(zug1vor);
			break;
		case 5:
			GuiHandler.h.zug1.setBounds(485, 72, 225, 180);
			GuiHandler.h.zug1.setIcon(zug1vor);
			break;
//		case 6:
//			zug1.setBounds(243, 11, 225, 180);
//			zug1.setIcon(zug1vor);
//			break;
//		case 7:
//			zug1.setBounds(0, 220, 225, 180);
//			zug1.setIcon(zug1zur);
//			break;
//		case 8:
//			zug1.setBounds(0, 400, 225, 180);
//			zug1.setIcon(zug1zur);
//			break;
//		case 9:
//			zug1.setBounds(230, 488, 225, 180);
//			zug1.setIcon(zug1zur);
//			break;
//		case 10:
//			zug1.setBounds(518, 488, 225, 180);
//			zug1.setIcon(zug1zur);
//			break;
//		case 11:
//			zug1.setBounds(800, 488, 225, 180);
//			zug1.setIcon(zug1zur);
//			break;
//		case 12:
//			zug1.setBounds(1070, 425, 225, 180);
//			zug1.setIcon(zug1zur);
//			break;
//		case 13:
//			zug1.setBounds(1111, 161, 225, 180);
//			zug1.setIcon(zug1vor);
//			break;
//		case 14:
//			zug1.setBounds(525, 430, 225, 180);
//			zug1.setIcon(zug1zur);
//			break;
//		case 15:
//			zug1.setBounds(900, 395, 225, 180);
//			zug1.setIcon(zug1zur);
//			break;			
		}
	}
/*
	public void posZug2(int pos) {

		switch (pos) {
		case 0:
			zug2.setBounds(1331, 100, 183, 100);
			zug2.setIcon(zug2vor);
			break;
		case 1:
			zug2.setBounds(733, 11, 225, 180);
			zug2.setIcon(zug2vor);
			break;
		case 2:
			zug2.setBounds(573, 11, 225, 180);
			zug2.setIcon(zug2vor);
			break;
		case 3:
			zug2.setBounds(450, 11, 225, 180);
			zug2.setIcon(zug2vor);
			break;
		case 4:
			zug2.setBounds(733, 72, 225, 180);
			zug2.setIcon(zug2vor);
			break;
		case 5:
			zug2.setBounds(485, 72, 225, 180);
			zug2.setIcon(zug2vor);
			break;
		case 6:
			zug2.setBounds(243, 11, 225, 180);
			zug2.setIcon(zug2vor);
			break;
		case 7:
			zug2.setBounds(0, 220, 225, 180);
			zug2.setIcon(zug2zur);
			break;
		case 8:
			zug2.setBounds(0, 400, 225, 180);
			zug2.setIcon(zug2zur);
			break;
		case 9:
			zug2.setBounds(230, 488, 225, 180);
			zug2.setIcon(zug2zur);
			break;
		case 10:
			zug2.setBounds(518, 488, 225, 180);
			zug2.setIcon(zug2zur);
			break;
		case 11:
			zug2.setBounds(800, 488, 225, 180);
			zug2.setIcon(zug2zur);
			break;
		case 12:
			zug2.setBounds(1070, 425, 225, 180);
			zug2.setIcon(zug2zur);
			break;
		case 13:
			zug2.setBounds(1111, 161, 225, 180);
			zug2.setIcon(zug2vor);
			break;
		case 14:
			zug2.setBounds(525, 430, 225, 180);
			zug2.setIcon(zug2zur);
			break;
		case 15:
			zug2.setBounds(900, 395, 225, 180);
			zug2.setIcon(zug2zur);
			break;			
		}
	}*/
	
	


}
