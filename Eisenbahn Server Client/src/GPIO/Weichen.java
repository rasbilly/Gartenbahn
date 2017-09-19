package GPIO;

import com.pi4j.gpio.extension.mcp.MCP23017Pin;
import com.pi4j.io.gpio.GpioPinDigitalOutput;

import com.pi4j.io.gpio.PinState;

public class Weichen extends GpioHandler {

	public Weichen() {
		super();
	}
	// 0x23 A
	
	//RELAY WEICHEN
	final GpioPinDigitalOutput weiche1Links = gpio.provisionDigitalOutputPin(lcdRelay, MCP23017Pin.GPIO_B2,
			"Weiche 1 Links", PinState.HIGH);
	final GpioPinDigitalOutput weiche1Rechts = gpio.provisionDigitalOutputPin(lcdRelay, MCP23017Pin.GPIO_B3,
			"Weiche 1 Rechts", PinState.HIGH);
	final GpioPinDigitalOutput weiche2Links = gpio.provisionDigitalOutputPin(lcdRelay, MCP23017Pin.GPIO_B4,
			"Weiche 2 Links", PinState.HIGH);
	final GpioPinDigitalOutput weiche2Rechts = gpio.provisionDigitalOutputPin(lcdRelay, MCP23017Pin.GPIO_B5,
			"Weiche 2 Rechts", PinState.HIGH);
	final GpioPinDigitalOutput weiche3Links = gpio.provisionDigitalOutputPin(lcdRelay, MCP23017Pin.GPIO_B6,
			"Weiche 2 Links", PinState.HIGH);
	final GpioPinDigitalOutput weiche3Rechts = gpio.provisionDigitalOutputPin(lcdRelay, MCP23017Pin.GPIO_B7,
			"Weiche 2 Rechts", PinState.HIGH);

	//LED WEICHEN
	final GpioPinDigitalOutput ledWeiche1L = gpio.provisionDigitalOutputPin(weiSig, MCP23017Pin.GPIO_A0,
			"Weiche 1 Links", PinState.LOW);
	final GpioPinDigitalOutput ledWeiche1R = gpio.provisionDigitalOutputPin(weiSig, MCP23017Pin.GPIO_A1,
			"Weiche 1 Rechts", PinState.LOW);
	final GpioPinDigitalOutput ledWeiche2L = gpio.provisionDigitalOutputPin(weiSig, MCP23017Pin.GPIO_A2,
			"Weiche 2 Links", PinState.LOW);
	final GpioPinDigitalOutput ledWeiche2R = gpio.provisionDigitalOutputPin(weiSig, MCP23017Pin.GPIO_A3,
			"Weiche 2 Rechts", PinState.LOW);
	final GpioPinDigitalOutput ledWeiche3L = gpio.provisionDigitalOutputPin(weiSig, MCP23017Pin.GPIO_A4,
			"Weiche 3 Links", PinState.LOW);
	final GpioPinDigitalOutput ledWeiche3R = gpio.provisionDigitalOutputPin(weiSig, MCP23017Pin.GPIO_A5,
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
			weiche1Rechts.low();
			Thread.sleep(100);
			weiche1Rechts.high();
			statusWeiche1 = 'r';
			System.out.println("Weiche 1 Rechts");

		} else if (c == 'l') {
			ledWeiche1L.high();
			ledWeiche1R.low();
			weiche1Links.low();
			Thread.sleep(100);
			weiche1Links.high();
			statusWeiche1 = 'l';
			System.out.println("Weiche 1 Links");

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
			weiche2Rechts.low();
			Thread.sleep(100);
			weiche2Rechts.high();
			statusWeiche2 = 'r';
			System.out.println("Weiche 2 Rechts");

		} else if (c == 'l') {
			ledWeiche2L.high();
			ledWeiche2R.low();
			weiche2Links.low();
			Thread.sleep(100);
			weiche2Links.high();
			statusWeiche2 = 'l';
			System.out.println("Weiche 2 Links");

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
			weiche3Rechts.high();
			Thread.sleep(100);
			weiche3Rechts.low();
			statusWeiche3 = 'r';

		} else if (c == 'l') {
			ledWeiche3L.high();
			ledWeiche3R.low();
			weiche3Links.high();
			Thread.sleep(100);
			weiche3Links.low();
			statusWeiche3 = 'l';

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
