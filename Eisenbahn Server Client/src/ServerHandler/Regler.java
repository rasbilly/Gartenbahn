package ServerHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Regler extends Device{
	
	public Regler(String reglerID, Socket clientSocket) {
		super(reglerID);
		
		// Buffets erstellen
		try {
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in= new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			saveInAndOutPutStream(in, out);
			
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("!! - Buffet konnte nicht erstellt werden! ");
		}
	}
	
	/**
	 * Zug zum Regler suchen
	 * @return
	 */
	public Zug getZug() {
		return ZugManager.INSTANCE.findZugByName(id.substring(6));
	}

}
