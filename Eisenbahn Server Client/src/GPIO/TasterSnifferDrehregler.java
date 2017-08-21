package GPIO;

import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class TasterSnifferDrehregler implements Runnable {

	// var anlegen
	int counter = 0;
	boolean richtung;
	int clk_Letzter = GpioHandler.INSTANCE.drehreglerEinsClk.getState().getValue();
	int clk_Aktuell;
	int helfer;

	@Override
	public void run() {

		GpioHandler.INSTANCE.drehreglerEinsClk.addListener(new GpioPinListenerDigital() {

			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				clk_Aktuell = GpioHandler.INSTANCE.drehreglerEinsClk.getState().getValue();
				int dt = GpioHandler.INSTANCE.drehreglerEinsDT.getState().getValue();

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
						}
					}
				}
				clk_Letzter = clk_Aktuell;

			}

		});
	}
}
