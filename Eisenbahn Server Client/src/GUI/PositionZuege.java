package GUI;

import javax.swing.ImageIcon;


public class PositionZuege {	
	ImageIcon picAnnaLinks = new ImageIcon("resources/Zug/annaLinks.gif");
	ImageIcon picAnnaRechts= new ImageIcon("resources/Zug/annaRechts.gif");
	
	ImageIcon picZug2Links = new ImageIcon("resources/Zug/zug2links.gif");
	ImageIcon picZug2Rechts= new ImageIcon("resources/Zug/zug2rechts.gif");
	
	ImageIcon picZug3Links = new ImageIcon("resources/Zug/zug3links.gif");
	ImageIcon picZug3Rechts= new ImageIcon("resources/Zug/zug3Rechts.gif");
	
	//TODO Bildrichtung beim Rückwärtsfahren
	
	
	public void posZug1(int pos) {
		switch (pos) {
		case 0:
			GuiHandler.h.zug1.setBounds(235, 670, 208, 135);
			GuiHandler.h.zug1.setIcon(picAnnaRechts);
			break;
		case 1:
			GuiHandler.h.zug1.setBounds(733, 11, 225, 180);
			GuiHandler.h.zug1.setIcon(picAnnaLinks);
			break;
		case 2:
			GuiHandler.h.zug1.setBounds(573, 11, 225, 180);
			GuiHandler.h.zug1.setIcon(picAnnaLinks);
			break;
		case 3:
			GuiHandler.h.zug1.setBounds(450, 11, 225, 180);
			GuiHandler.h.zug1.setIcon(picAnnaLinks);
			break;
		case 4:
			GuiHandler.h.zug1.setBounds(733, 72, 225, 180);
			GuiHandler.h.zug1.setIcon(picAnnaLinks);
			break;
		case 5:
			GuiHandler.h.zug1.setBounds(485, 72, 225, 180);
			GuiHandler.h.zug1.setIcon(picAnnaLinks);
			break;
		case 6:
			GuiHandler.h.zug1.setBounds(243, 11, 225, 180);
			GuiHandler.h.zug1.setIcon(picAnnaLinks);
			break;
		case 7:
			GuiHandler.h.zug1.setBounds(0, 220, 225, 180);
			GuiHandler.h.zug1.setIcon(picAnnaRechts);
			break;
		case 8:
			GuiHandler.h.zug1.setBounds(0, 400, 225, 180);
			GuiHandler.h.zug1.setIcon(picAnnaRechts);
			break;
		case 9:
			GuiHandler.h.zug1.setBounds(230, 488, 225, 180);
			GuiHandler.h.zug1.setIcon(picAnnaRechts);
			break;
		case 10:
			GuiHandler.h.zug1.setBounds(518, 488, 225, 180);
			GuiHandler.h.zug1.setIcon(picAnnaRechts);
			break;
		case 11:
			GuiHandler.h.zug1.setBounds(800, 488, 225, 180);
			GuiHandler.h.zug1.setIcon(picAnnaRechts);
			break;
		case 12:
			GuiHandler.h.zug1.setBounds(1070, 425, 225, 180);
			GuiHandler.h.zug1.setIcon(picAnnaRechts);
			break;
		case 13:
			GuiHandler.h.zug1.setBounds(1111, 161, 225, 180);
			GuiHandler.h.zug1.setIcon(picAnnaLinks);
			break;
		case 14:
			GuiHandler.h.zug1.setBounds(525, 430, 225, 180);
			GuiHandler.h.zug1.setIcon(picAnnaRechts);
			break;
		case 15:
			GuiHandler.h.zug1.setBounds(900, 395, 225, 180);
			GuiHandler.h.zug1.setIcon(picAnnaRechts);
			break;			
		}
	}
	
	public void posZug2(int pos) {
		switch (pos) {
		case 0:
			GuiHandler.h.zug2.setBounds(453, 680, 220, 127);
			GuiHandler.h.zug2.setIcon(picZug2Rechts);
			break;
		case 1:
			GuiHandler.h.zug2.setBounds(733, 11, 225, 180);
			GuiHandler.h.zug2.setIcon(picZug2Links);
			break;
		case 2:
			GuiHandler.h.zug2.setBounds(573, 11, 225, 180);
			GuiHandler.h.zug2.setIcon(picZug2Links);
			break;
		case 3:
			GuiHandler.h.zug2.setBounds(450, 11, 225, 180);
			GuiHandler.h.zug2.setIcon(picZug2Links);
			break;
		case 4:
			GuiHandler.h.zug2.setBounds(733, 72, 225, 180);
			GuiHandler.h.zug2.setIcon(picZug2Links);
			break;
		case 5:
			GuiHandler.h.zug2.setBounds(485, 72, 225, 180);
			GuiHandler.h.zug2.setIcon(picZug2Links);
			break;
		case 6:
			GuiHandler.h.zug2.setBounds(243, 11, 225, 180);
			GuiHandler.h.zug2.setIcon(picZug2Links);
			break;
		case 7:
			GuiHandler.h.zug2.setBounds(0, 220, 225, 180);
			GuiHandler.h.zug2.setIcon(picZug2Rechts);
			break;
		case 8:
			GuiHandler.h.zug2.setBounds(0, 400, 225, 180);
			GuiHandler.h.zug2.setIcon(picZug2Rechts);
			break;
		case 9:
			GuiHandler.h.zug2.setBounds(230, 488, 225, 180);
			GuiHandler.h.zug2.setIcon(picZug2Rechts);
			break;
		case 10:
			GuiHandler.h.zug2.setBounds(518, 488, 225, 180);
			GuiHandler.h.zug2.setIcon(picZug2Rechts);
			break;
		case 11:
			GuiHandler.h.zug2.setBounds(800, 488, 225, 180);
			GuiHandler.h.zug2.setIcon(picZug2Rechts);
			break;
		case 12:
			GuiHandler.h.zug2.setBounds(1070, 425, 225, 180);
			GuiHandler.h.zug2.setIcon(picZug2Rechts);
			break;
		case 13:
			GuiHandler.h.zug2.setBounds(1111, 161, 225, 180);
			GuiHandler.h.zug2.setIcon(picZug2Links);
			break;
		case 14:
			GuiHandler.h.zug2.setBounds(525, 430, 225, 180);
			GuiHandler.h.zug2.setIcon(picZug2Rechts);
			break;
		case 15:
			GuiHandler.h.zug2.setBounds(900, 395, 225, 180);
			GuiHandler.h.zug2.setIcon(picZug2Rechts);
			break;			
		}
	}
	
	
	public void posZug3(int pos) {
		switch (pos) {
		case 0:
			GuiHandler.h.zug3.setBounds(683, 680, 220, 127);
			GuiHandler.h.zug3.setIcon(picZug3Rechts);
			break;
		case 1:
			GuiHandler.h.zug3.setBounds(733, 11, 225, 180);
			GuiHandler.h.zug3.setIcon(picZug3Links);
			break;
		case 2:
			GuiHandler.h.zug3.setBounds(573, 11, 225, 180);
			GuiHandler.h.zug3.setIcon(picZug3Links);
			break;
		case 3:
			GuiHandler.h.zug3.setBounds(450, 11, 225, 180);
			GuiHandler.h.zug3.setIcon(picZug3Links);
			break;
		case 4:
			GuiHandler.h.zug3.setBounds(733, 72, 225, 180);
			GuiHandler.h.zug3.setIcon(picZug3Links);
			break;
		case 5:
			GuiHandler.h.zug3.setBounds(485, 72, 225, 180);
			GuiHandler.h.zug3.setIcon(picZug3Links);
			break;
		case 6:
			GuiHandler.h.zug3.setBounds(243, 11, 225, 180);
			GuiHandler.h.zug3.setIcon(picZug3Links);
			break;
		case 7:
			GuiHandler.h.zug3.setBounds(0, 220, 225, 180);
			GuiHandler.h.zug3.setIcon(picZug3Rechts);
			break;
		case 8:
			GuiHandler.h.zug3.setBounds(0, 400, 225, 180);
			GuiHandler.h.zug3.setIcon(picZug3Rechts);
			break;
		case 9:
			GuiHandler.h.zug3.setBounds(230, 488, 225, 180);
			GuiHandler.h.zug3.setIcon(picZug3Rechts);
			break;
		case 10:
			GuiHandler.h.zug3.setBounds(518, 488, 225, 180);
			GuiHandler.h.zug3.setIcon(picZug3Rechts);
			break;
		case 11:
			GuiHandler.h.zug3.setBounds(800, 488, 225, 180);
			GuiHandler.h.zug3.setIcon(picZug3Rechts);
			break;
		case 12:
			GuiHandler.h.zug3.setBounds(1070, 425, 225, 180);
			GuiHandler.h.zug3.setIcon(picZug3Rechts);
			break;
		case 13:
			GuiHandler.h.zug3.setBounds(1111, 161, 225, 180);
			GuiHandler.h.zug3.setIcon(picZug3Links);
			break;
		case 14:
			GuiHandler.h.zug3.setBounds(525, 430, 225, 180);
			GuiHandler.h.zug3.setIcon(picZug3Rechts);
			break;
		case 15:
			GuiHandler.h.zug3.setBounds(900, 395, 225, 180);
			GuiHandler.h.zug3.setIcon(picZug3Rechts);
			break;			
		}
	}

	
	


}
