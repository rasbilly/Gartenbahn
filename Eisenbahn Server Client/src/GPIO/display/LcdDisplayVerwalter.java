package GPIO.display;

import java.text.SimpleDateFormat;
import java.util.Date;


import Programme.ProgrammHandler;

public class LcdDisplayVerwalter {

	static int statusZaehler = 0;

	public static void lcdSchalten() {

		if (statusZaehler < 2) {
			statusZaehler++;
		} else {
			statusZaehler = 0;
		}

		switch (statusZaehler) {
		case 0:
			aktuelleUhrzeit();
			break;
		case 1:
			modus();
			break;
		case 2:
			LcdDisplayHandler.changeLcdZeileEins("zwei");
			LcdDisplayHandler.changeLcdZeileZwei("");
			break;
//		case 3:
//			LcdDisplayHandler.changeLcdZeileEins("drei");
//			break;
//		case 4:
//			LcdDisplayHandler.changeLcdZeileEins("vier");
//			break;
		}

	}
	
	public static void modus() {
		LcdDisplayHandler.lcd.clear();
		LcdDisplayHandler.changeLcdZeileEins("Aktuell ist");
		new Thread(new Runnable() {
			@Override
			public void run() {
				String helper = null;
				while (statusZaehler == 1) {
					try {
						String s = ProgrammHandler.INSTANCE.proStatusAbfrage();
						if(helper!=s) {
						LcdDisplayHandler.changeLcdZeileZwei(s);
						helper = s;
						}
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				Thread.currentThread().interrupt();
				Thread.currentThread().stop();
			}
		}).start();
	}
	

	public static void aktuelleUhrzeit() {
		LcdDisplayHandler.lcd.clear();
		LcdDisplayHandler.changeLcdZeileEins("Datum/Uhrzeit");
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (statusZaehler == 0) {
					String zeit = new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date());
					LcdDisplayHandler.changeLcdZeileZwei(zeit);
					try {
						Thread.sleep(60000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				Thread.currentThread().interrupt();
			}
		}).start();
	}

}
