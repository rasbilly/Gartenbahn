package GPIO;

import java.io.IOException;

import com.pi4j.gpio.extension.mcp.MCP23017GpioProvider;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CFactory.UnsupportedBusNumberException;

public class GpioHandler {

	public GpioHandler() {
		//System.out.println("GPIO Handler");
	}

	static GpioController gpio = GpioFactory.getInstance();

	static MCP23017GpioProvider expander1;
	static MCP23017GpioProvider expander2;
	static MCP23017GpioProvider lcdRelay;
	static MCP23017GpioProvider weiSig;
	
	
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

			Thread tasterDrehregler = new Thread(new TasterSnifferDrehregler());
			Thread tasterSignalWeiche = new Thread(new TasterSnifferSignalWeiche());
			Thread tasterProgramme = new Thread(new TasterSnifferProgramme());
			Thread magSensoren = new Thread(new MagnetSensoren());
			
			LedStatusZugHandler lsh = new LedStatusZugHandler();

			Thread.sleep(100);
			lsh.sch();

			tasterDrehregler.start();
			tasterSignalWeiche.start();
			tasterProgramme.start();
			magSensoren.start();
			
			LcdDisplayHandler.startLcdDisplay();
			

		} catch (Exception r) {
			r.printStackTrace();
			System.out.println("thrad nicht erstellt");
			gpio.shutdown();
			gpio.unexportAll();
		}

	}

}
