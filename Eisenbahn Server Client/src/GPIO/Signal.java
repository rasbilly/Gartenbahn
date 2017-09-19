package GPIO;

import com.pi4j.gpio.extension.mcp.MCP23017Pin;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;

public class Signal extends GpioHandler {
	
	public Signal() {
		super();
	}
	
	//0x23 A	
	//RELAY SIGNAL
	final GpioPinDigitalOutput SignalAN = gpio.provisionDigitalOutputPin(lcdRelay, MCP23017Pin.GPIO_B0, "Signal AN Relay", PinState.HIGH);
	final GpioPinDigitalOutput SignalAUS = gpio.provisionDigitalOutputPin(lcdRelay, MCP23017Pin.GPIO_B1, "Signal AUS Relay", PinState.HIGH);
	//LED SIGNAL
	final GpioPinDigitalOutput ledSignalAn = gpio.provisionDigitalOutputPin(weiSig, MCP23017Pin.GPIO_A6, "Led Signal An", PinState.LOW);
	final GpioPinDigitalOutput ledSignalAus = gpio.provisionDigitalOutputPin(weiSig, MCP23017Pin.GPIO_A7, "Led Signal Aus", PinState.LOW);
	
	public static final Signal SIGNAL = new Signal();
	
	private char statusSignal = 'g';
	
	/**
	 * Pins steuern Schaltbox an und �berbr�cken den Taster
	 * g = go
	 * s = stopp
	 * t = toogle
	 * @param c
	 * @throws InterruptedException
	 */
	public void schalteSignal(char c) throws InterruptedException {
		if (c == 'g') {
			ledSignalAn.high();
			ledSignalAus.low();
			SignalAN.low();
			Thread.sleep(100);
			SignalAN.high();
			statusSignal = 'g';
			System.out.println("Signal Gr�n");

		} else if (c == 's') {
			ledSignalAn.low();
			ledSignalAus.high();
			SignalAUS.low();
			Thread.sleep(100);
			SignalAUS.high();
			statusSignal = 's';
			System.out.println("Signal Rot");

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
