package ServerHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import Verwalter.PositionUidTags;

public class Zug extends Device{

	private String zugId;
	private int position;
	private int tempo;
	private boolean alive = true;
	public boolean aliveHelper;
	private Socket clientSocket;

	public Zug(String zugId, Socket clientSocket) {
		super(zugId);
		
		this.zugId = zugId;
		this.clientSocket = clientSocket;

		// Buffets erstellen
		try {
			PrintWriter out = new PrintWriter(this.clientSocket.getOutputStream(), true);
			BufferedReader in= new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			saveInAndOutPutStream(in, out);
			
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("!! - Buffet konnte nicht erstellt werden! " + zugId);
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
		System.out.println(zugId+" an Position: " + uid);
		PositionUidTags.INSTANCE.tagsAktualisieren(this);
		
	}

	public int getTempo() {
		return tempo;
	}
	
	public String getTempoKommando() {
		return "t" + getTempo();
	}

	public void setTempo(int tempo) {
		this.tempo = tempo;
	}




}
