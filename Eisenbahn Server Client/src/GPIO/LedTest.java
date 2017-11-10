package GPIO;

import GPIO.digit.Digit1;
import GPIO.digit.Digit2;
import GPIO.digit.Digit3;


public class LedTest extends LedStatusZugHandler implements Runnable {
	int time = 125;
	int time2 = 75;
	
	@Override
	public void run() {
		startAndResetLeds();
	}

	public void startAndResetLeds() {
		resetLed();
		resetDigits();
		while (time > 100) {
			time -= 25;
			aDigit1.low();
			aDigit2.low();
			aDigit3.low();
			resetDigits();
			bDigit1.low();
			bDigit2.low();
			bDigit3.low();
			resetDigits();
			gDigit1.low();
			gDigit2.low();
			gDigit3.low();
			resetDigits();
			eDigit1.low();
			eDigit2.low();
			eDigit3.low();
			resetDigits();
			dDigit1.low();
			dDigit2.low();
			dDigit3.low();
			resetDigits();
			cDigit1.low();
			cDigit2.low();
			cDigit3.low();
			resetDigits();
			gDigit1.low();
			gDigit2.low();
			gDigit3.low();
			resetDigits();
			fDigit1.low();
			fDigit2.low();
			fDigit3.low();
			resetDigits();
		}
		while (time2 > 50) {
			time2 -= 25;
			Signal.SIGNAL.ledSignalAus.low();
			Signal.SIGNAL.ledSignalAn.low();
			sleep();
			Signal.SIGNAL.ledSignalAn.high();
			sleep();
			Weichen.WEICHEN.ledWeiche1L.high();
			sleep();
			Weichen.WEICHEN.ledWeiche1R.high();
			sleep();
			Weichen.WEICHEN.ledWeiche2L.high();
			sleep();
			Weichen.WEICHEN.ledWeiche2R.high();
			sleep();
			Weichen.WEICHEN.ledWeiche3L.high();
			sleep();
			Weichen.WEICHEN.ledWeiche3R.high();
			sleep();
			ledRichtung3.high();
			sleep();
			ledRichtung2.high();
			sleep();
			ledRichtung1.high();
			sleep();
			Signal.SIGNAL.ledSignalAn.low();
			sleep();
			Weichen.WEICHEN.ledWeiche1L.low();
			sleep();
			Weichen.WEICHEN.ledWeiche1R.low();
			sleep();
			Weichen.WEICHEN.ledWeiche2L.low();
			sleep();
			Weichen.WEICHEN.ledWeiche2R.low();
			sleep();
			Weichen.WEICHEN.ledWeiche3L.low();
			sleep();
			Weichen.WEICHEN.ledWeiche3R.low();
			sleep();
			ledRichtung3.low();
			sleep();
			ledRichtung2.low();
			sleep();
			ledRichtung1.low();
			resetLed();
		}
		startZustand();
		System.out.println("Signal und Weichen wurden auf Startposition gestellt");
	}

	private void startZustand() {
		try {
			Thread.sleep(2000);
			Signal.SIGNAL.schalteSignal('g');
			Weichen.WEICHEN.schalteWeiche1('r');
			Weichen.WEICHEN.schalteWeiche2('l');
			Weichen.WEICHEN.schalteWeiche3('r');
			punktDigit1.low();
			punktDigit2.low();
			punktDigit3.low();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private void sleep() {
		try {
			Thread.sleep(75);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void resetLed() {
		Signal.SIGNAL.ledSignalAn.low();
		Signal.SIGNAL.ledSignalAus.low();
		Weichen.WEICHEN.ledWeiche1L.low();
		Weichen.WEICHEN.ledWeiche1R.low();
		Weichen.WEICHEN.ledWeiche2L.low();
		Weichen.WEICHEN.ledWeiche2R.low();
		Weichen.WEICHEN.ledWeiche3L.low();
		Weichen.WEICHEN.ledWeiche3R.low();
	}

	private void resetDigits() {
		sleep();
		Digit1.resetDigit1();
		Digit2.resetDigit2();
		Digit3.resetDigit3();
		
	}

}
