package GPIO.taster;

import com.pi4j.gpio.extension.mcp.MCP23017Pin;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

import GPIO.GpioHandler;
import GPIO.display.LcdDisplayVerwalter;
import Programme.ProgrammHandler;
import ServerHandler.Log;

public class TasterSnifferProgramme extends GpioHandler implements Runnable {

	public TasterSnifferProgramme() {
		super();
	}

	final GpioPinDigitalInput buttonP1 = gpio.provisionDigitalInputPin(expander4, MCP23017Pin.GPIO_B4,
			PinPullResistance.PULL_UP);
	final GpioPinDigitalInput buttonP2 = gpio.provisionDigitalInputPin(expander4, MCP23017Pin.GPIO_B5,
			PinPullResistance.PULL_UP);
	final GpioPinDigitalInput buttonP3 = gpio.provisionDigitalInputPin(expander4, MCP23017Pin.GPIO_B6,
			PinPullResistance.PULL_UP);
	final GpioPinDigitalInput buttonLcdSwitch = gpio.provisionDigitalInputPin(expander4, MCP23017Pin.GPIO_B7,
			PinPullResistance.PULL_UP);

	@Override
	public void run() {

		buttonP1.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				try {
					if (buttonP1.isLow()) {
						Log.Track(getClass().getName(), "Taster Programm 1 gedrückt");
						ProgrammHandler.INSTANCE.programmWaehlen(1);
						Thread.sleep(1000);
					}
				} catch (InterruptedException e1) {
					Log.Error(getClass().getName(), "Programm 1 Taster", e1);
				}
			}
		});

		buttonP2.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				try {
					if (buttonP2.isLow()) {
						Log.Track(getClass().getName(), "Taster Programm 2 gedrückt");
						ProgrammHandler.INSTANCE.programmWaehlen(2);
						Thread.sleep(1000);
					}
				} catch (InterruptedException e2) {
					Log.Error(getClass().getName(), "Programm 2 Taster", e2);
				}
			}
		});

		buttonP3.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				try {
					if (buttonP3.isLow()) {
						Log.Track(getClass().getName(), "Taster Programm 3 gedrückt");
						ProgrammHandler.INSTANCE.programmWaehlen(3);
						Thread.sleep(1000);
					}
				} catch (InterruptedException e3) {
					Log.Error(getClass().getName(), "Programm 3 Taster", e3);
				}
			}
		});
		buttonLcdSwitch.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				try {
					if (buttonLcdSwitch.isLow()) {
						Log.Track(getClass().getName(), "LCD Display Taster gedrückt");
						LcdDisplayVerwalter.lcdSchalten();
						Thread.sleep(1000);
					}
				} catch (InterruptedException e4) {
					Log.Error(getClass().getName(), "Display Taster", e4);
				}
			}
		});

		while (true)

		{
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
