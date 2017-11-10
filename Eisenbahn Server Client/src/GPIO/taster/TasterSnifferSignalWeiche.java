package GPIO.taster;

import com.pi4j.gpio.extension.mcp.MCP23017Pin;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

import GPIO.GpioHandler;
import GPIO.Signal;
import GPIO.Weichen;


public class TasterSnifferSignalWeiche extends GpioHandler implements Runnable {

	public TasterSnifferSignalWeiche() {
		super();
	}

	// 0x23 A
	final GpioPinDigitalInput buttonSignal = gpio.provisionDigitalInputPin(weiSig, MCP23017Pin.GPIO_B0,
			PinPullResistance.PULL_UP);
	final GpioPinDigitalInput buttonW1 = gpio.provisionDigitalInputPin(weiSig, MCP23017Pin.GPIO_B1,
			PinPullResistance.PULL_UP);
	final GpioPinDigitalInput buttonW2 = gpio.provisionDigitalInputPin(weiSig, MCP23017Pin.GPIO_B2,
			PinPullResistance.PULL_UP);
	final GpioPinDigitalInput buttonW3 = gpio.provisionDigitalInputPin(weiSig, MCP23017Pin.GPIO_B3,
			PinPullResistance.PULL_UP);

	@Override
	public void run() {
		System.out.println("Thread Taster Weichen/Signale gestartet");
		/**
		 * Signal warten auf Tasterdruck löst Toggle funktion bei Weiche aus
		 */
		buttonSignal.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {

				try {
					if (buttonSignal.isLow()) {
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
		buttonW1.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				try {
					if (buttonW1.isLow()) {
						Weichen.WEICHEN.schalteWeiche1('t');
						Thread.sleep(1000);
					}
				} catch (InterruptedException e1) {
					System.out.println("!!FEHLER - Taster Weiche 1");
					e1.printStackTrace();
				}
			}
		});
		/**
		 * Weiche 2 warten auf Tasterdruck löst Toggle funktion bei Weiche aus
		 */
		buttonW2.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				try {
					if (buttonW2.isLow()) {
						Weichen.WEICHEN.schalteWeiche2('t');
						Thread.sleep(1000);
					}
				} catch (InterruptedException e2) {
					System.out.println("!!FEHLER - Taster Weiche 2");
					e2.printStackTrace();
				}
			}
		});
		/**
		 * Weiche 3 warten auf Tasterdruck löst Toggle funktion bei Weiche aus
		 */
		buttonW3.addListener(new GpioPinListenerDigital() {

			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				try {
					if (buttonW3.isLow()) {
						Weichen.WEICHEN.schalteWeiche3('t');
						Thread.sleep(1000);
					}
				} catch (InterruptedException e3) {
					System.out.println("!!FEHLER - Taster Weiche 3");
					e3.printStackTrace();
				}
			}
		});

		while (true) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
