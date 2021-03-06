package ServerHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

public class Device {

	public PrintWriter out;
	private BufferedReader in;
	protected String id;
	boolean alive = true;
	boolean aliveHelper;

	public Device(String id) {
		this.id = id;
		sendeQueue();
	}

	public void saveInAndOutPutStream(BufferedReader in, PrintWriter out) {
		this.out = out;
		this.in = in;
	}

	/**
	 * Daten EMPFANGEN
	 * 
	 * @return Empfangene Daten
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
				Log.Error(getClass().getName(), "Netzwerkverbindung zu " + id + "verloren", e1);
			}
			Log.Error(getClass().getName(), "BufferedReader konnte " + id + " nicht lesen", e);
			Thread.currentThread().interrupt();
		}
		return null;
	}

	/**
	 * Daten SENDEN
	 * 
	 * @param inhalt
	 */
	public void sendeDaten(int inhalt) {
		queue.add(Integer.toString(inhalt));
	}

	public void sendeDaten(String inhalt) {
		queue.add(inhalt);
	}

	List<String> queue = new LinkedList<String>();

	/**
	 * Sendet und Filtert Daten an Zug
	 */
	public void sendeQueue() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(150);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					if (!queue.isEmpty()) {
						if (queue.get(0).startsWith("t")) {
							if (((queue.size() > 3) && queue.get(1).startsWith("t")) && (queue.get(2).startsWith("t"))
									&& (queue.get(3).startsWith("t"))) {
								queue.remove(0);
								queue.remove(0);
								queue.remove(0);
								out.println(queue.get(0));
								out.flush();
								queue.remove(0);
							} else if ((queue.size() > 2) && (queue.get(1).startsWith("t"))
									&& (queue.get(2).startsWith("t"))) {
								queue.remove(0);
								queue.remove(0);
								out.println(queue.get(0));
								out.flush();
								queue.remove(0);
							} else if ((queue.size() > 1) && (queue.get(1).startsWith("t"))) {
								queue.remove(0);
								out.println(queue.get(0));
								out.flush();
								queue.remove(0);
							} else {
								out.println(queue.get(0));
								out.flush();
								queue.remove(0);
							}
						} else if (((queue.size() > 1) && queue.get(0).startsWith("h"))) {
							for (int i = 1; i < queue.size(); i++) {
								if (queue.get(i).startsWith("h")) {
									queue.remove(i);
								}
							}
							out.println(queue.get(0));
							out.flush();
							queue.remove(0);
						} else {
							out.println(queue.get(0));
							out.flush();
							queue.remove(0);
						}
					}
				}
			}
		}).start();
	}

	public String getId() {
		return id;
	}

}
