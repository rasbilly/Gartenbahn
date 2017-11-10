package GPIO;

import java.awt.Color;

import com.pi4j.gpio.extension.mcp.MCP23017Pin;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;

import GUI.Hauptmenu;


public class Signal extends GpioHandler {

	public Signal() {
		super();
	}

	// RELAY SIGNAL
	final GpioPinDigitalOutput signalRelay = gpio.provisionDigitalOutputPin(lcdRelay, MCP23017Pin.GPIO_B1,
			"Signal AN Relay", PinState.HIGH);

	// LED SIGNAL
	public final GpioPinDigitalOutput ledSignalAn = gpio.provisionDigitalOutputPin(weiSig, MCP23017Pin.GPIO_A6,
			"Led Signal An", PinState.LOW);
	public final GpioPinDigitalOutput ledSignalAus = gpio.provisionDigitalOutputPin(weiSig, MCP23017Pin.GPIO_A7,
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
			signalRelay.low();
			statusSignal = 'g';
			System.out.println("Signal Grün");
			Hauptmenu.butSignal.setBackground(Color.green);

		} else if (c == 's') {
			ledSignalAn.low();
			ledSignalAus.high();
			signalRelay.high();
			statusSignal = 's';
			System.out.println("Signal Rot");
			Hauptmenu.butSignal.setBackground(Color.red);

		} else if (c == 't') {
			if (statusSignal == 's') {
				schalteSignal('g');
			} else if (statusSignal == 'g') {
				schalteSignal('s');
			} else {
				System.err.println("Signal - Fehler Toggle");
			}
		} else {
			System.err.println("Signal - Fehlerhafte Stellung");
		}
	}

	public char getStatusSignal() {
		return statusSignal;
	}

}
