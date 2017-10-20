package GPIO;

public class LcdDisplayVerwalter {

	static int statusZaehler;

	public static void lcdSchalten() {

		if (statusZaehler < 4) {
			statusZaehler++;
		} else {
			statusZaehler = 0;
		}

		switch (statusZaehler) {
		case 0:
			LcdDisplayHandler.changeLcdZeileEins("null");
		case 1:
			LcdDisplayHandler.changeLcdZeileEins("eins");
		case 2:
			LcdDisplayHandler.changeLcdZeileEins("zwei");
		case 3:
			LcdDisplayHandler.changeLcdZeileEins("drei");
		case 4:
			LcdDisplayHandler.changeLcdZeileEins("vier");
		}

	}

}
