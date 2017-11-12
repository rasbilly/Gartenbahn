package GPIO.drehregler;

import com.pi4j.gpio.extension.mcp.MCP23017Pin;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

import ServerHandler.Zug;
import ServerHandler.ZugManager;

public class Drehregler3 extends DrehreglerHandler implements Runnable {

	
	static final GpioPinDigitalInput drehregler3Taster = gpio.provisionDigitalInputPin(expander2,
			MCP23017Pin.GPIO_A2, "Button Drehknopf",PinPullResistance.PULL_UP);
	final GpioPinDigitalInput drehregler3DT = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02,
			PinPullResistance.PULL_DOWN);
	final GpioPinDigitalInput drehregler3Clk = gpio.provisionDigitalInputPin(RaspiPin.GPIO_05,
			PinPullResistance.PULL_DOWN);
	
	// Regler dem Zug zuweisen
	Zug zug;

	// var anlegen
	int counter, helfer, clk_Aktuell;
	int clk_Letzter = drehregler3Clk.getState().getValue();

	@Override
	public void run() {

		/**
		 * Wartet auf eingabe vom Drehregler und verändert das Tempo. Min -5; Stopp 0;
		 * Max 5; Da der Drehregler zwischen den Spürbaren Rastungen noch einen
		 * Zwischenschritt hat, wird dieser mit "counter % 2 == 0 " Übersprungen.
		 */
		drehregler3Clk.addListener(new GpioPinListenerDigital() {

			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				
				zug = ZugManager.INSTANCE.findZugByName("DB");

				if (zug != null) { // Prüfen ob Zug "Online"

					clk_Aktuell = drehregler3Clk.getState().getValue();
					int dt = drehregler3DT.getState().getValue();
					if (counter % 2 == 0 || counter == 0) {
						 counter = 2*zug.getTempo(); //TODO Anzeige kommt durcheinander bei schnellen Drehungen..timestamp ??
					}

					if (clk_Aktuell != clk_Letzter) {
						if (dt != clk_Aktuell) {
							if (counter > -10) {
								counter--;
							}
						} else {
							if (counter < 10) {
								counter++;
							}
						}
					}

					// Ruft die Methoden nur auf wenn der counter durch 2
					// teilbar ist
					// TODO Drehmomente beim Taster beachten!
					if (counter % 2 == 0 || counter == 0) {
						if (helfer != counter) { // verhindert senden des selben Tempos
							DrehreglerHandler.sendeReglerAnZug(zug, counter);
							DrehreglerHandler.drehreglerausgabe(zug, counter);
							try {
								Thread.sleep(150);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							helfer = counter;
						}
					}
					clk_Letzter = clk_Aktuell;
				} else if (zug == null) {
					System.out.println("! Zug 'DB' nicht Online! - null ");
				}

			}
		});

		/**
		 * Wartet auf Tasterdruck, dann wird das Tempo vom Zug auf 0 gesetzt
		 * Pull_UP
		 */
		drehregler3Taster.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				if(drehregler3Taster.isLow()){
				System.out.println("Drehregler Taster Zug 3");
				if (zug != null) {
				counter = 0;
				DrehreglerHandler.drehreglerausgabe(zug, counter);
				DrehreglerHandler.sendeReglerAnZug(zug, counter);
				}}
			}
		});
	
}}
