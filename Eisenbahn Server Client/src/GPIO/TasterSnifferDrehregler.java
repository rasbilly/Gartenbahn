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
		super(); // gpio

	}

	// Drehregler Eins
	final GpioPinDigitalInput drehreglerEinsClk = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02,
			PinPullResistance.PULL_DOWN);
	final GpioPinDigitalInput drehreglerEinsDT = gpio.provisionDigitalInputPin(RaspiPin.GPIO_03,
			PinPullResistance.PULL_DOWN);
	// final GpioPinDigitalInput drehreglerEinsTaster =
	// gpio.provisionDigitalInputPin(RaspiPin.GPIO_30,
	// PinPullResistance.PULL_DOWN);
	// Drehregler Zwei
	// final GpioPinDigitalInput drehreglerZweiClk =
	// gpio.provisionDigitalInputPin(RaspiPin.GPIO_30, PinPullResistance.PULL_DOWN);
	// final GpioPinDigitalInput drehreglerZweiDT =
	// gpio.provisionDigitalInputPin(RaspiPin.GPIO_30, PinPullResistance.PULL_DOWN);
	// final GpioPinDigitalInput drehreglerZweiTaster =
	// gpio.provisionDigitalInputPin(RaspiPin.GPIO_30, PinPullResistance.PULL_DOWN);

	// Regler dem Zug zuweisen
	Zug zugAnna;

	// var anlegen
	int counter=0;
	int clk_Aktuell;
	int clk_Letzter = drehreglerEinsClk.getState().getValue();

	@Override
	public void run() {

		/**
		 * Wartet auf eingabe vom Drehregler und verändert das Tempo
		 */
		drehreglerEinsClk.addListener(new GpioPinListenerDigital() {
			

			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				zugAnna = ZugManager.INSTANCE.findZugByName("Anna");

				if (zugAnna != null) { // Prüfen ob Zug "Online"
					
					clk_Aktuell = drehreglerEinsClk.getState().getValue();
					int dt = drehreglerEinsDT.getState().getValue();
					//counter = zugAnna.getTempo();

					if (clk_Aktuell != clk_Letzter) {
						if (dt != clk_Aktuell) {
							if (counter > -10) {
								counter--;
							}
						} else {
							if (counter < 10) {
								System.out.println(counter);
								counter++;
								System.out.println(counter);
							}
						}
					}
					drehreglerausgabe(zugAnna, counter);
					//sendeReglerAnZug(zugAnna, counter / 2);
					String kommando = "t"+counter/2;
					ZugManager.INSTANCE.sendeAnZug(zugAnna, kommando);
				} else if (zugAnna == null) {
					System.out.println("! Zug 'Anna' nicht Online! - null ");
				}
				clk_Letzter = clk_Aktuell;

			}

		});

		/**
		 * Wartet auf Tasterdruck, dann wird das Tempo vom Zug auf 0 gesetzt
		 */
		// drehreglerEinsTaster.addListener(new GpioPinListenerDigital() {
		// @Override
		// public void
		// handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
		// counter = 0;
		// drehreglerausgabe(zugAnna, counter);
		// sendeReglerAnZug(zugAnna, counter);
		// }
		// });

	}

	/**
	 * Gibt das neue Tempo auf der Console aus
	 * 
	 * @param zug
	 * @param tempo
	 */
	public void drehreglerausgabe(Zug zug, int tempo) {
		int helfer = tempo;
		if (tempo % 2 == 0 || tempo == 0) {
			if (helfer != tempo) {
				System.out.print(zug.getZugId());
				if (tempo > 0) {
					System.out.print(" - Vorwärts - ");
				} else if (tempo < 0) {
					System.out.print(" - Rückwärts - ");
				} else if (tempo == 0) {
					System.out.print(" - Stopp - ");
				}
				System.out.println("Tempo: " + tempo / 2);
			}
		}
	}

	/**
	 * Sendet neues Tempo an den Zug
	 * 
	 * @param zug
	 * @param tempo
	 */
	public void sendeReglerAnZug(Zug zug, int tempo) {
		zug.setTempo(tempo);
		zug.sendeDaten("t"+tempo);
		System.out.println("Tempo gesendet: "+tempo);
	}

}
