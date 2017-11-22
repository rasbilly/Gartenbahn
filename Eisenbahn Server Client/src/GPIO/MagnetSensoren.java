package GPIO;

import com.pi4j.gpio.extension.mcp.MCP23017Pin;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;


import GUI.StatusWeichenSignal;

public class MagnetSensoren extends GpioHandler implements Runnable {

	final GpioPinDigitalInput sensorW2L = gpio.provisionDigitalInputPin(expander3, MCP23017Pin.GPIO_A0,
			PinPullResistance.PULL_UP);
	final GpioPinDigitalInput sensorW2R = gpio.provisionDigitalInputPin(expander3, MCP23017Pin.GPIO_A1,
			PinPullResistance.PULL_UP);
	final GpioPinDigitalInput sensorMagnet3 = gpio.provisionDigitalInputPin(expander3, MCP23017Pin.GPIO_A2,
			PinPullResistance.PULL_UP);
	final GpioPinDigitalInput sensorMagnet4 = gpio.provisionDigitalInputPin(expander3, MCP23017Pin.GPIO_A3,
			PinPullResistance.PULL_UP);

	@Override
	public void run() {

		sensorW2L.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				try {
					if (sensorW2L.isLow()) {
						System.out.println("Magnetsensor L");
						Weichen.WEICHEN.schalteWeiche2('l');
						StatusWeichenSignal.schaltenWeiche2Gui('o');
						Thread.sleep(400);
					}
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});

		sensorW2R.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				try {
					if (sensorW2R.isLow()) {
						System.out.println("Magnetsensor R");
						Weichen.WEICHEN.schalteWeiche2('r');
						StatusWeichenSignal.schaltenWeiche2Gui('u');
						Thread.sleep(400);
					}
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		sensorMagnet3.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				try {
					if (sensorMagnet3.isLow()) {
						Thread.sleep(400);
					}
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		sensorMagnet4.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				try {
					if (sensorMagnet4.isLow()) {
						System.out.println("Magnetsensor 4");
						Thread.sleep(400);
					}
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
