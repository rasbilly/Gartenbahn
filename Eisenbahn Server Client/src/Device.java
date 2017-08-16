import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Device {

	private PrintWriter out;
	private BufferedReader in;
	protected String id;

	public Device(String id) {
		this.id = id;
	}
	
	public void saveInAndOutPutStream(BufferedReader in, PrintWriter out) {
		this.out = out;;
		this.in = in;
	}
	
	/**
	 *  Daten EMPFANGEN
	 * @return
	 */
	public String empfangeDaten() {
		try {
			return in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("!! - Kann Buffet in nicht lesen");
		}
		return null;
	}

	/**
	 *  Daten SENDEN
	 * @param inhalt
	 */
	public void sendeDaten(int inhalt) {
		out.println(inhalt);
		out.flush();
	}

	public void sendeDaten(String inhalt) {
		out.println(inhalt);
		out.flush();
	}
	
	public String getId() {
		return id;
	}
}
