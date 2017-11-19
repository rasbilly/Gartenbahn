package GPIO;

import com.pi4j.gpio.extension.mcp.MCP23017Pin;
import com.pi4j.io.gpio.GpioPinDigitalOutput;

import com.pi4j.io.gpio.PinState;

import GUI.StatusWeichenSignal;

public class Weichen extends GpioHandler {

	public Weichen() {
		super();
	}

	
	//RELAY WEICHEN
	final GpioPinDigitalOutput weiche1A = gpio.provisionDigitalOutputPin(expander3, MCP23017Pin.GPIO_B3,
			"Weiche 1", PinState.HIGH);
	final GpioPinDigitalOutput weiche1B = gpio.provisionDigitalOutputPin(expander3, MCP23017Pin.GPIO_B2,
			"Weiche 1", PinState.HIGH);
	final GpioPinDigitalOutput weiche2A = gpio.provisionDigitalOutputPin(expander3, MCP23017Pin.GPIO_B5,
			"Weiche 2", PinState.HIGH);
	final GpioPinDigitalOutput weiche2B = gpio.provisionDigitalOutputPin(expander3, MCP23017Pin.GPIO_B4,
			"Weiche 2", PinState.HIGH);
	final GpioPinDigitalOutput weiche3A = gpio.provisionDigitalOutputPin(expander3, MCP23017Pin.GPIO_B7,
			"Weiche 3", PinState.HIGH);
	final GpioPinDigitalOutput weiche3B = gpio.provisionDigitalOutputPin(expander3, MCP23017Pin.GPIO_B6,
			"Weiche 3", PinState.HIGH);
	

	//LED WEICHEN
	public final GpioPinDigitalOutput ledWeiche1L = gpio.provisionDigitalOutputPin(expander4, MCP23017Pin.GPIO_A5,
			"Weiche 1 Links", PinState.LOW);
	public final GpioPinDigitalOutput ledWeiche1R = gpio.provisionDigitalOutputPin(expander4, MCP23017Pin.GPIO_A4,
			"Weiche 1 Rechts", PinState.LOW);
	public final GpioPinDigitalOutput ledWeiche2L = gpio.provisionDigitalOutputPin(expander4, MCP23017Pin.GPIO_A3,
			"Weiche 2 Links", PinState.LOW);
	public final GpioPinDigitalOutput ledWeiche2R = gpio.provisionDigitalOutputPin(expander4, MCP23017Pin.GPIO_A2,
			"Weiche 2 Rechts", PinState.LOW);
	public final GpioPinDigitalOutput ledWeiche3L = gpio.provisionDigitalOutputPin(expander4, MCP23017Pin.GPIO_A1,
			"Weiche 3 Links", PinState.LOW);
	public final GpioPinDigitalOutput ledWeiche3R = gpio.provisionDigitalOutputPin(expander4, MCP23017Pin.GPIO_A0,
			"Weiche 3 Rechts", PinState.LOW);

	public static final Weichen WEICHEN = new Weichen();

	private char statusWeiche1 = 'r';
	private char statusWeiche2 = 'r';
	private char statusWeiche3 = 'r';

	/**
	 * Pins steuern Schaltbox an und überbrücken den Taster r = rechts l = linksv t
	 * = toogle
	 * 
	 * @param c
	 * @throws InterruptedException
	 */

	public void schalteWeiche1(char c) throws InterruptedException {
		if (c == 'r') {
			ledWeiche1L.low();
			ledWeiche1R.high();
			weiche1A.low();
			weiche1B.high();
			Thread.sleep(100);
			weiche1A.low();
			weiche1B.low();
			statusWeiche1 = 'r';
			System.out.println("Weiche 1 Rechts");
			StatusWeichenSignal.schaltenWeiche1Gui('r');

		} else if (c == 'l') {
			ledWeiche1L.high();
			ledWeiche1R.low();
			weiche1A.high();
			weiche1B.low();
			Thread.sleep(100);
			weiche1A.low();
			weiche1B.low();
			statusWeiche1 = 'l';
			System.out.println("Weiche 1 Links");
			StatusWeichenSignal.schaltenWeiche1Gui('l');

		} else if (c == 't') {
			if (statusWeiche1 == 'r') {
				schalteWeiche1('l');
			} else if (statusWeiche1 == 'l') {
				schalteWeiche1('r');
			} else {
				System.err.println("Weiche 1 - Fehler Toggle " + c);
			}
		} else {
			System.err.println("Weiche 1 - Fehlerhafte Stellung");
		}

	}

	public void schalteWeiche2(char c) throws InterruptedException {
		if (c == 'r') {
			ledWeiche2L.low();
			ledWeiche2R.high();
			weiche2A.low();
			weiche2B.high();
			Thread.sleep(100);
			weiche2A.low();
			weiche2B.low();
			statusWeiche2 = 'r';
			System.out.println("Weiche 2 Rechts");
			StatusWeichenSignal.schaltenWeiche2Gui('r');

		} else if (c == 'l') {
			ledWeiche2L.high();
			ledWeiche2R.low();
			weiche2A.high();
			weiche2B.low();
			Thread.sleep(100);
			weiche2A.low();
			weiche2B.low();
			statusWeiche2 = 'l';
			System.out.println("Weiche 2 Links");
			StatusWeichenSignal.schaltenWeiche2Gui('l');

		} else if (c == 't') {
			if (statusWeiche2 == 'r') {
				schalteWeiche2('l');
			} else if (statusWeiche2 == 'l') {
				schalteWeiche2('r');
			} else {
				System.err.println("Weiche 2 - Fehler Toggle");
			}
		} else {
			System.err.println("Weiche 2 - Fehlerhafte Stellung");
		}
	}

	public void schalteWeiche3(char c) throws InterruptedException {
		if (c == 'r') {
			ledWeiche3L.low();
			ledWeiche3R.high();
			weiche3A.low();
			weiche3B.high();
			Thread.sleep(100);
			weiche3A.low();
			weiche3B.low();
			statusWeiche3 = 'r';
			System.out.println("Weiche 3 Rechts");
			StatusWeichenSignal.schaltenWeiche3Gui('r');

		} else if (c == 'l') {
			ledWeiche3L.high();
			ledWeiche3R.low();
			weiche3A.high();
			weiche3B.low();
			Thread.sleep(100);
			weiche3A.low();
			weiche3B.low();
			statusWeiche3 = 'l';
			System.out.println("Weiche 3 Links");
			StatusWeichenSignal.schaltenWeiche3Gui('l');

		} else if (c == 't') {
			if (statusWeiche3 == 'r') {
				schalteWeiche3('l');
			} else if (statusWeiche3 == 'l') {
				schalteWeiche3('r');
			} else {
				System.err.println("Weiche 3 - Fehler Toggle");
			}
		} else {
			System.err.println("Weiche 3 - Fehlerhafte Stellung");
		}
	}

	public char getStatusWeiche1() {
		return statusWeiche1;
	}

	public char getStatusWeiche2() {
		return statusWeiche2;
	}

	public char getStatusWeiche3() {
		return statusWeiche3;
	}

}
