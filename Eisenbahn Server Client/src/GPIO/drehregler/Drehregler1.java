package GPIO.drehregler;

import com.pi4j.gpio.extension.mcp.MCP23017Pin;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

import ServerHandler.Log;
import ServerHandler.Zug;
import ServerHandler.ZugManager;

public class Drehregler1 extends DrehreglerHandler implements Runnable {

	static final GpioPinDigitalInput drehregler1Taster = gpio.provisionDigitalInputPin(expander2, MCP23017Pin.GPIO_A4,
			"Button Drehknopf", PinPullResistance.PULL_UP);
	final GpioPinDigitalInput drehregler1DT = gpio.provisionDigitalInputPin(RaspiPin.GPIO_00,
			PinPullResistance.PULL_DOWN);
	final GpioPinDigitalInput drehregler1Clk = gpio.provisionDigitalInputPin(RaspiPin.GPIO_03,
			PinPullResistance.PULL_DOWN);

	// Regler dem Zug zuweisen
	Zug zug;

	// var anlegen
	int counter, helfer, clk_Aktuell;
	int clk_Letzter = drehregler1Clk.getState().getValue();

	@Override
	public void run() {

		/**
		 * Wartet auf eingabe vom Drehregler und verändert das Tempo. Min -5;
		 * Stopp 0; Max 5; Da der Drehregler zwischen den Spürbaren Rastungen
		 * noch einen Zwischenschritt hat, wird dieser mit "counter % 2 == 0 "
		 * Übersprungen.
		 */
		drehregler1Clk.addListener(new GpioPinListenerDigital() {

			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {

				zug = ZugManager.INSTANCE.findZugByName("Anna");

				if (zug != null) { // Prüfen ob Zug "Online"

					clk_Aktuell = drehregler1Clk.getState().getValue();
					int dt = drehregler1DT.getState().getValue();
					if (counter % 2 == 0 || counter == 0) {
						counter = 2 * zug.getTempo(); // TODO Anzeige kommt
														// durcheinander bei
														// schnellen
														// Drehungen..timestamp
														// ??
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
						if (helfer != counter) { // verhindert senden des selben
													// Tempos
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
					Log.Warning(getClass().getName(), "! Zug 'Anna' nicht Online!", "null",true);
				}

			}
		});

		/**
		 * Wartet auf Tasterdruck, dann wird das Tempo vom Zug auf 0 gesetzt
		 * Pull_UP
		 */
		drehregler1Taster.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				if (drehregler1Taster.isLow()) {
					Log.Track(getClass().getName(), "Drehregler Taster Zug 1 gedrückt");
					if (zug != null) {
						counter = 0;
						DrehreglerHandler.drehreglerausgabe(zug, counter);
						DrehreglerHandler.sendeReglerAnZug(zug, counter);
					}
				}
			}
		});

	}
}
