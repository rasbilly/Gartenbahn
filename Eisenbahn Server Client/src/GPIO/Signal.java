package GPIO;

import java.awt.Color;

import com.pi4j.gpio.extension.mcp.MCP23017Pin;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;

import GUI.Hauptmenu;
import ServerHandler.Log;


public class Signal extends GpioHandler {

	public Signal() {
		super();
	}

	// RELAY SIGNAL
	final GpioPinDigitalOutput signal1A = gpio.provisionDigitalOutputPin(expander3, MCP23017Pin.GPIO_B1,
			"Signal AN Relay", PinState.HIGH);
	final GpioPinDigitalOutput signal1B = gpio.provisionDigitalOutputPin(expander3, MCP23017Pin.GPIO_B0,
			"Signal AN Relay", PinState.HIGH);

	// LED SIGNAL
	public final GpioPinDigitalOutput ledSignalAn = gpio.provisionDigitalOutputPin(expander4, MCP23017Pin.GPIO_A6,
			"Led Signal An", PinState.LOW);
	public final GpioPinDigitalOutput ledSignalAus = gpio.provisionDigitalOutputPin(expander4, MCP23017Pin.GPIO_A7,
			"Led Signal Aus", PinState.LOW);

	public static final Signal SIGNAL = new Signal();

	private char statusSignal = 'g';

	/**
	 * Pins steuern Schaltbox an und überbrücken den Taster g = go s = stopp t =
	 * toogle
	 * 
	 * @param c
	 * @throws InterruptedException
	 */
	public void schalteSignal(char c) throws InterruptedException {
		if (c == 'g') {
			ledSignalAn.high();
			ledSignalAus.low();
			signal1A.low();
			signal1B.high();
			Thread.sleep(100);
			signal1A.low();
			signal1B.low();
			statusSignal = 'g';
			Log.Track(getClass().getName(), "Signal Grün");
			Hauptmenu.butSignal.setBackground(Color.green);

		} else if (c == 's') {
			ledSignalAn.low();
			ledSignalAus.high();
			signal1A.high();
			signal1B.low();
			Thread.sleep(100);
			signal1A.low();
			signal1B.low();
			statusSignal = 's';
			Log.Track(getClass().getName(), "Signal Rot");
			Hauptmenu.butSignal.setBackground(Color.red);

		} else if (c == 't') {
			if (statusSignal == 's') {
				schalteSignal('g');
			} else if (statusSignal == 'g') {
				schalteSignal('s');
			} else {
				Log.Warning(getClass().getName(), "Fehler Toggle","Signal", false);
			}
		} else {
			Log.Warning(getClass().getName(), "Fehlerhafte Stellung","Signal", false);
		}
	}

	public char getStatusSignal() {
		return statusSignal;
	}

}
