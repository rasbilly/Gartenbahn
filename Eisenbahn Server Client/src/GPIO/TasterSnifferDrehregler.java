package GPIO;

import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

import ServerHandler.Zug;
import ServerHandler.ZugManager;

public class TasterSnifferDrehregler extends GpioHandler implements Runnable {
	
	
	public TasterSnifferDrehregler() {
		super();  //gpio
		
	}
	//Drehregler Eins
	final GpioPinDigitalInput drehreglerEinsClk = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02, PinPullResistance.PULL_DOWN);
	final GpioPinDigitalInput drehreglerEinsDT = gpio.provisionDigitalInputPin(RaspiPin.GPIO_03, PinPullResistance.PULL_DOWN);
	final GpioPinDigitalInput drehreglerEinsTaster =  gpio.provisionDigitalInputPin(RaspiPin.GPIO_30,PinPullResistance.PULL_DOWN); 
	//Drehregler Zwei
//	final GpioPinDigitalInput drehreglerZweiClk = gpio.provisionDigitalInputPin(RaspiPin.GPIO_30, PinPullResistance.PULL_DOWN); 
//	final GpioPinDigitalInput drehreglerZweiDT = gpio.provisionDigitalInputPin(RaspiPin.GPIO_30, PinPullResistance.PULL_DOWN); 
//	final GpioPinDigitalInput drehreglerZweiTaster = gpio.provisionDigitalInputPin(RaspiPin.GPIO_30, PinPullResistance.PULL_DOWN); 
	 
	
	
	//Regler dem Zug zuweisen
	Zug zugAnna =ZugManager.INSTANCE.findZugByName("Anna");
	
		
	// var anlegen
	int counter = 0;
	boolean richtung;
	int clk_Letzter = drehreglerEinsClk.getState().getValue();
	int clk_Aktuell;
	int helfer;

	@Override
	public void run() {
		System.out.println("Thread Drehregler");

		
		drehreglerEinsClk.addListener(new GpioPinListenerDigital() {

			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				clk_Aktuell = drehreglerEinsClk.getState().getValue();
				int dt = drehreglerEinsDT.getState().getValue();
				try{
				counter = zugAnna.getTempo();
			}catch(Exception e){
				System.err.println("! Zug '" +zugAnna.getZugId()+"' nicht Online! (getTempo)");
			}
				if (clk_Aktuell != clk_Letzter) {

					if (dt != clk_Aktuell) {
						if (counter > -18) {
							counter--;
						}
					} else {
						if (counter < 18) {
							counter++;
						}
					}
					if (counter % 2 == 0 || counter == 0) {
						if (helfer != counter) {
							if (counter > 0) {
								System.out.println("Vorwärts - ");
							} else if (counter < 0) {
								System.out.println("Rückwärts - ");
							} else if (counter == 0) {
								System.out.println("Stopp - ");
							}
							System.out.println("Tempo: " + counter / 2);
							helfer = counter;
							try{
							zugAnna.setTempo(counter/2);
							}catch(Exception e){
								System.err.println("! Zug '" +zugAnna.getZugId()+"' nicht Online! (setTempo)");
							}
						}
					}
				}
				clk_Letzter = clk_Aktuell;

			}

		});
	/*	drehreglerEinsTaster.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				counter = 0;
				System.out.println("Stopp - Tempo: 0");
				try{
				zug.setTempo(0);
				}catch(Exception e){
								System.err.println("! Zug '" +zugAnna.getZugId()+"' nicht Online! (setTempo(0))");
							}
			}
		});*/
	}
}
