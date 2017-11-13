package GPIO.display;

import com.pi4j.component.lcd.impl.I2CLcdDisplay;
import com.pi4j.io.i2c.I2CBus;

public class LcdDisplayHandler {

	static I2CLcdDisplay lcd;

	public static I2CLcdDisplay getLcd() {
		return lcd;
	}

	public static void setLcd(I2CLcdDisplay lcd) {
		LcdDisplayHandler.lcd = lcd;
	}

	public static void changeLcdZeileEins(String msg) {
		lcd.setCursorPosition(0, 0);
		lcd.write("                ");
		lcd.setCursorPosition(0, 0);
		lcd.write(msg);
	}

	public static void changeLcdZeileZwei(String msg) {
		lcd.setCursorPosition(1, 0);
		lcd.write("                 ");
		lcd.setCursorPosition(1, 0);
		lcd.write(msg);
	}

	public static void startLcdDisplay() {
		try {
			lcd = new I2CLcdDisplay(2, 21, I2CBus.BUS_1, 0x27, 3, 0, 1, 2, 7, 6, 5, 4);
			lcd.setCursorHome();
			ladeProgramm();
		} catch (Exception e) {
			System.out.println("LCD Display konnte nicht gestratet werden");
			e.printStackTrace();
		}

	}

	public static void ladeProgramm() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					lcd.write("Starte Programm");
					Thread.sleep(1100);
					for (int i = 0; i <= 15; i++) {
						lcd.setCursorPosition(1, i);
						lcd.write(".");
						Thread.sleep(410);
					}
				} catch (InterruptedException e) {

				}
				lcd.clear();
				changeLcdZeileEins("Gartenbahn");
				lcd.setCursorPosition(1, 12);
				lcd.write("v1.0");
			}
		}).start();
	}

}