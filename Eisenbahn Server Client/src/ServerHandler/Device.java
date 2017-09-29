package ServerHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

public class Device {

	private PrintWriter out;
	private BufferedReader in;
	protected String id;

	public Device(String id) {
		this.id = id;
		System.out.println("Devvii");
		// Thread qs = new Thread(new QueueSender());
		// qs.start();
	}

	public void saveInAndOutPutStream(BufferedReader in, PrintWriter out) {
		this.out = out;
		;
		this.in = in;
	}

	/**
	 * Daten EMPFANGEN
	 * 
	 * @return
	 * @throws IOException 
	 */
	public String empfangeDaten() {
		try {
			return in.readLine();
		} catch (IOException e) {
			out.close();
			try {
				in.close();
			} catch (IOException e1) {
				System.out.println("Fehler");
				e1.printStackTrace();
			}
			Thread.currentThread().interrupt();
			System.err.println("!! - Kann Buffet in nicht lesen -> " + id);
			
		}
		return null;
	}

	/**
	 * Daten SENDEN
	 * 
	 * @param inhalt
	 */
	public void sendeDaten(int inhalt) {
		out.println(inhalt);
		out.flush();
	}

	public void sendeDaten(String inhalt) {

		//queue.add(inhalt);

		out.println(inhalt);
		out.flush();

	}

	List<String> queue = new LinkedList<String>();

	// RUN in einer normalen Klasse erstellen oder neue extra klasse erstellen?
	public void run() throws InterruptedException {
		while (true) {
			Thread.sleep(500);
			for (int i = 1; i < queue.size(); i++) {
				String start = queue.get(0).substring(0);
				if (start.equals(queue.get(i).substring(0))) {
					queue.remove(0);
					return; // Um schleife erneut zu wiederholen??
				} else {
					out.println(queue.get(0));
					out.flush();
				}
			}
		}
	}

	public String getId() {
		return id;
	}

}
