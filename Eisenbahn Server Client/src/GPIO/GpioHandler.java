package GPIO;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class GpioHandler {
	
	static GpioController gpio = GpioFactory.getInstance();
	
	public GpioHandler() {
		threadErstellerEingang();
	}
	 
					
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
		
	//LED Statusanzeige
	final GpioPinDigitalOutput faRiLedZugAnna = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, "Fahrtrichtung Anna", PinState.LOW);
	final GpioPinDigitalOutput faRiLedZugZwei = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07, "Fahrtrichtung Zug 2", PinState.LOW);
	
	
	//LED Weiche / Signal
	final GpioPinDigitalOutput ledWeiche1Links = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, "LED Weiche 1 Links", PinState.LOW);
	final GpioPinDigitalOutput ledWeiche1Rechts = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, "LED Weiche 1 Rechts", PinState.LOW);
	final GpioPinDigitalOutput ledWeiche2Links = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07, "LED Weiche 2 Links", PinState.LOW);
	final GpioPinDigitalOutput ledWeiche2Rechts = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07, "LED Weiche 2 Rechts", PinState.LOW);
	final GpioPinDigitalOutput ledWeiche3Links = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, "LED Weiche 3 Links", PinState.LOW);
	final GpioPinDigitalOutput ledWeiche3Rechts = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, "LED Weiche 3 Rechts", PinState.LOW);
	final GpioPinDigitalOutput ledSignalOben = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07, "LED Signal Oben", PinState.LOW);
	final GpioPinDigitalOutput ledSignalUnten = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07, "LED Signal Unten", PinState.LOW);
	
		
	//TODO Ziffern der Tempoanzeige
		//Zug 1
	
		//Zug 2
	
	//TASTER Programme
	final GpioPinDigitalInput programm1 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_09, PinPullResistance.PULL_DOWN);
	final GpioPinDigitalInput programm2 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_10, PinPullResistance.PULL_DOWN);
	final GpioPinDigitalInput programm3 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_11, PinPullResistance.PULL_DOWN);
	
	
	/**
	 * Threads für Taster und andere eingaben erstellen
	 */
	public void threadErstellerEingang(){
	
		Thread tasterDrehregler = new Thread(new TasterSnifferDrehregler());
		Thread tasterSignalWeiche = new Thread(new TasterSnifferSignalWeiche());
		Thread tasterProgramme = new Thread(new TasterSnifferProgramme());
	
		tasterDrehregler.start();
		tasterProgramme.start();
		tasterSignalWeiche.start();
	}

}
