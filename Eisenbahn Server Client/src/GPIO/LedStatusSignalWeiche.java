package GPIO;

public class LedStatusSignalWeiche extends GpioHandler{

	public LedStatusSignalWeiche() {
		super();
	}
	
	
	/**
	 * Bekommt Schaltstellung links oder rechts übergeben und schaltet die entsprechende LED
	 * @param c
	 */
	public void weiche1(char c) {
		if(c =='l') {
			ledWeiche1Links.high();
			ledWeiche1Rechts.low();
		}else if(c == 'r') {
			ledWeiche1Links.low();
			ledWeiche1Rechts.high();
		}
	}
	public void weiche2(char c) {
		if(c =='l') {
			ledWeiche2Links.high();
			ledWeiche2Rechts.low();
		}else if(c == 'r') {
			ledWeiche2Links.low();
			ledWeiche2Rechts.high();
		}
	}
	public void weiche3(char c) {
		if(c =='l') {
			ledWeiche3Links.high();
			ledWeiche3Rechts.low();
		}else if(c == 'r') {
			ledWeiche3Links.low();
			ledWeiche3Rechts.high();
		}
	}
	public void signal(char c) {
		if(c =='l') {
			ledSignalOben.high();
			ledSignalUnten.low();
		}else if(c == 'r') {
			ledSignalOben.low();
			ledSignalUnten.high();
		}
	}
}
