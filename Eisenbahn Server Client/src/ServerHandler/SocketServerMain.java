package ServerHandler;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import GPIO.GpioHandler;
import GUI.Hauptmenu;


public class SocketServerMain {

	public static void main(String[] args) throws IOException, InterruptedException {

		System.out.println("Start Gartenbahn\n");

		// GPIO Pins ##################################################################
		try {
			System.out.print("GPIO...");
			GpioHandler gp = new GpioHandler();
			gp.portExpanderErsteller();
			gp.threadErstellerEingang();
			System.out.println("erfolgreich aktiviert. \n");
		} catch (Exception e) {
			System.err.println("!-- main GPIO Fehler -- BEENDEN");
		}

		
		// GUI ########################################################################
		try {
			System.out.print("GUI...");
			new Hauptmenu().setVisible(true);
			System.out.println("erstellt. \n");
		} catch (Exception e) {
			System.err.println("! Fehler - konnte nicht erstellt werden");
		}

		
		// ServerSocket ###############################################################
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(603);
		} catch (IOException e) {
			System.err.println("!! - ServerSocket -Port schlug fehl: " + e.getMessage());
		}

		System.out.println("Gartenbahn Server gestartet! mit Port: " + serverSocket.getLocalPort() + " ,IP-Adresse: "
				+ InetAddress.getLocalHost() + "\n");

		// Thread zum einlesen von der Konsole
		Thread consoleEinlesen = new Thread(new ConsoleEinlesen());
		consoleEinlesen.start();

		String helferName;

		// auf Anfragen/Züge warten
		while (true) {
			try {

				// Client akzeptieren
				System.out.println("ServerSocket -  warten auf Zug/Client..");
				Socket clientSocket = serverSocket.accept();

				// Zug bestimmen und id festlegen
				String zugIP = clientSocket.getInetAddress().toString();

				// IP in Name umwandeln
				if (zugIP.equals("/192.168.178.48")) {
					helferName = "Anna";
				} else if (zugIP.equals("/192.168.178.49")) {
					helferName = "Lgb";
				} else if (zugIP.equals("/192.168.178.50")) {
					helferName = "DB";
				} else {
					helferName = zugIP;
				}

				Device connectedDevice;

				if (helferName.startsWith("regler")) {
					connectedDevice = new Regler(helferName, clientSocket);

				} else {
					connectedDevice = new Zug(helferName, clientSocket);
					ZugManager.INSTANCE.registerZug((Zug) connectedDevice);
				}

				// Thread erstellen und zug übergeben
				Thread threadHandler = new Thread(new EmpfangHandler(connectedDevice));
				threadHandler.start();

				System.out.println(
						"ServerSocket - Verbindung zum Client: " + zugIP + " (" + helferName + ") hergestellt \n\n");

			} catch (IOException e) {
				System.out.println("!! - ServerSocket - .accept(); fehlgeschlagen");
				serverSocket.close();
			}

		}
	}
}