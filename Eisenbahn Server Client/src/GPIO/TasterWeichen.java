package GPIO;

import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class TasterWeichen {

	public TasterWeichen() throws InterruptedException {

		
		GpioHandler.GPIOHANDLER.tasterWeiche1.addListener(new GpioPinListenerDigital() {
			// Jedesmal wenn irgendetwas auf dem Pin passiert (High / Low) wird
			// folgende Methode aufgerufen
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				System.out.println(
						" --> GPIO PIN Zustand hat sich veraendert: " + event.getPin() + " = " + event.getState());
				try {
					Weichen.WEICHEN.schalteWeiche1('t');
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// Optional sollte hier gewartet werden um das prellen des
				// Tasters zu vermeiden...
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		GpioHandler.GPIOHANDLER.tasterWeiche2.addListener(new GpioPinListenerDigital() {
			// Jedesmal wenn irgendetwas auf dem Pin passiert (High / Low) wird
			// folgende Methode aufgerufen
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				System.out.println(
						" --> GPIO PIN Zustand hat sich veraendert: " + event.getPin() + " = " + event.getState());
				try {
					Weichen.WEICHEN.schalteWeiche2('t');
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// Optional sollte hier gewartet werden um das prellen des
				// Tasters zu vermeiden...
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		GpioHandler.GPIOHANDLER.tasterWeiche3.addListener(new GpioPinListenerDigital() {
			// Jedesmal wenn irgendetwas auf dem Pin passiert (High / Low) wird
			// folgende Methode aufgerufen
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				System.out.println(
						" --> GPIO PIN Zustand hat sich veraendert: " + event.getPin() + " = " + event.getState());
				try {
					Weichen.WEICHEN.schalteWeiche3('t');
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// Optional sollte hier gewartet werden um das prellen des
				// Tasters zu vermeiden...
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		while (true) {
			Thread.sleep(500);
		}
	}

}
