package GPIO;

import com.pi4j.gpio.extension.mcp.MCP23017Pin;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;

public class LedStatusZugHandler extends GpioHandler {

	/*
	 * Digit Steuerung
	 */

	// 0x20 B
	// Zug 1
	static final GpioPinDigitalOutput aDigit1 = gpio.provisionDigitalOutputPin(expander1, MCP23017Pin.GPIO_B0, "a",
			PinState.HIGH);
	static final GpioPinDigitalOutput bDigit1 = gpio.provisionDigitalOutputPin(expander1, MCP23017Pin.GPIO_B1, "b",
			PinState.HIGH);
	static final GpioPinDigitalOutput cDigit1 = gpio.provisionDigitalOutputPin(expander1, MCP23017Pin.GPIO_B2, "c",
			PinState.HIGH);
	static final GpioPinDigitalOutput dDigit1 = gpio.provisionDigitalOutputPin(expander1, MCP23017Pin.GPIO_B3, "d",
			PinState.HIGH);
	static final GpioPinDigitalOutput eDigit1 = gpio.provisionDigitalOutputPin(expander1, MCP23017Pin.GPIO_B4, "e",
			PinState.HIGH);
	static final GpioPinDigitalOutput fDigit1 = gpio.provisionDigitalOutputPin(expander1, MCP23017Pin.GPIO_B5, "f",
			PinState.HIGH);
	static final GpioPinDigitalOutput gDigit1 = gpio.provisionDigitalOutputPin(expander1, MCP23017Pin.GPIO_B6, "g",
			PinState.HIGH);
	static final GpioPinDigitalOutput punktDigit1 = gpio.provisionDigitalOutputPin(expander1, MCP23017Pin.GPIO_B7, ".",
			PinState.LOW);


	// Zug 2
	static final GpioPinDigitalOutput punktDigit2 = gpio.provisionDigitalOutputPin(expander1, MCP23017Pin.GPIO_A0, ".",
			PinState.HIGH);
	static final GpioPinDigitalOutput gDigit2 = gpio.provisionDigitalOutputPin(expander1, MCP23017Pin.GPIO_A1, "g",
			PinState.HIGH);
	static final GpioPinDigitalOutput fDigit2 = gpio.provisionDigitalOutputPin(expander1, MCP23017Pin.GPIO_A2, "f",
			PinState.HIGH);
	static final GpioPinDigitalOutput eDigit2 = gpio.provisionDigitalOutputPin(expander1, MCP23017Pin.GPIO_A3, "e",
			PinState.HIGH);
	static final GpioPinDigitalOutput dDigit2 = gpio.provisionDigitalOutputPin(expander1, MCP23017Pin.GPIO_A4, "d",
			PinState.HIGH);
	static final GpioPinDigitalOutput cDigit2 = gpio.provisionDigitalOutputPin(expander1, MCP23017Pin.GPIO_A5, "c",
			PinState.HIGH);
	static final GpioPinDigitalOutput bDigit2 = gpio.provisionDigitalOutputPin(expander1, MCP23017Pin.GPIO_A6, "b",
			PinState.HIGH);
	static final GpioPinDigitalOutput aDigit2 = gpio.provisionDigitalOutputPin(expander1, MCP23017Pin.GPIO_A7, "a",
			PinState.LOW);

	// Zug 3
	static final GpioPinDigitalOutput aDigit3 = gpio.provisionDigitalOutputPin(expander2, MCP23017Pin.GPIO_B0, "a",
			PinState.HIGH);
	static final GpioPinDigitalOutput bDigit3 = gpio.provisionDigitalOutputPin(expander2, MCP23017Pin.GPIO_B1, "b",
			PinState.HIGH);
	static final GpioPinDigitalOutput cDigit3 = gpio.provisionDigitalOutputPin(expander2, MCP23017Pin.GPIO_B2, "c",
			PinState.HIGH);
	static final GpioPinDigitalOutput dDigit3 = gpio.provisionDigitalOutputPin(expander2, MCP23017Pin.GPIO_B3, "d",
			PinState.HIGH);
	static final GpioPinDigitalOutput eDigit3 = gpio.provisionDigitalOutputPin(expander2, MCP23017Pin.GPIO_B4, "e",
			PinState.HIGH);
	static final GpioPinDigitalOutput fDigit3 = gpio.provisionDigitalOutputPin(expander2, MCP23017Pin.GPIO_B5, "f",
			PinState.HIGH);
	static final GpioPinDigitalOutput gDigit3 = gpio.provisionDigitalOutputPin(expander2, MCP23017Pin.GPIO_B6, "g",
			PinState.HIGH);
	static final GpioPinDigitalOutput punktDigit3 = gpio.provisionDigitalOutputPin(expander2, MCP23017Pin.GPIO_B7,
			".", PinState.LOW);
	
	//Led Fahrtrichtung
	static final GpioPinDigitalOutput ledRichtung1 = gpio.provisionDigitalOutputPin(expander2, MCP23017Pin.GPIO_A7,
			"Richtung Zug 1", PinState.LOW);
	static final GpioPinDigitalOutput ledRichtung2 = gpio.provisionDigitalOutputPin(expander2, MCP23017Pin.GPIO_A6,
			"Richtung Zug 2", PinState.LOW);
	static final GpioPinDigitalOutput ledRichtung3 = gpio.provisionDigitalOutputPin(expander2, MCP23017Pin.GPIO_A5,
			"Richtung Zug 3", PinState.LOW);

	// zum TEST
	public void sch() {
		try {
			System.out.println("Digits starten");
			Thread digit = new Thread(new DigitLed());
			digit.start();
		} catch (Exception e) {
			System.out.println("!!FEHLER- Digits wurden nicht gestartet!");
			e.printStackTrace();
		}

	}
	
	

}
