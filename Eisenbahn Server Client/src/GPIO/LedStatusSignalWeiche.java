package GPIO;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class LedStatusSignalWeiche extends GpioHandler{

	public LedStatusSignalWeiche() {
		super();
	}
	
//	  final GpioPinDigitalOutput ledWeiche1Links =	  gpio.provisionDigitalOutputPin(RaspiPin.GPIO_30, "LED Weiche 1 Links",	  PinState.LOW); 
//	  final GpioPinDigitalOutput ledWeiche1Rechts =	  gpio.provisionDigitalOutputPin(RaspiPin.GPIO_30, "LED Weiche 1 Rechts",	  PinState.LOW); 
//	  final GpioPinDigitalOutput ledWeiche2Links =	  gpio.provisionDigitalOutputPin(RaspiPin.GPIO_30, "LED Weiche 2 Links",	  PinState.LOW); 
//	  final GpioPinDigitalOutput ledWeiche2Rechts =	  gpio.provisionDigitalOutputPin(RaspiPin.GPIO_30, "LED Weiche 2 Rechts",	  PinState.LOW); 
//	  final GpioPinDigitalOutput ledWeiche3Links =	  gpio.provisionDigitalOutputPin(RaspiPin.GPIO_30, "LED Weiche 3 Links",	  PinState.LOW); 
//	  final GpioPinDigitalOutput ledWeiche3Rechts =	  gpio.provisionDigitalOutputPin(RaspiPin.GPIO_30, "LED Weiche 3 Rechts",	  PinState.LOW); 
//	  final GpioPinDigitalOutput ledSignalOben =	  gpio.provisionDigitalOutputPin(RaspiPin.GPIO_30, "LED Signal Oben",	  PinState.LOW); 
//	  final GpioPinDigitalOutput ledSignalUnten =	  gpio.provisionDigitalOutputPin(RaspiPin.GPIO_30, "LED Signal Unten",	  PinState.LOW);
	  
	
	/*
	/**
	 * Bekommt Schaltstellung links oder rechts übergeben und schaltet die entsprechende LED
	 * @param c
	 /
	public void weiche1(char c) {
		if(c =='l') {
			ledWeiche1Links.high();
			ledWeiche1Rechts.low();
		}else if(c == 'r') {
			ledWeiche1Links.low();
			ledWeiche1Rechts.high();
		}
	}
	public void weiche2(char c) {
		if(c =='l') {
			ledWeiche2Links.high();
			ledWeiche2Rechts.low();
		}else if(c == 'r') {
			ledWeiche2Links.low();
			ledWeiche2Rechts.high();
		}
	}
	public void weiche3(char c) {
		if(c =='l') {
			ledWeiche3Links.high();
			ledWeiche3Rechts.low();
		}else if(c == 'r') {
			ledWeiche3Links.low();
			ledWeiche3Rechts.high();
		}
	}
	public void signal(char c) {
		if(c =='l') {
			ledSignalOben.high();
			ledSignalUnten.low();
		}else if(c == 'r') {
			ledSignalOben.low();
			ledSignalUnten.high();
		}
	}
	*/
}
