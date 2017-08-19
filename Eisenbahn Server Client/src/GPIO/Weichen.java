package GPIO;

public class Weichen {
	public static final Weichen WEICHEN = new Weichen();
	char statusWeiche1;
	char statusWeiche2;
	char statusWeiche3;

	/**
	 * Pins steuern Schaltbox an und überbrücken den Taster
	 * r = rechts
	 * l = links
	 * t = toogle
	 * @param c
	 * @throws InterruptedException
	 */
	public void schalteWeiche1(char c) throws InterruptedException {
		if (c == 'r') {
			GpioHandler.GPIOHANDLER.weiche1Rechts.high();
			Thread.sleep(100);
			GpioHandler.GPIOHANDLER.weiche1Rechts.low();
			statusWeiche1 = 'r';

		} else if (c == 'l') {
			GpioHandler.GPIOHANDLER.weiche1Links.high();
			Thread.sleep(100);
			GpioHandler.GPIOHANDLER.weiche1Links.low();
			statusWeiche1 = 'l';

		} else if (c == 't') {
			if (statusWeiche1 == 'r') {
				schalteWeiche1('l');
			} else if (statusWeiche1 == 'l') {
				schalteWeiche1('r');
			} else {
				System.err.println("Weiche 1 - Fehler Toggle");
			}
		} else {
			System.err.println("Weiche 1 - Fehlerhafte Stellung");
		}
	}

	public void schalteWeiche2(char c) throws InterruptedException {
		if (c == 'r') {
			GpioHandler.GPIOHANDLER.weiche2Rechts.high();
			Thread.sleep(100);
			GpioHandler.GPIOHANDLER.weiche2Rechts.low();
			statusWeiche2 = 'r';

		} else if (c == 'l') {
			GpioHandler.GPIOHANDLER.weiche2Links.high();
			Thread.sleep(100);
			GpioHandler.GPIOHANDLER.weiche2Links.low();
			statusWeiche2 = 'l';

		} else if (c == 't') {
			if (statusWeiche2 == 'r') {
				schalteWeiche2('l');
			} else if (statusWeiche2 == 'l') {
				schalteWeiche2('r');
			} else {
				System.err.println("Weiche 2 - Fehler Toggle");
			}
		} else {
			System.err.println("Weiche 2 - Fehlerhafte Stellung");
		}
	}

	public void schalteWeiche3(char c) throws InterruptedException {
		if (c == 'r') {
			GpioHandler.GPIOHANDLER.weiche3Rechts.high();
			Thread.sleep(100);
			GpioHandler.GPIOHANDLER.weiche3Rechts.low();
			statusWeiche3 = 'r';

		} else if (c == 'l') {
			GpioHandler.GPIOHANDLER.weiche3Links.high();
			Thread.sleep(100);
			GpioHandler.GPIOHANDLER.weiche3Links.low();
			statusWeiche3 = 'l';

		} else if (c == 't') {
			if (statusWeiche3 == 'r') {
				schalteWeiche3('l');
			} else if (statusWeiche3 == 'l') {
				schalteWeiche3('r');
			} else {
				System.err.println("Weiche 3 - Fehler Toggle");
			}
		} else {
			System.err.println("Weiche 3 - Fehlerhafte Stellung");
		}
	}

	public char getStatusWeiche1() {
		return statusWeiche1;
	}

	public char getStatusWeiche2() {
		return statusWeiche2;
	}

	public char getStatusWeiche3() {
		return statusWeiche3;
	}

}
