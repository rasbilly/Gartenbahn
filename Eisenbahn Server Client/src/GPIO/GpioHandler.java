package GPIO;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;


public class GpioHandler {
	
	private GpioHandler() {	}
	
	public static final GpioHandler INSTANCE = new GpioHandler();
	
	final GpioController gpio = GpioFactory.getInstance();
		
	//SIGNAL
	final GpioPinDigitalOutput SignalAN = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "Signal AN", PinState.LOW);
	final GpioPinDigitalOutput SignalAUS = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "Signal AUS", PinState.LOW);
	
	//WEICHEN
	final GpioPinDigitalOutput weiche1Links = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "Weiche 1 Links", PinState.LOW);
	final GpioPinDigitalOutput weiche1Rechts = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "Weiche 1 Rechts", PinState.LOW);
	final GpioPinDigitalOutput weiche2Links = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "Weiche 2 Links", PinState.LOW);
	final GpioPinDigitalOutput weiche2Rechts = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "Weiche 2 Rechts", PinState.LOW);
	final GpioPinDigitalOutput weiche3Links = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, "Weiche 3 Links", PinState.LOW);
	final GpioPinDigitalOutput weiche3Rechts = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07, "Weiche 3 Rechts", PinState.LOW);
	
	//TASTER SIGNAL 
	final GpioPinDigitalInput tasterSignal = gpio.provisionDigitalInputPin(RaspiPin.GPIO_08, PinPullResistance.PULL_DOWN);
		
	//TASTER WEICHEN 
	final GpioPinDigitalInput tasterWeiche1 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_09, PinPullResistance.PULL_DOWN);
	final GpioPinDigitalInput tasterWeiche2 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_10, PinPullResistance.PULL_DOWN);
	final GpioPinDigitalInput tasterWeiche3 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_11, PinPullResistance.PULL_DOWN);
	
	//Drehregler
	final GpioPinDigitalInput drehreglerEinsClk = gpio.provisionDigitalInputPin(RaspiPin.GPIO_12, PinPullResistance.PULL_DOWN);
	final GpioPinDigitalInput drehreglerEinsDT = gpio.provisionDigitalInputPin(RaspiPin.GPIO_13, PinPullResistance.PULL_DOWN);
	final GpioPinDigitalInput drehreglerEinsTaster = gpio.provisionDigitalInputPin(RaspiPin.GPIO_14, PinPullResistance.PULL_DOWN);
	final GpioPinDigitalInput drehreglerZweiClk = gpio.provisionDigitalInputPin(RaspiPin.GPIO_10, PinPullResistance.PULL_DOWN);
	final GpioPinDigitalInput drehreglerZweiDT = gpio.provisionDigitalInputPin(RaspiPin.GPIO_10, PinPullResistance.PULL_DOWN);
	final GpioPinDigitalInput drehreglerZweiTaster = gpio.provisionDigitalInputPin(RaspiPin.GPIO_10, PinPullResistance.PULL_DOWN);
		
	//TODO LED Statusanzeige
		
	//TODO Ziffern der Tempoanzeige
	
	
	
	

}
