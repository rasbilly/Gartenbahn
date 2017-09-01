package ServerHandler;
import java.util.HashMap;
import java.util.Map;

public class ZugManager {
	
	public Map<String, Zug> getZugMap() {
		return zugMap;
	}


	private ZugManager() {
	}

	// (Liste) für Züge anlegen
	// Damit es keine doppelten einträge gibt - HashMap
	Map<String, Zug> zugMap = new HashMap<String, Zug>();

	// es gibt nur eine Instanz
	public static final ZugManager INSTANCE = new ZugManager();

	/**
	 * Fügt Züge zur Map hinzu und überschreibt alte (gleiche), aber übernimmt
	 * alle werte.
	 * 
	 * @param zug
	 */
	public void registerZug(Zug zug) {
		// Wenn es den ZugName schon gibt, dann
		if (zugMap.containsKey(zug.getZugId())) { 
			//hole altes Tempo und Position
			zug.setTempo(zugMap.get(zug.getZugId()).getTempo()); 
			zug.setPosition(zugMap.get(zug.getZugId()).getPosition()); 
		}
		//Zug zur Map hinzufügen
		zugMap.put(zug.getZugId(), zug);
	}

	

	/**
	 * 
	 * @param zug
	 * @param kommando
	 */
	public void sendeAnZug(Zug zug, String kommando) {

		if (kommando.startsWith("t")) {
			zug.setTempo(Integer.parseInt(kommando.substring(1)));
		}
		zug.sendeDaten(kommando);
	}

	/**
	 * Vergleicht die ZugId mit dem inhalt der Map
	 * @param name
	 * @return Zug Objekt
	 */
	public Zug findZugByName(String name) {
		for (Zug z : zugMap.values()) {
			if (name.equals(z.getZugId())) {
				return z;
			}
		}
		return null;
	}
	


}