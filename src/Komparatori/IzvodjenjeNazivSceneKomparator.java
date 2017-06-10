package Komparatori;

import java.util.Comparator;

import Entiteti.Izvodjenje;

public class IzvodjenjeNazivSceneKomparator implements Comparator<Izvodjenje>{

	@Override
	public int compare(Izvodjenje izvodjenje1, Izvodjenje izvodjenje2) {
		// Sortiranje po nazivu scene.
		String naziv1 = izvodjenje1.getScenaIzvodjenja().getNazivScene().toUpperCase();
		String naziv2 = izvodjenje2.getScenaIzvodjenja().getNazivScene().toUpperCase();
		
		return naziv1.compareTo(naziv2);
	}

}
