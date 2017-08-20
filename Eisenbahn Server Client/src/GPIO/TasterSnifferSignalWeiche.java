package GPIO;

import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class TasterSnifferSignalWeiche implements Runnable {

	public TasterSnifferSignalWeiche() {
	}

	@Override
	public void run() {
		/**
		 * Signal warten auf Tasterdruck löst Toggle funktion bei Weiche aus
		 */
		GpioHandler.GPIOHANDLER.tasterSignal.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				System.out.println(" --> GPIO Zustand: " + event.getPin() + " = " + event.getState());
				try {
					Signal.SIGNAL.schalteSignal('t');
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		/**
		 * Weiche 1 warten auf Tasterdruck löst Toggle funktion bei Weiche aus
		 */
		GpioHandler.GPIOHANDLER.tasterWeiche1.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				System.out.println(" --> GPIO Zustand: " + event.getPin() + " = " + event.getState());
				try {
					Weichen.WEICHEN.schalteWeiche1('t');
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		/**
		 * Weiche 2 warten auf Tasterdruck löst Toggle funktion bei Weiche aus
		 */
		GpioHandler.GPIOHANDLER.tasterWeiche2.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				System.out.println(" --> GPIO Zustand: " + event.getPin() + " = " + event.getState());
				try {
					Weichen.WEICHEN.schalteWeiche2('t');
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		/**
		 * Weiche 3 warten auf Tasterdruck löst Toggle funktion bei Weiche aus
		 */
		GpioHandler.GPIOHANDLER.tasterWeiche3.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				System.out.println(" --> GPIO Zustand: " + event.getPin() + " = " + event.getState());
				try {
					Weichen.WEICHEN.schalteWeiche3('t');
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		GpioHandler.GPIOHANDLER.tasterWeiche3.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				System.out.println(" --> GPIO Zustand: " + event.getPin() + " = " + event.getState());
				try {
					Weichen.WEICHEN.schalteWeiche3('t');
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
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
