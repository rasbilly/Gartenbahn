package Verwalter;

import GUI.GuiHandler;
import GUI.PositionZuege;
import ServerHandler.Zug;

public class PositionUidTags {

	public static final PositionUidTags INSTANCE = new PositionUidTags();

	public String[] tags = new String[16];

	/**
	 * Entfernt den letzten Zug eintrag und setzt die neue Position
	 * 
	 * @param zug
	 */
	public void tagsAktualisieren(Zug zug) {

		for (int i = 1; i < (tags.length-1); i++) {
			if (tags[i]==zug.getZugId()) {
				tags[i] = null;
			}
			tags[zug.getPosition()] = zug.getZugId();
			
		}

		Gleisabschnitte.INSTANCE.gleisStatusAktuallisieren(tags);
		
	}

	public String[] getTags() {
		return tags;
	}

}
