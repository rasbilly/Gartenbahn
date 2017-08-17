package ServerHandler;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
/**

 * Verwaltung der neuen Clients
 *
 */
public class SocketServer {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;

		// ServerSocket erstellen
		try {
			serverSocket = new ServerSocket(602);
		} catch (IOException e) {
			System.out.println("!! - ServerSocket -Port schlug fehl: " + e.getMessage());
		}

		// Thread zum einlesen von der Konsole (NUR ZUM PROBEBETRIEB)
		Thread senden = new Thread(new ConsoleEinlesen());
		senden.start();
				
		String helferName;

		// auf Anfragen warten
		while (true) {
			try {

				// Client akzeptieren

				System.out.println("ServerSocket -  warten..");
				Socket clientSocket = serverSocket.accept();

				// Zug bestimmen und id festlegen
				String zugIP = clientSocket.getInetAddress().toString();
				
				//IP in Name umwandeln
				if(zugIP.equals("/192.168.178.45")) {
					helferName="Anna";
				}else if(zugIP.equals("/192.168.178.37")) {
					helferName="reglerAnna";
				}else {
					helferName="zugIP";
				}
				
				Device connectedDevice;
				
				if(helferName.startsWith("regler")) {
					//Regler connected gerade
					connectedDevice = new Regler(helferName, clientSocket);
					
				} else {
					//Zug connected gerade
					connectedDevice = new Zug(helferName, clientSocket);
					ZugManager.INSTANCE.registerZug((Zug) connectedDevice);
				}

				// Thread erstellen und zug übergeben
				Thread threadHandler = new Thread(new EmpfangHandler(connectedDevice));
				threadHandler.start();
				
				System.out.println("ServerSocket - Verbindung zum Client: " + zugIP + " ("+helferName+") hergestellt");

			} catch (IOException e) {
				System.out.println("!! - ServerSocket - .accept(); fehlgeschlagen");
			}

		}
	}
}