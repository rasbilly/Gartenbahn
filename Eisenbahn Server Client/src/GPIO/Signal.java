package GPIO;

public class Signal extends GpioHandler {
	
	public Signal() {
		super();
	}
	public static final Signal SIGNAL = new Signal();
	
	private char statusSignal;
	
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
			SignalAN.high();
			Thread.sleep(100);
			SignalAN.low();
			statusSignal = 'g';

		} else if (c == 's') {
			SignalAUS.high();
			Thread.sleep(100);
			SignalAUS.low();
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
