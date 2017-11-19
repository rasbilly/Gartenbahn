package GUI;

//import javax.swing.ImageIcon;

import ServerHandler.Zug;

public class PositionZuege extends Hauptmenu {
	//public static final PositionZuege INSTANCE = new PositionZuege();

//	ImageIcon picAnnaLinks = new ImageIcon(getClass().getResource("/resources/annaLinks.gif"));
//	 ImageIcon picAnnaRechts = new ImageIcon(getClass().getResource("/resources/annaRechts.gif"));
//
//	ImageIcon picZug2Links = new ImageIcon(getClass().getResource("/resources/zug2links.gif"));
//	ImageIcon picZug2Rechts = new ImageIcon(getClass().getResource("/resources/zug2rechts.gif"));
//
//	ImageIcon picZug3Links = new ImageIcon(getClass().getResource("/resources/zug3links.gif"));
//	ImageIcon picZug3Rechts = new ImageIcon(getClass().getResource("/resources/zug3links.gif"));

	// TODO Bildrichtung beim Rückwärtsfahren

	public void zugFinden(Zug zug, int uid) {
		System.out.println("METHODE");
		if (zug.getZugId().equals("Anna")) {
			posZug1(uid);
			System.out.println("HIER INNEN");
		}else if(zug.getZugId().equals("Lgb")) {
			//posZug2(uid);
		}else if(zug.getZugId().equals("DB")) {
		//	posZug3(uid);
		}
	}

	public void posZug1(int pos) {
		switch (pos) {
		case 0:
			zug1.setBounds(235, 670, 208, 135);
			zug1.setIcon(picAnnaRechts);
			break;
		case 1:
			zug1.setBounds(733, 11, 225, 180);
			zug1.setIcon(picAnnaLinks);
			break;
		case 2:
			zug1.setBounds(573, 11, 225, 180);
			zug1.setIcon(picAnnaLinks);
			break;
		case 3:
			zug1.setBounds(450, 11, 225, 180);
			zug1.setIcon(picAnnaLinks);
			break;
		case 4:
			zug1.setBounds(733, 72, 225, 180);
			zug1.setIcon(picAnnaLinks);
			break;
		case 5:
			zug1.setBounds(485, 72, 225, 180);
			zug1.setIcon(picAnnaLinks);
			break;
		case 6:
			zug1.setBounds(243, 11, 225, 180);
			zug1.setIcon(picAnnaLinks);
			break;
		case 7:
			zug1.setBounds(0, 220, 225, 180);
			zug1.setIcon(picAnnaRechts);
			break;
		case 8:
			zug1.setBounds(0, 400, 225, 180);
			zug1.setIcon(picAnnaRechts);
			break;
		case 9:
			zug1.setBounds(230, 488, 225, 180);
			zug1.setIcon(picAnnaRechts);
			break;
		case 10:
			zug1.setBounds(518, 488, 225, 180);
			zug1.setIcon(picAnnaRechts);
			break;
		case 11:
			zug1.setBounds(800, 488, 225, 180);
			zug1.setIcon(picAnnaRechts);
			break;
		case 12:
			zug1.setBounds(1070, 425, 225, 180);
			zug1.setIcon(picAnnaRechts);
			break;
		case 13:
			zug1.setBounds(1111, 161, 225, 180);
			zug1.setIcon(picAnnaLinks);
			break;
		case 14:
			zug1.setBounds(525, 430, 225, 180);
			zug1.setIcon(picAnnaRechts);
			break;
		case 15:
			zug1.setBounds(900, 395, 225, 180);
			zug1.setIcon(picAnnaRechts);
			break;
		}
	}

	public void posZug2(int pos) {
		switch (pos) {
		case 0:
			zug2.setBounds(453, 680, 220, 127);
			zug2.setIcon(picZug2Rechts);
			break;
		case 1:
			zug2.setBounds(733, 11, 225, 180);
			zug2.setIcon(picZug2Links);
			break;
		case 2:
			zug2.setBounds(573, 11, 225, 180);
			zug2.setIcon(picZug2Links);
			break;
		case 3:
			zug2.setBounds(450, 11, 225, 180);
			zug2.setIcon(picZug2Links);
			break;
		case 4:
			zug2.setBounds(733, 72, 225, 180);
			zug2.setIcon(picZug2Links);
			break;
		case 5:
			zug2.setBounds(485, 72, 225, 180);
			zug2.setIcon(picZug2Links);
			break;
		case 6:
			zug2.setBounds(243, 11, 225, 180);
			zug2.setIcon(picZug2Links);
			break;
		case 7:
			zug2.setBounds(0, 220, 225, 180);
			zug2.setIcon(picZug2Rechts);
			break;
		case 8:
			zug2.setBounds(0, 400, 225, 180);
			zug2.setIcon(picZug2Rechts);
			break;
		case 9:
			zug2.setBounds(230, 488, 225, 180);
			zug2.setIcon(picZug2Rechts);
			break;
		case 10:
			zug2.setBounds(518, 488, 225, 180);
			zug2.setIcon(picZug2Rechts);
			break;
		case 11:
			zug2.setBounds(800, 488, 225, 180);
			zug2.setIcon(picZug2Rechts);
			break;
		case 12:
			zug2.setBounds(1070, 425, 225, 180);
			zug2.setIcon(picZug2Rechts);
			break;
		case 13:
			zug2.setBounds(1111, 161, 225, 180);
			zug2.setIcon(picZug2Links);
			break;
		case 14:
			zug2.setBounds(525, 430, 225, 180);
			zug2.setIcon(picZug2Rechts);
			break;
		case 15:
			zug2.setBounds(900, 395, 225, 180);
			zug2.setIcon(picZug2Rechts);
			break;
		}
	}

	public void posZug3(int pos) {
		switch (pos) {
		case 0:
			zug3.setBounds(683, 680, 220, 127);
			zug3.setIcon(picZug3Rechts);
			break;
		case 1:
			zug3.setBounds(733, 11, 225, 180);
			zug3.setIcon(picZug3Links);
			break;
		case 2:
			zug3.setBounds(573, 11, 225, 180);
			zug3.setIcon(picZug3Links);
			break;
		case 3:
			zug3.setBounds(450, 11, 225, 180);
			zug3.setIcon(picZug3Links);
			break;
		case 4:
			zug3.setBounds(733, 72, 225, 180);
			zug3.setIcon(picZug3Links);
			break;
		case 5:
			zug3.setBounds(485, 72, 225, 180);
			zug3.setIcon(picZug3Links);
			break;
		case 6:
			zug3.setBounds(243, 11, 225, 180);
			zug3.setIcon(picZug3Links);
			break;
		case 7:
			zug3.setBounds(0, 220, 225, 180);
			zug3.setIcon(picZug3Rechts);
			break;
		case 8:
			zug3.setBounds(0, 400, 225, 180);
			zug3.setIcon(picZug3Rechts);
			break;
		case 9:
			zug3.setBounds(230, 488, 225, 180);
			zug3.setIcon(picZug3Rechts);
			break;
		case 10:
			zug3.setBounds(518, 488, 225, 180);
			zug3.setIcon(picZug3Rechts);
			break;
		case 11:
			zug3.setBounds(800, 488, 225, 180);
			zug3.setIcon(picZug3Rechts);
			break;
		case 12:
			zug3.setBounds(1070, 425, 225, 180);
			zug3.setIcon(picZug3Rechts);
			break;
		case 13:
			zug3.setBounds(1111, 161, 225, 180);
			zug3.setIcon(picZug3Links);
			break;
		case 14:
			zug3.setBounds(525, 430, 225, 180);
			zug3.setIcon(picZug3Rechts);
			break;
		case 15:
			zug3.setBounds(900, 395, 225, 180);
			zug3.setIcon(picZug3Rechts);
			break;
		}
	}

}
