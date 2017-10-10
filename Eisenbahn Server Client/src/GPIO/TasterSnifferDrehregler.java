package GPIO;

import com.pi4j.gpio.extension.mcp.MCP23017Pin;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;


import ServerHandler.Zug;
import ServerHandler.ZugManager;

public class TasterSnifferDrehregler extends GpioHandler implements Runnable {

	public TasterSnifferDrehregler() {
	}

	// Drehregler Eins
	static final GpioPinDigitalInput drehregler1Taster = gpio.provisionDigitalInputPin(expander2,
			MCP23017Pin.GPIO_A4, "Button Drehknopf",PinPullResistance.PULL_UP);
	final GpioPinDigitalInput drehregler1DT = gpio.provisionDigitalInputPin(RaspiPin.GPIO_00,
			PinPullResistance.PULL_DOWN);
	final GpioPinDigitalInput drehregler1Clk = gpio.provisionDigitalInputPin(RaspiPin.GPIO_03,
			PinPullResistance.PULL_DOWN);

	// Drehregler Zwei
	static final GpioPinDigitalInput drehregler2Taster = gpio.provisionDigitalInputPin(expander2,
			MCP23017Pin.GPIO_A3, "Button Drehknopf",PinPullResistance.PULL_UP);
	final GpioPinDigitalInput drehregler2DT = gpio.provisionDigitalInputPin(RaspiPin.GPIO_01,
			PinPullResistance.PULL_DOWN);
	final GpioPinDigitalInput drehregler2Clk = gpio.provisionDigitalInputPin(RaspiPin.GPIO_04,
			PinPullResistance.PULL_DOWN);
	
	
	// Drehregler Drei
	static final GpioPinDigitalInput drehregler3Taster = gpio.provisionDigitalInputPin(expander2,
			MCP23017Pin.GPIO_A2, "Button Drehknopf",PinPullResistance.PULL_UP);
	final GpioPinDigitalInput drehregler3DT = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02,
			PinPullResistance.PULL_DOWN);
	final GpioPinDigitalInput drehregler3Clk = gpio.provisionDigitalInputPin(RaspiPin.GPIO_05,
			PinPullResistance.PULL_DOWN);
	

	// Regler dem Zug zuweisen
	Zug zugAnna;

	// var anlegen
	int counter;
	int helfer;
	int clk_Aktuell;
	int clk_Letzter = drehregler1Clk.getState().getValue();

	@Override
	public void run() {

		/**
		 * Wartet auf eingabe vom Drehregler und verändert das Tempo. Min -5; Stopp 0;
		 * Max 5; Da der Drehregler zwischen den Spürbaren Rastungen noch einen
		 * Zwischenschritt hat, wird dieser mit "counter % 2 == 0 " Übersprungen.
		 */
		drehregler1Clk.addListener(new GpioPinListenerDigital() {

			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				
				zugAnna = ZugManager.INSTANCE.findZugByName("Anna");

				if (zugAnna != null) { // Prüfen ob Zug "Online"

					clk_Aktuell = drehregler1Clk.getState().getValue();
					int dt = drehregler1DT.getState().getValue();
					if (counter % 2 == 0 || counter == 0) {
						 counter = 2*zugAnna.getTempo(); //TODO Anzeige kommt durcheinander bei schnellen Drehungen..timestamp ??
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
							sendeReglerAnZug(zugAnna, counter);
							drehreglerausgabe(zugAnna, counter);
							try {
								Thread.sleep(150);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							helfer = counter;
						}
					}
					clk_Letzter = clk_Aktuell;
				} else if (zugAnna == null) {
					System.out.println("! Zug 'Anna' nicht Online! - null ");
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
				if(drehregler1Taster.isLow()){
				System.out.println("Drehregler Taster Zug 1");
				if (zugAnna != null) {
				counter = 0;
				drehreglerausgabe(zugAnna, counter);
				sendeReglerAnZug(zugAnna, counter);
				}}
			}
		});
		drehregler2Taster.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				if(drehregler2Taster.isLow()){
				System.out.println("Drehregler Taster Zug 2");
//				if (zugAnna != null) {
//				counter = 0;
//				drehreglerausgabe(zugAnna, counter);
//				sendeReglerAnZug(zugAnna, counter);
//				}
			}}
		});
		drehregler3Taster.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				if(drehregler3Taster.isLow()){
				System.out.println("Drehregler Taster Zug 3");
//				if (zugAnna != null) {
//				counter = 0;
//				drehreglerausgabe(zugAnna, counter);
//				sendeReglerAnZug(zugAnna, counter);
//				}
			}}
		});

	}

	/**
	 * Gibt das neue Tempo auf der Console aus
	 * 
	 * @param zug
	 * @param tempo
	 */
	public void drehreglerausgabe(Zug zug, int tempo) {
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

	/**
	 * Sendet neues Tempo an den Zug
	 * 
	 * @param zug
	 * @param tempo
	 */
	public void sendeReglerAnZug(Zug zug, int tempo) {
		zug.setTempo(tempo / 2);
		zug.sendeDaten("t" + tempo / 2);
		// zug.sendeDaten(zug.getTempoKommando());
	}

}
