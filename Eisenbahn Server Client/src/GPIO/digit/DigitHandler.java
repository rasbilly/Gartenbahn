package GPIO.digit;

import com.pi4j.gpio.extension.mcp.MCP23017Pin;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;

import GPIO.GpioHandler;
import GPIO.LedTest;
import ServerHandler.Log;

public class DigitHandler extends GpioHandler {

	/*
	 * Digit Steuerung
	 */

	public void threadErsteller() {
		try {
			Thread ledTest = new Thread(new LedTest());
			Thread digit1 = new Thread(new Digit1());
			Thread digit2 = new Thread(new Digit2());
			Thread digit3 = new Thread(new Digit3());
			ledTest.start();
			Thread.sleep(2500);
			digit1.start();
			digit2.start();
			digit3.start();
		} catch (Exception e) {
			Log.Error(getClass().getName(), "Digits wurden nicht gestartet", e);
		}

	}

	// 0x20 B
	// Zug 1

	protected static final GpioPinDigitalOutput aDigit1 = gpio.provisionDigitalOutputPin(expander1, MCP23017Pin.GPIO_B0,
			"a", PinState.HIGH);
	protected static final GpioPinDigitalOutput bDigit1 = gpio.provisionDigitalOutputPin(expander1, MCP23017Pin.GPIO_B1,
			"b", PinState.HIGH);
	protected static final GpioPinDigitalOutput cDigit1 = gpio.provisionDigitalOutputPin(expander1, MCP23017Pin.GPIO_B2,
			"c", PinState.HIGH);
	protected static final GpioPinDigitalOutput dDigit1 = gpio.provisionDigitalOutputPin(expander1, MCP23017Pin.GPIO_B3,
			"d", PinState.HIGH);
	protected static final GpioPinDigitalOutput eDigit1 = gpio.provisionDigitalOutputPin(expander1, MCP23017Pin.GPIO_B4,
			"e", PinState.HIGH);
	protected static final GpioPinDigitalOutput fDigit1 = gpio.provisionDigitalOutputPin(expander1, MCP23017Pin.GPIO_B5,
			"f", PinState.HIGH);
	protected static final GpioPinDigitalOutput gDigit1 = gpio.provisionDigitalOutputPin(expander1, MCP23017Pin.GPIO_B6,
			"g", PinState.HIGH);
	protected static final GpioPinDigitalOutput punktDigit1 = gpio.provisionDigitalOutputPin(expander1,
			MCP23017Pin.GPIO_B7, ".", PinState.HIGH);

	// Zug 2
	protected static final GpioPinDigitalOutput punktDigit2 = gpio.provisionDigitalOutputPin(expander1,
			MCP23017Pin.GPIO_A0, ".", PinState.HIGH);
	protected static final GpioPinDigitalOutput gDigit2 = gpio.provisionDigitalOutputPin(expander1, MCP23017Pin.GPIO_A1,
			"g", PinState.HIGH);
	protected static final GpioPinDigitalOutput fDigit2 = gpio.provisionDigitalOutputPin(expander1, MCP23017Pin.GPIO_A2,
			"f", PinState.HIGH);
	protected static final GpioPinDigitalOutput eDigit2 = gpio.provisionDigitalOutputPin(expander1, MCP23017Pin.GPIO_A3,
			"e", PinState.HIGH);
	protected static final GpioPinDigitalOutput dDigit2 = gpio.provisionDigitalOutputPin(expander1, MCP23017Pin.GPIO_A4,
			"d", PinState.HIGH);
	protected static final GpioPinDigitalOutput cDigit2 = gpio.provisionDigitalOutputPin(expander1, MCP23017Pin.GPIO_A5,
			"c", PinState.HIGH);
	protected static final GpioPinDigitalOutput bDigit2 = gpio.provisionDigitalOutputPin(expander1, MCP23017Pin.GPIO_A6,
			"b", PinState.HIGH);
	protected static final GpioPinDigitalOutput aDigit2 = gpio.provisionDigitalOutputPin(expander1, MCP23017Pin.GPIO_A7,
			"a", PinState.HIGH);

	// Zug 3
	protected static final GpioPinDigitalOutput aDigit3 = gpio.provisionDigitalOutputPin(expander2, MCP23017Pin.GPIO_B0,
			"a", PinState.HIGH);
	protected static final GpioPinDigitalOutput bDigit3 = gpio.provisionDigitalOutputPin(expander2, MCP23017Pin.GPIO_B1,
			"b", PinState.HIGH);
	protected static final GpioPinDigitalOutput cDigit3 = gpio.provisionDigitalOutputPin(expander2, MCP23017Pin.GPIO_B2,
			"c", PinState.HIGH);
	protected static final GpioPinDigitalOutput dDigit3 = gpio.provisionDigitalOutputPin(expander2, MCP23017Pin.GPIO_B3,
			"d", PinState.HIGH);
	protected static final GpioPinDigitalOutput eDigit3 = gpio.provisionDigitalOutputPin(expander2, MCP23017Pin.GPIO_B4,
			"e", PinState.HIGH);
	protected static final GpioPinDigitalOutput fDigit3 = gpio.provisionDigitalOutputPin(expander2, MCP23017Pin.GPIO_B5,
			"f", PinState.HIGH);
	protected static final GpioPinDigitalOutput gDigit3 = gpio.provisionDigitalOutputPin(expander2, MCP23017Pin.GPIO_B6,
			"g", PinState.HIGH);
	protected static final GpioPinDigitalOutput punktDigit3 = gpio.provisionDigitalOutputPin(expander2,
			MCP23017Pin.GPIO_B7, ".", PinState.HIGH);

	// Led Fahrtrichtung
	protected static final GpioPinDigitalOutput ledRichtung1 = gpio.provisionDigitalOutputPin(expander2,
			MCP23017Pin.GPIO_A7, "Richtung Zug 1", PinState.LOW);
	protected static final GpioPinDigitalOutput ledRichtung2 = gpio.provisionDigitalOutputPin(expander2,
			MCP23017Pin.GPIO_A6, "Richtung Zug 2", PinState.LOW);
	protected static final GpioPinDigitalOutput ledRichtung3 = gpio.provisionDigitalOutputPin(expander2,
			MCP23017Pin.GPIO_A5, "Richtung Zug 3", PinState.LOW);

}
