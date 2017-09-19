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

	static MCP23017GpioProvider steuerungZug1;
	static MCP23017GpioProvider steuerungZug2;
	static MCP23017GpioProvider steuerungZug3;
	static MCP23017GpioProvider weiSig;
	static MCP23017GpioProvider lcdRelay;

	public void portExpanderErsteller() throws UnsupportedBusNumberException, IOException {

		steuerungZug1 = new MCP23017GpioProvider(I2CBus.BUS_1, 0x20);
		// steuerungZug2 = new MCP23017GpioProvider(I2CBus.BUS_1, 0x21);
		lcdRelay = new MCP23017GpioProvider(I2CBus.BUS_1, 0x22);
		weiSig = new MCP23017GpioProvider(I2CBus.BUS_1, 0x23);

	}

	/**
	 * Threads für Taster und andere eingaben erstellen
	 */
	public void threadErstellerEingang() {
		System.out.println("Threads erstellen");
		try {

			Thread tasterDrehregler = new Thread(new TasterSnifferDrehregler());
			Thread tasterSignalWeiche = new Thread(new TasterSnifferSignalWeiche());
			// Thread tasterProgramme = new Thread(new TasterSnifferProgramme());

			LedStatusZugHandler lsh = new LedStatusZugHandler();

			Thread.sleep(500);
			lsh.sch();

			tasterDrehregler.start();
			// tasterProgramme.start();
			tasterSignalWeiche.start();

		} catch (Exception r) {
			r.printStackTrace();
			System.out.println("thrad nicht erstellt");
			gpio.shutdown();
			gpio.unexportAll();
		}

	}

}
