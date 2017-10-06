package GUI;

public class GuiHandler {
	public static final Hauptmenu h = new Hauptmenu();

	public static final PositionZuege posZug = new PositionZuege();

	public static final GuiHandler INSTANCE = new GuiHandler();
	
	
	

	public GuiHandler() {
		System.out.println("guten tag");
		zugAnimation();

	}

	//ZUM Programm testen
	public static void zugAnimation() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		for (int i = 0; i <= 11; i++) {
			posZug.posZug1(i);
			posZug.posZug2(i + 3);
			posZug.posZug3(i + 5);
			System.out.println(i);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (i == 15) {
				i = 0;
			}

		}
	}

}
