package GPIO;

import com.pi4j.gpio.extension.mcp.MCP23017Pin;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class TasterSnifferProgramme extends GpioHandler implements Runnable {

	public TasterSnifferProgramme() {
		super();
	}
	final GpioPinDigitalInput buttonP1 = gpio.provisionDigitalInputPin(weiSig, MCP23017Pin.GPIO_B4, PinPullResistance.PULL_UP);
	final GpioPinDigitalInput buttonP2 = gpio.provisionDigitalInputPin(weiSig, MCP23017Pin.GPIO_B5, PinPullResistance.PULL_UP);
	final GpioPinDigitalInput buttonP3 = gpio.provisionDigitalInputPin(weiSig, MCP23017Pin.GPIO_B6, PinPullResistance.PULL_UP);
	final GpioPinDigitalInput buttonLcdSwitch = gpio.provisionDigitalInputPin(weiSig, MCP23017Pin.GPIO_B7, PinPullResistance.PULL_UP);

	@Override
	public void run() {

		buttonP1.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				System.out.println("Programm 1 gestartet!");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});

		buttonP2.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				System.out.println("Programm 2 gestartet!");
				try {
					
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});

		buttonP3.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				System.out.println("Programm 3 gestartet!");
				try {
					
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
