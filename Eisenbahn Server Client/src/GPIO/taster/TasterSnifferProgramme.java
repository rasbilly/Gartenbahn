package GPIO.taster;

import com.pi4j.gpio.extension.mcp.MCP23017Pin;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

import GPIO.GpioHandler;
import GPIO.display.LcdDisplayVerwalter;

public class TasterSnifferProgramme extends GpioHandler implements Runnable {

	public TasterSnifferProgramme() {
		super();
	}

	final GpioPinDigitalInput buttonP1 = gpio.provisionDigitalInputPin(weiSig, MCP23017Pin.GPIO_B4,
			PinPullResistance.PULL_UP);
	final GpioPinDigitalInput buttonP2 = gpio.provisionDigitalInputPin(weiSig, MCP23017Pin.GPIO_B5,
			PinPullResistance.PULL_UP);
	final GpioPinDigitalInput buttonP3 = gpio.provisionDigitalInputPin(weiSig, MCP23017Pin.GPIO_B6,
			PinPullResistance.PULL_UP);
	final GpioPinDigitalInput buttonLcdSwitch = gpio.provisionDigitalInputPin(weiSig, MCP23017Pin.GPIO_B7,
			PinPullResistance.PULL_UP);

	@Override
	public void run() {

		buttonP1.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				try {
					if (buttonP1.isLow()) {
						System.out.println("Programm 1 gestartet!");
						Thread.sleep(1000);
					}
				} catch (InterruptedException e1) {
					System.out.println("!!FEHLER - Taster P1");
					e1.printStackTrace();
				}
			}
		});

		buttonP2.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				try {
					if (buttonP2.isLow()) {
						System.out.println("Programm 2 gestartet!");
						Thread.sleep(1000);
					}
				} catch (InterruptedException e2) {
					System.out.println("!!FEHLER - Taster P2");
					e2.printStackTrace();
				}
			}
		});

		buttonP3.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				try {
					if (buttonP3.isLow()) {
						System.out.println("Programm 3 gestartet!");
						Thread.sleep(1000);
					}
				} catch (InterruptedException e3) {
					System.out.println("!!FEHLER - Taster P3");
					e3.printStackTrace();
				}
			}
		});
		buttonLcdSwitch.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				try {
					if (buttonLcdSwitch.isLow()) {
						System.out.println("LCD Display Taster!");
						LcdDisplayVerwalter.lcdSchalten();
						Thread.sleep(1000);
					}
				} catch (InterruptedException e4) {
					System.out.println("!!FEHLER - Display Taster");
					e4.printStackTrace();
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
