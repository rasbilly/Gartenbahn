package Verwalter;

import ServerHandler.Zug;

public class PositionUidTags {

	public static final PositionUidTags INSTANCE = new PositionUidTags();
	Gleisabschnitte ga = new Gleisabschnitte();//TODO
	public String[] tags = new String[15];

	/**
	 * Entfernt den letzten Zug eintrag und setzt die neue Position
	 * 
	 * @param zug
	 */
	public void tagsAktualisieren(Zug zug) {
		System.out.println("driin"+ zug.getZugId());
		for (int i = 1; i < (tags.length-1); i++) {
			if (tags[i]==zug.getZugId()) {
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
