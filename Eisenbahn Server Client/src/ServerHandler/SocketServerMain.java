package ServerHandler;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import GPIO.GpioHandler;
import GUI.Hauptmenu;
import Programme.ProgrammHandler;

public class SocketServerMain {
	static String className = SocketServerMain.class.getCanonicalName();

	public static void main(String[] args) throws IOException, InterruptedException {
		Log.Info(className, "Programm Gartenbahn gestartet!");

		// Programme erstellen
		try {
			ProgrammHandler.INSTANCE.proErsteller();
			Log.Milestone(className, "Programme wurden erstellt");
		} catch (Exception e) {
			Log.Error(className, "Programme konnten nicht erstellt werden", e);
		}

		// GPIO Pins
		// ##################################################################
		try {
			// GpioHandler gp = new GpioHandler();
			// gp.portExpanderErsteller();
			// gp.threadErstellerEingang();
			Log.Milestone(className, "Gpio wurde gestartet");
		} catch (Exception e) {
			Log.Error(className, "GPIO - PI4J konnten nicht erstellt werden", e);
		}

		// GUI
		// ########################################################################
		try {
			// new Hauptmenu().setVisible(true);
			Log.Milestone(className, "GUI wurde erstellt");
		} catch (Exception e) {
			Log.Error(className, "GUI konnte nicht erstellt werden", e);
		}

		// ServerSocket
		// ###############################################################
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(603);
			Log.Milestone(className, "ServerSocket wurde gestartet");
			Log.Track(className, "Port:" + serverSocket.getLocalPort(),
					"IP: " + serverSocket.getLocalPort() + "/" + InetAddress.getLocalHost());
		} catch (IOException e) {
			Log.Error(className, "Port schlug fehl", e);
		}

		// Thread zum einlesen von der Konsole
		Thread consoleEinlesen = new Thread(new ConsoleEinlesen());
		consoleEinlesen.start();
		Log.Milestone(className, "Alle notwendigen Klassen estellt - los gehts!");

		String helferName;

		// auf Anfragen/Züge warten
		while (true) {
			try {
				// Client akzeptieren
				Log.Info(className, "warten auf Zug/Client..");
				Socket clientSocket = serverSocket.accept();

				// Zug bestimmen und id festlegen
				String zugIP = clientSocket.getInetAddress().toString();

				// IP in Name umwandeln
				if (zugIP.equals("/192.168.10.48")) {
					helferName = "Anna";
				} else if (zugIP.equals("/192.168.10.49")) {
					helferName = "Lgb";
				} else if (zugIP.equals("/192.168.10.50")) {
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

				Log.Info(className, "Verbindung zum Client: " + zugIP + " (" + helferName + ") hergestellt");

			} catch (IOException e) {
				Log.Error(className, ".accept(); fehlgeschlagen. Keine Kommunikation mit Geräten mehr möglich", e);
				serverSocket.close();
			}

		}
	}
}