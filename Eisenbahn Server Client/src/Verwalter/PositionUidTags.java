package Verwalter;

import ServerHandler.Zug;

public class PositionUidTags {

	public static final PositionUidTags INSTANCE = new PositionUidTags();
	Gleisabschnitte ga = new Gleisabschnitte();//TODO
	private String[] tags = new String[15];

	/**
	 * Entfernt den letzten Zug eintrag und setzt die neue Position
	 * 
	 * @param zug
	 */
	public void tagsAktualisieren(Zug zug) {
		for (int i = 0; i < tags.length; i++) {
			if (tags[i].equals(zug.getZugId())) {
				tags[i] = null;
			}
			tags[zug.getPosition()] = zug.getZugId();
		}
		ga.gleisStatusAktuallisieren(tags); //Aktuallisiert die Gleisabschnitte
	}

	public String[] getTags() {
		return tags;
	}

}
