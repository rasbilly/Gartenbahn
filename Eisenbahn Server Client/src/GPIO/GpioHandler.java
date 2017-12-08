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
import ServerHandler.Log;

public class GpioHandler {

	public GpioHandler() {
	}
	
	protected static GpioController gpio;

	protected static MCP23017GpioProvider expander1;
	protected static MCP23017GpioProvider expander2;
	protected static MCP23017GpioProvider expander3;
	protected static MCP23017GpioProvider expander4;

	public void portExpanderErsteller()  {
		try {
			gpio = GpioFactory.getInstance();
		} catch (Exception e) {
			System.out.println("lalal");
			Log.Error(getClass().getName(), "GPIO get Instance", e);
			
		}
		try {
			expander1 = new MCP23017GpioProvider(I2CBus.BUS_1, 0x20);
		} catch (Exception e) {
			Log.Error(getClass().getName(), "Expander 1", e);
		}
		try {
			expander2 = new MCP23017GpioProvider(I2CBus.BUS_1, 0x21);
		} catch (Exception e) {
			Log.Error(getClass().getName(), "Expander 2", e);
		}
		try {
			expander3 = new MCP23017GpioProvider(I2CBus.BUS_1, 0x22);
		} catch (Exception e) {
			Log.Error(getClass().getName(), "Expander 3", e);
		}
		try {
			expander4 = new MCP23017GpioProvider(I2CBus.BUS_1, 0x23);
		} catch (Exception e) {
			Log.Error(getClass().getName(), "Expander 4", e);
		}
	}

	/**
	 * Threads für Taster und andere eingaben erstellen
	 */
	public void threadErstellerEingang() {
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

		} catch (Exception e) {
			Log.Error(getClass().getName(), "GPIO Pins konnten nicht erstellt werden. Ports werden geschlossen", e);
			gpio.shutdown();
			gpio.unexportAll();
		}

	}

}
