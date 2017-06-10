package Komparatori;

import java.util.Comparator;

import Entiteti.Karta;

public class KartaPopustKomparator implements Comparator<Karta>{

	@Override
	public int compare(Karta karta1, Karta karta2) {
		// Sortiranje po popustu.
		int popust1 = karta1.getPopustKarte();
		int popust2 = karta2.getPopustKarte();
		
		return popust1 - popust2;
	}
	
}
