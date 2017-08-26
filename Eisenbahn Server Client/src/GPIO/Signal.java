package GPIO;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class Signal extends GpioHandler {
	
	public Signal() {
		super();
	}
	final GpioPinDigitalOutput SignalAN = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_25, "Signal AN", PinState.HIGH);
	final GpioPinDigitalOutput SignalAUS = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_26, "Signal AUS", PinState.HIGH);
	
	public static final Signal SIGNAL = new Signal();
	
	private char statusSignal = 'g';
	
	/**
	 * Pins steuern Schaltbox an und überbrücken den Taster
	 * g = go
	 * s = stopp
	 * t = toogle
	 * @param c
	 * @throws InterruptedException
	 */
	public void schalteSignal(char c) throws InterruptedException {
		if (c == 'g') {
			SignalAN.low();
			Thread.sleep(100);
			SignalAN.high();
			statusSignal = 'g';

		} else if (c == 's') {
			SignalAUS.low();
			Thread.sleep(100);
			SignalAUS.high();
			statusSignal = 's';

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
