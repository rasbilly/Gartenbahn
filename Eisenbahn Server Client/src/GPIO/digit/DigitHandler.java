package GPIO.digit;

import GPIO.LedStatusZugHandler;
import GPIO.LedTest;


public class DigitHandler extends LedStatusZugHandler {

	Thread ledTest = new Thread(new LedTest());
	Thread digit1 = new Thread(new Digit1());
	Thread digit2 = new Thread(new Digit2());
	Thread digit3 = new Thread(new Digit3());

	public void threadErsteller() {
		try {
			ledTest.start();
			Thread.sleep(2500);
			digit1.start();
			digit2.start();
			digit3.start();
		} catch (Exception e) {
			System.err.println("!!Fehler - Digits wurden nicht gestartet");
		}

	}

}
