package Komparatori;

import java.util.Comparator;

import Entiteti.Karta;

public class KartaVremeIzdavanjaKomparator implements Comparator<Karta> {

	@Override
	public int compare(Karta karta1, Karta karta2) {
		// Sortiranje po vremenu izdavanja.
		if (karta1.getVremeIzdavanjaKarte().equals(
				karta2.getVremeIzdavanjaKarte())) {
			return 0;
		} else if (karta1.getVremeIzdavanjaKarte().before(
				karta2.getVremeIzdavanjaKarte())) {
			return -1;
		}
		return 1;
	}

}
