package ServerHandler;

import java.awt.Color;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import GPIO.GpioHandler;
import GUI.GuiHandler;
import GUI.Hauptmenu;
import Verwalter.Gleisabschnitte;

/**
 * 
 * Verwaltung der neuen Clients
 *
 */
public class SocketServerMain {

	public static void main(String[] args) throws IOException {

		System.out.println("Start Gartenbahn\n");

		// GPIO Pins aktivieren
//		try {
//			GpioHandler gp = new GpioHandler();
//			Thread.sleep(200);
//
//			gp.portExpanderErsteller();
//			Thread.sleep(200);
//
//			gp.threadErstellerEingang();
//
//		} catch (Exception e) {
//			System.out.println("!-- main GPIO Fehler -- BEENDEN");
//		}
//		System.out.println("GPIOs erfolgreich aktiviert. \n");
		
		
		new Hauptmenu().setVisible(true);
		
		GuiHandler gh = new GuiHandler();
		System.out.println("GUI erstellt. \n");

		

		ServerSocket serverSocket = null;

		// ServerSocket erstellen
		try {
			serverSocket = new ServerSocket(603);
		} catch (IOException e) {
			System.out.println("!! - ServerSocket -Port schlug fehl: " + e.getMessage());
		}

		System.out.println("Gartenbahn Server gestartet! mit Port: " + serverSocket.getLocalPort() + " ,IP-Adresse: "+ InetAddress.getLocalHost()+"\n");


		// Thread zum einlesen von der Konsole
		Thread consoleEinlesen = new Thread(new ConsoleEinlesen());
		consoleEinlesen.start();

		String helferName;
		
		for(int ii = 0;ii<10;ii++) {
			System.out.println("Hallo "+ ii);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

		// auf Anfragen warten
		while (true) {
			try {

				// Client akzeptieren
				System.out.println("ServerSocket -  warten auf Client..");
				Socket clientSocket = serverSocket.accept();

				// Zug bestimmen und id festlegen
				String zugIP = clientSocket.getInetAddress().toString();

				// IP in Name umwandeln
				if (zugIP.equals("/192.168.178.47")) {
					helferName = "Roland";
				} else if (zugIP.equals("/192.168.178.48")) {
					helferName = "Anna";
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

				
				}

				// Thread erstellen und zug übergeben
				Thread threadHandler = new Thread(new EmpfangHandler(connectedDevice));
				threadHandler.start();

				System.out.println(
						"ServerSocket - Verbindung zum Client: " + zugIP + " (" + helferName + ") hergestellt \n");

			} catch (IOException e) {
				System.out.println("!! - ServerSocket - .accept(); fehlgeschlagen");
				serverSocket.close();
			}

		}
	}
}