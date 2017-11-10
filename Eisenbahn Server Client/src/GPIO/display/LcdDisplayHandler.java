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
		lcd.clear();
		lcd.write(msg);
	}

	public static void changeLcdZeileZwei(String msg) {
		lcd.setCursorPosition(1, 0);
		lcd.write(msg);
	}

	public static void startLcdDisplay() {
		try {
			lcd = new I2CLcdDisplay(2, 21, I2CBus.BUS_1, 0x27, 3, 0, 1, 2, 7, 6, 5, 4);
			lcd.setCursorHome();
			lcd.write("Gartenbahn");
		} catch (Exception e) {
			System.out.println("LCD Display konnte nicht gestratet werden");
			e.printStackTrace();
		}

	}
}