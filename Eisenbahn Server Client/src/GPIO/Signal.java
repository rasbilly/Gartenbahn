package GPIO;

public class Signal {
	public static final Signal SIGNAL = new Signal();
	
	char statusSignal;
	
	/**
	 * Pins steuern Schaltbox an und überbrücken den Taster
	 * g = go
	 * s = stopp
	 * t = toogle
	 * @param c
	 * @throws InterruptedException
	 */
	public void schalteSignal(char c) throws InterruptedException {
		if (c == 'g') {
			GpioHandler.GPIOHANDLER.SignalAN.high();
			Thread.sleep(100);
			GpioHandler.GPIOHANDLER.SignalAN.low();
			statusSignal = 'g';

		} else if (c == 's') {
			GpioHandler.GPIOHANDLER.SignalAUS.high();
			Thread.sleep(100);
			GpioHandler.GPIOHANDLER.SignalAUS.low();
			statusSignal = 's';

		} else if (c == 't') {
			if (statusSignal == 's') {
				schalteSignal('g');
			} else if (statusSignal == 'g') {
				schalteSignal('s');
			} else {
				System.err.println("Signal - Fehler Toggle");
			}
		} else {
			System.err.println("Signal - Fehlerhafte Stellung");
		}
	}

	public char getStatusSignal() {
		return statusSignal;
	}

}
