package Komparatori;

import java.util.Comparator;

import Entiteti.Izvodjenje;

public class IzvodjenjeNazivPredstaveKomparator implements Comparator<Izvodjenje> {

	@Override
	public int compare(Izvodjenje izvodjenje1, Izvodjenje izvodjenje2) {
		// Sortiranje po nazivu predstave izvodjenja.
		String naziv1 = izvodjenje1.getPredstavaIzvodjenja().getNazivPredstave().toUpperCase();
		String naziv2 = izvodjenje2.getPredstavaIzvodjenja().getNazivPredstave().toUpperCase();

		return naziv1.compareTo(naziv2);
	}

}
