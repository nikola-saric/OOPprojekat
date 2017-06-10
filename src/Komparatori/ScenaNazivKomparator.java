package Komparatori;

import java.util.Comparator;

import Entiteti.Scena;

public class ScenaNazivKomparator implements Comparator<Scena> {

	@Override
	public int compare(Scena scena1, Scena scena2) {
		// Sortiranje po nazivu scena.
		String naziv1 = scena1.getNazivScene().toUpperCase();
		String naziv2 = scena2.getNazivScene().toUpperCase();

		return naziv1.compareTo(naziv2);
	}

}
