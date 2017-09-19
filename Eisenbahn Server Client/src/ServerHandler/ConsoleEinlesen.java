package ServerHandler;

import java.util.Scanner;

import GPIO.Signal;
import GPIO.Weichen;

public class ConsoleEinlesen implements Runnable {

	public ConsoleEinlesen() {
	}

	Scanner scanner = new Scanner(System.in);

	/**
	 * von Console einlesen
	 */
	@Override
	public void run() {
		String input = null;
		while (true) {
			input = scanner.nextLine();
			if (input != null) {
				if (input.equals("liste")) { // TODO
					System.out.println("------------------ LISTE -----------------------------");
					for (Zug s : ZugManager.INSTANCE.zugMap.values()) {

						System.out.println(s.getId());
					}
					System.out.println("----------------- Ende Liste --------------------");
				} else if (input.startsWith("Weiche")) {
					try {
						processConsoleCommandWeiche(input);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else if (input.startsWith("Signal")) {
					String[] split = input.split(";");
					char s = split[1].charAt(0);
					if (s == 's' || s == 'g') {
						try {
							Signal.SIGNAL.schalteSignal(s);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else {
						System.out.println("!!Fehler -- Signal muss s oder g sein!");
					}

				} else {
					processConsoleCommand(input);
				}
			}
		}
	}

	private void processConsoleCommandWeiche(String command) throws InterruptedException {
		String[] split = command.split(";");
		char s = split[1].charAt(0);
		if (s == 'r' || s == 'l') {
			if (command.startsWith("Weiche1")) {
				Weichen.WEICHEN.schalteWeiche1(s);
			} else if (command.startsWith("Weiche2")) {
				Weichen.WEICHEN.schalteWeiche2(s);
			} else if (command.startsWith("Weiche3")) {
				Weichen.WEICHEN.schalteWeiche3(s);
			} else {
				System.out.println("!!Fehler -- Diese Weiche existiert nicht!");
			}
		} else {
			System.out.println("!!Fehler -- Weiche kann nicht auf '" + s + "' gestellt werden! ");
		}
	}

	/**
	 * splittet die Eingabe in den Zug Name und den Befehl. Sucht zum Zug Name das
	 * passende zug Objekt. Zug mit befehl weitergeben
	 * 
	 * @param command
	 *            // zb. Anna;t1
	 */
	private void processConsoleCommand(String command) {
		String[] split = command.split(";");

		Zug zielZug = ZugManager.INSTANCE.findZugByName(split[0]);
		String kommando = split[1];

		ZugManager.INSTANCE.sendeAnZug(zielZug, kommando);
	}
}
