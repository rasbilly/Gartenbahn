package GPIO;

public class LedTest extends DigitLed implements Runnable{
	int time = 150;
	int time2 = 150;


	public void startAndResetLeds()  {

		while (time > 50) {
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
			Weichen.WEICHEN.ledWeiche3R.low();
			sleep();
			ledRichtung3.low();
			sleep();
			ledRichtung2.low();
			sleep();
			ledRichtung1.low();

		}

	}

	private void sleep() {
		try {
			Thread.sleep(time2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void resetDigits() {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resetDigit1();
		resetDigit2();
		resetDigit3();
	}

}
