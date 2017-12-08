package GPIO;

import com.pi4j.gpio.extension.mcp.MCP23017Pin;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

import GUI.StatusWeichenSignal;
import ServerHandler.Log;

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
						Log.Track(getClass().getName(), "Magnetsensor 1");
						Weichen.WEICHEN.schalteWeiche2('l');
						StatusWeichenSignal.schaltenWeiche2Gui('o');
						Thread.sleep(400);
					}
				} catch (InterruptedException e1) {
					Log.Error(getClass().getName(), "Magnetsensor 1", e1);
				}
			}
		});

		sensorW2R.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				try {
					if (sensorW2R.isLow()) {
						Log.Track(getClass().getName(), "Magnetsensor 2");
						Weichen.WEICHEN.schalteWeiche2('r');
						StatusWeichenSignal.schaltenWeiche2Gui('u');
						Thread.sleep(400);
					}
				} catch (InterruptedException e1) {
					Log.Error(getClass().getName(), "Magnetsensor 2", e1);
				}
			}
		});
		sensorMagnet3.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				try {
					if (sensorMagnet3.isLow()) {
						Log.Track(getClass().getName(), "Magnetsensor 3");
						Thread.sleep(400);
					}
				} catch (InterruptedException e1) {
					Log.Error(getClass().getName(), "Magnetsensor 3", e1);
				}
			}
		});
		sensorMagnet4.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				try {
					if (sensorMagnet4.isLow()) {
						Log.Track(getClass().getName(), "Magnetsensor 4");
						Thread.sleep(400);
					}
				} catch (InterruptedException e1) {
					Log.Error(getClass().getName(), "Magnetsensor 4", e1);
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
