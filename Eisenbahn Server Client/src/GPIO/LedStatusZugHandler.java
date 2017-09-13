package GPIO;

import com.pi4j.gpio.extension.mcp.MCP23017Pin;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;

public class LedStatusZugHandler extends GpioHandler {

	/*
	 * Digit Steuerung
	 */

	// 0x20 B
	static final GpioPinDigitalOutput aDigit1 = gpio.provisionDigitalOutputPin(steuerungZug1, MCP23017Pin.GPIO_B0, "a",
			PinState.LOW);
	static final GpioPinDigitalOutput bDigit1 = gpio.provisionDigitalOutputPin(steuerungZug1, MCP23017Pin.GPIO_B1, "b",
			PinState.LOW);
	static final GpioPinDigitalOutput cDigit1 = gpio.provisionDigitalOutputPin(steuerungZug1, MCP23017Pin.GPIO_B2, "c",
			PinState.LOW);
	static final GpioPinDigitalOutput dDigit1 = gpio.provisionDigitalOutputPin(steuerungZug1, MCP23017Pin.GPIO_B3, "d",
			PinState.LOW);
	static final GpioPinDigitalOutput eDigit1 = gpio.provisionDigitalOutputPin(steuerungZug1, MCP23017Pin.GPIO_B4, "e",
			PinState.LOW);
	static final GpioPinDigitalOutput fDigit1 = gpio.provisionDigitalOutputPin(steuerungZug1, MCP23017Pin.GPIO_B5, "f",
			PinState.LOW);
	static final GpioPinDigitalOutput gDigit1 = gpio.provisionDigitalOutputPin(steuerungZug1, MCP23017Pin.GPIO_B6, "g",
			PinState.LOW);
	static final GpioPinDigitalOutput punktDigit1 = gpio.provisionDigitalOutputPin(steuerungZug1, MCP23017Pin.GPIO_B7,
			".", PinState.LOW);
	static final GpioPinDigitalOutput ledRichtung1 = gpio.provisionDigitalOutputPin(steuerungZug1, MCP23017Pin.GPIO_A7,
			"Richtung", PinState.LOW);

	// 0x21 B
	// static final GpioPinDigitalOutput aDigit2 =
	// gpio.provisionDigitalOutputPin(steuerungZug2, MCP23017Pin.GPIO_B0, "a",
	// PinState.LOW);
	// static final GpioPinDigitalOutput bDigit2 =
	// gpio.provisionDigitalOutputPin(steuerungZug2, MCP23017Pin.GPIO_B1, "b",
	// PinState.LOW);
	// static final GpioPinDigitalOutput cDigit2 =
	// gpio.provisionDigitalOutputPin(steuerungZug2, MCP23017Pin.GPIO_B2, "c",
	// PinState.LOW);
	// static final GpioPinDigitalOutput dDigit2 =
	// gpio.provisionDigitalOutputPin(steuerungZug2, MCP23017Pin.GPIO_B3, "d",
	// PinState.LOW);
	// static final GpioPinDigitalOutput eDigit2 =
	// gpio.provisionDigitalOutputPin(steuerungZug2, MCP23017Pin.GPIO_B4, "e",
	// PinState.LOW);
	// static final GpioPinDigitalOutput fDigit2 =
	// gpio.provisionDigitalOutputPin(steuerungZug2, MCP23017Pin.GPIO_B5, "f",
	// PinState.LOW);
	// static final GpioPinDigitalOutput gDigit2 =
	// gpio.provisionDigitalOutputPin(steuerungZug2, MCP23017Pin.GPIO_B6, "g",
	// PinState.LOW);
	// static final GpioPinDigitalOutput punktDigit2 =
	// gpio.provisionDigitalOutputPin(steuerungZug2, MCP23017Pin.GPIO_B7, ".",
	// PinState.LOW);
	//// 0x22 B
	// static final GpioPinDigitalOutput aDigit3 =
	// gpio.provisionDigitalOutputPin(steuerungZug3, MCP23017Pin.GPIO_B0, "a",
	// PinState.LOW);
	// static final GpioPinDigitalOutput bDigit3 =
	// gpio.provisionDigitalOutputPin(steuerungZug3, MCP23017Pin.GPIO_B1, "b",
	// PinState.LOW);
	// static final GpioPinDigitalOutput cDigit3 =
	// gpio.provisionDigitalOutputPin(steuerungZug3, MCP23017Pin.GPIO_B2, "c",
	// PinState.LOW);
	// static final GpioPinDigitalOutput dDigit3 =
	// gpio.provisionDigitalOutputPin(steuerungZug3, MCP23017Pin.GPIO_B3, "d",
	// PinState.LOW);
	// static final GpioPinDigitalOutput eDigit3 =
	// gpio.provisionDigitalOutputPin(steuerungZug3, MCP23017Pin.GPIO_B4, "e",
	// PinState.LOW);
	// static final GpioPinDigitalOutput fDigit3 =
	// gpio.provisionDigitalOutputPin(steuerungZug3, MCP23017Pin.GPIO_B5, "f",
	// PinState.LOW);
	// static final GpioPinDigitalOutput gDigit3 =
	// gpio.provisionDigitalOutputPin(steuerungZug3, MCP23017Pin.GPIO_B6, "g",
	// PinState.LOW);
	// static final GpioPinDigitalOutput punktDigit3 =
	// gpio.provisionDigitalOutputPin(steuerungZug3, MCP23017Pin.GPIO_B7, ".",
	// PinState.LOW);

	// zum TEST
	public void sch() {
		try {
			System.out.println("schl");
			Thread di = new Thread(new DigitLed());
			di.start();
		} catch (Exception e) {
			System.out.println("!!!sch");
			e.printStackTrace();
		}

	}

}
