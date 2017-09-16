package ServerHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import GPIO.GpioHandler;

/**
 * 
 * Verwaltung der neuen Clients
 *
 */
public class SocketServerMain {

	public static void main(String[] args) throws IOException {

		System.out.println("Start Gartenbahn\n");

		// GPIO Pins aktivieren
		try {
			GpioHandler gp = new GpioHandler();
			Thread.sleep(400);

			gp.portExpanderErsteller();
			System.out.println("Port-Expander erfolgreich erstellt.");
			Thread.sleep(400);

			gp.threadErstellerEingang();
			System.out.println("Threads erfolgreich erstellt.");

		} catch (Exception e) {
			System.err.println("!-- main GPIO Fehler -- BEENDEN");
		}

		System.out.println("GPIOs erfolgreich aktiviert. \n\n");

		ServerSocket serverSocket = null;

		// ServerSocket erstellen
		try {
			serverSocket = new ServerSocket(603);
		} catch (IOException e) {
			System.out.println("!! - ServerSocket -Port schlug fehl: " + e.getMessage());
		}

		System.out.println("Gartenbahn Server gestartet! mit IP: " + serverSocket.getLocalSocketAddress().toString());

		// Thread zum einlesen von der Konsole
		Thread consoleEinlesen = new Thread(new ConsoleEinlesen());
		consoleEinlesen.start();
		

		String helferName;

		// auf Anfragen warten
		while (true) {
			try {

				// Client akzeptieren

				System.out.println("ServerSocket -  warten..");
				Socket clientSocket = serverSocket.accept();

				// Zug bestimmen und id festlegen
				String zugIP = clientSocket.getInetAddress().toString();

				// IP in Name umwandeln
				if (zugIP.equals("/192.168.178.37")) {
					helferName = "Anna";
				} else if (zugIP.equals("/192.168.178.47")) {
					helferName = "Roland";
				} else {
					helferName = zugIP;
				}

				Device connectedDevice;

				if (helferName.startsWith("regler")) {
					// Regler connected gerade
					connectedDevice = new Regler(helferName, clientSocket);

				} else {
					// Zug connected gerade
					connectedDevice = new Zug(helferName, clientSocket);
					ZugManager.INSTANCE.registerZug((Zug) connectedDevice);
					
//					//Heartbeat
//					Thread heartbeat = new Thread(new Heartbeat((Zug) connectedDevice));
//					heartbeat.start();
//					System.out.println("Heartbeat erfolgreich gestartet.");
				}

				// Thread erstellen und zug übergeben
				Thread threadHandler = new Thread(new EmpfangHandler(connectedDevice));
				threadHandler.start();
				
				

				System.out.println(
						"ServerSocket - Verbindung zum Client: " + zugIP + " (" + helferName + ") hergestellt");

			} catch (IOException e) {
				System.out.println("!! - ServerSocket - .accept(); fehlgeschlagen");
				serverSocket.close();
			}

		}
	}
}