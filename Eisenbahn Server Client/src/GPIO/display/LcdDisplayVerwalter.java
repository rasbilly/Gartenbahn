package GPIO.display;

public class LcdDisplayVerwalter {

	static int statusZaehler = 0;

	public static void lcdSchalten() {

		if (statusZaehler < 4) {
			statusZaehler++;
			System.out.println(statusZaehler);
		} else {
			statusZaehler = 0;
		}

		switch (statusZaehler) {
		case 0:
			LcdDisplayHandler.changeLcdZeileEins("null");break;
		case 1:
			LcdDisplayHandler.changeLcdZeileEins("eins");break;
		case 2:
			LcdDisplayHandler.changeLcdZeileEins("zwei");break;
		case 3:
			LcdDisplayHandler.changeLcdZeileEins("drei");break;
		case 4:
			LcdDisplayHandler.changeLcdZeileEins("vier");break;
		}

	}

}
