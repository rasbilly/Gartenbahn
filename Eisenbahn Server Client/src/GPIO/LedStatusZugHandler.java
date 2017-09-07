package GPIO;

import com.pi4j.gpio.extension.mcp.MCP23017Pin;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;


import ServerHandler.Zug;
import ServerHandler.ZugManager;

public class LedStatusZugHandler extends GpioHandler {

	public LedStatusZugHandler() {
		super();
	}
	

//	
//0x20 B
	final GpioPinDigitalOutput aDigit1 = gpio.provisionDigitalOutputPin(steuerungZug1, MCP23017Pin.GPIO_B0, "a", PinState.LOW);
	final GpioPinDigitalOutput bDigit1 = gpio.provisionDigitalOutputPin(steuerungZug1, MCP23017Pin.GPIO_B1, "b", PinState.LOW);
	final GpioPinDigitalOutput cDigit1 = gpio.provisionDigitalOutputPin(steuerungZug1, MCP23017Pin.GPIO_B2, "c", PinState.LOW);
	final GpioPinDigitalOutput dDigit1 = gpio.provisionDigitalOutputPin(steuerungZug1, MCP23017Pin.GPIO_B3, "d", PinState.LOW);
	final GpioPinDigitalOutput eDigit1 = gpio.provisionDigitalOutputPin(steuerungZug1, MCP23017Pin.GPIO_B4, "e", PinState.LOW);
	final GpioPinDigitalOutput fDigit1 = gpio.provisionDigitalOutputPin(steuerungZug1, MCP23017Pin.GPIO_B5, "f", PinState.LOW);
	final GpioPinDigitalOutput gDigit1 = gpio.provisionDigitalOutputPin(steuerungZug1, MCP23017Pin.GPIO_B6, "g", PinState.LOW);
	final GpioPinDigitalOutput punktDigit1 = gpio.provisionDigitalOutputPin(steuerungZug1, MCP23017Pin.GPIO_B7, ".", PinState.LOW);
//0x21 B
	final GpioPinDigitalOutput aDigit2 = gpio.provisionDigitalOutputPin(steuerungZug2, MCP23017Pin.GPIO_B0, "a", PinState.LOW);
	final GpioPinDigitalOutput bDigit2 = gpio.provisionDigitalOutputPin(steuerungZug2, MCP23017Pin.GPIO_B1, "b", PinState.LOW);
	final GpioPinDigitalOutput cDigit2 = gpio.provisionDigitalOutputPin(steuerungZug2, MCP23017Pin.GPIO_B2, "c", PinState.LOW);
	final GpioPinDigitalOutput dDigit2 = gpio.provisionDigitalOutputPin(steuerungZug2, MCP23017Pin.GPIO_B3, "d", PinState.LOW);
	final GpioPinDigitalOutput eDigit2 = gpio.provisionDigitalOutputPin(steuerungZug2, MCP23017Pin.GPIO_B4, "e", PinState.LOW);
	final GpioPinDigitalOutput fDigit2 = gpio.provisionDigitalOutputPin(steuerungZug2, MCP23017Pin.GPIO_B5, "f", PinState.LOW);
	final GpioPinDigitalOutput gDigit2 = gpio.provisionDigitalOutputPin(steuerungZug2, MCP23017Pin.GPIO_B6, "g", PinState.LOW);
	final GpioPinDigitalOutput punktDigit2 = gpio.provisionDigitalOutputPin(steuerungZug2, MCP23017Pin.GPIO_B7, ".", PinState.LOW);
//0x22 B	
	final GpioPinDigitalOutput aDigit3 = gpio.provisionDigitalOutputPin(steuerungZug3, MCP23017Pin.GPIO_B0, "a", PinState.LOW);
	final GpioPinDigitalOutput bDigit3 = gpio.provisionDigitalOutputPin(steuerungZug3, MCP23017Pin.GPIO_B1, "b", PinState.LOW);
	final GpioPinDigitalOutput cDigit3 = gpio.provisionDigitalOutputPin(steuerungZug3, MCP23017Pin.GPIO_B2, "c", PinState.LOW);
	final GpioPinDigitalOutput dDigit3 = gpio.provisionDigitalOutputPin(steuerungZug3, MCP23017Pin.GPIO_B3, "d", PinState.LOW);
	final GpioPinDigitalOutput eDigit3 = gpio.provisionDigitalOutputPin(steuerungZug3, MCP23017Pin.GPIO_B4, "e", PinState.LOW);
	final GpioPinDigitalOutput fDigit3 = gpio.provisionDigitalOutputPin(steuerungZug3, MCP23017Pin.GPIO_B5, "f", PinState.LOW);
	final GpioPinDigitalOutput gDigit3 = gpio.provisionDigitalOutputPin(steuerungZug3, MCP23017Pin.GPIO_B6, "g", PinState.LOW);
	final GpioPinDigitalOutput punktDigit3 = gpio.provisionDigitalOutputPin(steuerungZug3, MCP23017Pin.GPIO_B7, ".", PinState.LOW);

	
	public void showNumber(int zahl, Zug zug){
		
		
	}
	
		
	

	// Regler dem Zug zuweisen
	Zug zugAnna = ZugManager.INSTANCE.findZugByName("Anna");

	

	public void statusLedZugAnna(int tempo) {
		if (tempo < 0 && tempo > -10) {

		} else if (tempo >= 0 && tempo < 10) {

		} else {

		}

	}

	/**
	 * Fahrtrichtungs LED für Zug Anna. Vorwärts: Aus Rückwärts: Blinken
	 * 
	 * @throws InterruptedException
	 */
	public void fahrtrichtungLed() throws InterruptedException {
		while (zugAnna.getTempo() < 0) {
		//	faRiLedZugAnna.low();
			Thread.sleep(800);
		//	faRiLedZugAnna.high();
			Thread.sleep(1000);
		}

	}


	
}
