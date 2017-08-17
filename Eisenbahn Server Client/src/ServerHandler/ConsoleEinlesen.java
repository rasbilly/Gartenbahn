package ServerHandler;
import java.util.Scanner;

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
				processConsoleCommand(input);
			}
		}
	}

	/**
	 * splittet die Eingabe in den Zug Name und den Befehl. Sucht zum Zug Name
	 * das passende zug Objekt. Zug mit befehl weitergeben
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
