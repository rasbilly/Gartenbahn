package GPIO;

import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class TasterSnifferSignalWeiche extends GpioHandler implements Runnable {

	public TasterSnifferSignalWeiche() {
		super();
	}

	final GpioPinDigitalInput tasterSignal = gpio.provisionDigitalInputPin(RaspiPin.GPIO_04,
			PinPullResistance.PULL_DOWN);
	final GpioPinDigitalInput tasterWeiche1 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_05,
			PinPullResistance.PULL_DOWN);
	final GpioPinDigitalInput tasterWeiche2 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_06,
			PinPullResistance.PULL_DOWN);
	// final GpioPinDigitalInput tasterWeiche3 =
	// gpio.provisionDigitalInputPin(RaspiPin.GPIO_30,
	// PinPullResistance.PULL_DOWN);

	@Override
	public void run() {
		System.out.println("Thread Taster weichen");
		/**
		 * Signal warten auf Tasterdruck löst Toggle funktion bei Weiche aus
		 */
		tasterSignal.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {

				try {
					if (tasterSignal.isHigh()) {
						System.out.println(" --> Signal : " + event.getPin() + " = " + event.getState());
						Signal.SIGNAL.schalteSignal('t');
						Thread.sleep(1000);
					}
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		/**
		 * Weiche 1 warten auf Tasterdruck löst Toggle funktion bei Weiche aus
		 */
		tasterWeiche1.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {

				try {
					if (tasterWeiche1.isHigh()) {
						System.out.println(" --> Weiche 1: " + event.getPin() + " = " + event.getState());
						Weichen.WEICHEN.schalteWeiche1('t');
						Thread.sleep(1000);
					}
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		/**
		 * Weiche 2 warten auf Tasterdruck löst Toggle funktion bei Weiche aus
		 */
		tasterWeiche2.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				try {
					if (tasterWeiche2.isHigh()) {
						System.out.println(" --> Weiche 2: " + event.getPin() + " = " + event.getState());
						Weichen.WEICHEN.schalteWeiche2('t');
						Thread.sleep(1000);
					}
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		/**
		 * Weiche 3 warten auf Tasterdruck löst Toggle funktion bei Weiche aus
		 * 
		 * tasterWeiche3.addListener(new GpioPinListenerDigital() {
		 * 
		 * @Override public void handleGpioPinDigitalStateChangeEvent(
		 *           GpioPinDigitalStateChangeEvent event) { System.out.println(
		 *           " --> GPIO Zustand: " + event.getPin() + " = " +
		 *           event.getState()); try { if(tasterSignal.isLow()){
		 *           Weichen.WEICHEN.schalteWeiche3('t'); Thread.sleep(1000);} }
		 *           catch (InterruptedException e1) { e1.printStackTrace(); } }
		 *           });
		 */

		while (true) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
