package GPIO;

import java.io.IOException;

import com.pi4j.gpio.extension.mcp.MCP23017GpioProvider;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CFactory.UnsupportedBusNumberException;

import GPIO.digit.DigitHandler;
import GPIO.display.LcdDisplayHandler;
import GPIO.drehregler.DrehreglerHandler;
import GPIO.taster.TasterSnifferProgramme;
import GPIO.taster.TasterSnifferSignalWeiche;

public class GpioHandler {

	public GpioHandler() {
		// System.out.println("GPIO Handler");
	}

	protected static GpioController gpio = GpioFactory.getInstance();

	protected static MCP23017GpioProvider expander1;
	protected static MCP23017GpioProvider expander2;
	protected static MCP23017GpioProvider lcdRelay;
	protected static MCP23017GpioProvider weiSig;

	public void portExpanderErsteller() throws UnsupportedBusNumberException, IOException {
		try {
			expander1 = new MCP23017GpioProvider(I2CBus.BUS_1, 0x20);
		} catch (Exception e) {
			System.out.println("Fehler - Expander 1");
			e.printStackTrace();
		}
		try {
			expander2 = new MCP23017GpioProvider(I2CBus.BUS_1, 0x21);
		} catch (Exception e) {
			System.out.println("!!Fehler - Expander 2");
			e.printStackTrace();
		}
		try {
			lcdRelay = new MCP23017GpioProvider(I2CBus.BUS_1, 0x22);
		} catch (Exception e) {
			System.out.println("!!Fehler - Expander 3 lcdRelay");
			e.printStackTrace();
		}
		try {
			weiSig = new MCP23017GpioProvider(I2CBus.BUS_1, 0x23);
		} catch (Exception e) {
			System.out.println("!!Fehler - Expander 4 weiSig");
			e.printStackTrace();
		}
	}

	/**
	 * Threads für Taster und andere eingaben erstellen
	 */
	public void threadErstellerEingang() {
		System.out.println("Threads erstellen");
		try {
			LcdDisplayHandler.startLcdDisplay();
			
			
			DrehreglerHandler drehHand = new DrehreglerHandler();
			Thread tasterSignalWeiche = new Thread(new TasterSnifferSignalWeiche());
			Thread tasterProgramme = new Thread(new TasterSnifferProgramme());
			Thread magSensoren = new Thread(new MagnetSensoren());

			Thread.sleep(100);
			DigitHandler dh = new DigitHandler();
			dh.threadErsteller();

			

			drehHand.drehreglerErsteller();
			tasterSignalWeiche.start();
			tasterProgramme.start();
			magSensoren.start();

			

		} catch (Exception r) {
			r.printStackTrace();
			System.out.println("thrad nicht erstellt");
			gpio.shutdown();
			gpio.unexportAll();
		}

	}

}
