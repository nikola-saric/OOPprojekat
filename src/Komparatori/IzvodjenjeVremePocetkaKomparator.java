package Komparatori;

import java.util.Comparator;

import Entiteti.Izvodjenje;

public class IzvodjenjeVremePocetkaKomparator implements Comparator<Izvodjenje>{

	@Override
	public int compare(Izvodjenje izvodjenje1, Izvodjenje izvodjenej2) {
		// Sortiranje po pocetku izvodjenja.
		if (izvodjenje1.getPocetakIzvodjenja().before(izvodjenej2.getPocetakIzvodjenja())) {
			return -1;
		}
		return 0;
	}

}
