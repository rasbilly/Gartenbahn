package GPIO.taster;

import com.pi4j.gpio.extension.mcp.MCP23017Pin;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

import GPIO.GpioHandler;
import GPIO.Signal;
import GPIO.Weichen;
import ServerHandler.Log;


public class TasterSnifferSignalWeiche extends GpioHandler implements Runnable {

	public TasterSnifferSignalWeiche() {
		super();
	}

	// 0x23 A
	final GpioPinDigitalInput buttonSignal = gpio.provisionDigitalInputPin(expander4, MCP23017Pin.GPIO_B0,
			PinPullResistance.PULL_UP);
	final GpioPinDigitalInput buttonW1 = gpio.provisionDigitalInputPin(expander4, MCP23017Pin.GPIO_B1,
			PinPullResistance.PULL_UP);
	final GpioPinDigitalInput buttonW2 = gpio.provisionDigitalInputPin(expander4, MCP23017Pin.GPIO_B2,
			PinPullResistance.PULL_UP);
	final GpioPinDigitalInput buttonW3 = gpio.provisionDigitalInputPin(expander4, MCP23017Pin.GPIO_B3,
			PinPullResistance.PULL_UP);

	@Override
	public void run() {
		Log.Milestone(getClass().getName(), "Thread Taster Weichen/Signale gestartet");
		/**
		 * Signal warten auf Tasterdruck l�st Toggle funktion bei Weiche aus
		 */
		buttonSignal.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {

				try {
					if (buttonSignal.isLow()) {
						Signal.SIGNAL.schalteSignal('t');
						Log.Track(getClass().getName(), "Taster Signal gedr�ckt");
						Thread.sleep(1000);
					}
				} catch (InterruptedException e1) {
					Log.Error(getClass().getName(), "Signal Taster", e1);
				}
			}
		});
		/**
		 * Weiche 1 warten auf Tasterdruck l�st Toggle funktion bei Weiche aus
		 */
		buttonW1.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				try {
					if (buttonW1.isLow()) {
						Weichen.WEICHEN.schalteWeiche1('t');
						Log.Track(getClass().getName(), "Taster Weiche 1 gedr�ckt");
						Thread.sleep(1000);
					}
				} catch (InterruptedException e1) {
					Log.Error(getClass().getName(), "Weiche 1 Taster", e1);
				}
			}
		});
		/**
		 * Weiche 2 warten auf Tasterdruck l�st Toggle funktion bei Weiche aus
		 */
		buttonW2.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				try {
					if (buttonW2.isLow()) {
						Weichen.WEICHEN.schalteWeiche2('t');
						Log.Track(getClass().getName(), "Taster Weiche 2 gedr�ckt");
						Thread.sleep(1000);
					}
				} catch (InterruptedException e2) {
					Log.Error(getClass().getName(), "Weiche 2 Taster", e2);
				}
			}
		});
		/**
		 * Weiche 3 warten auf Tasterdruck l�st Toggle funktion bei Weiche aus
		 */
		buttonW3.addListener(new GpioPinListenerDigital() {

			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				try {
					if (buttonW3.isLow()) {
						Weichen.WEICHEN.schalteWeiche3('t');
						Log.Track(getClass().getName(), "Taster Weiche 3 gedr�ckt");
						Thread.sleep(1000);
					}
				} catch (InterruptedException e3) {
					Log.Error(getClass().getName(), "Weiche 3 Taster", e3);
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
