package ServerHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import GUI.Hauptmenu;
import Verwalter.PositionUidTags;

public class Zug extends Device {

	private String zugId;
	private int position, tempo;
	private Socket clientSocket;

	public Zug(String zugId, Socket clientSocket) {
		super(zugId);

		this.zugId = zugId;
		this.clientSocket = clientSocket;

		// Buffets erstellen
		try {
			PrintWriter out = new PrintWriter(this.clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			saveInAndOutPutStream(in, out);

		} catch (IOException e) {
			Log.Error(getClass().getName(), "Buffer nicht erstellt" + zugId, e);
		}
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	// GETTER und SETTER
	public Socket getClientSocket() {
		return clientSocket;
	}

	public int getPosition() {
		return position;
	}

	public String getZugId() {
		return zugId;
	}

	public void setPosition(int uid) {
		this.position = uid;
		Log.Track(getClass().getName(), "Position", zugId+": "+uid); 
		PositionUidTags.INSTANCE.tagsAktualisieren(this);
		Hauptmenu.ps.zugFinden(this, uid);
	}

	public int getTempo() {
		return tempo;
	}

	public String getTempoKommando() {
		return "t" + getTempo();
	}

	public void setTempo(int tempo) {
		this.tempo = tempo;
		Log.Track(getClass().getName(), "setTempo", zugId+": "+tempo);
	}

}
