package Komparatori;

import java.util.Comparator;

import Entiteti.Izvodjenje;

public class IzvodjenjeNazivIPocetakKomparator implements
		Comparator<Izvodjenje> {

	@Override
	public int compare(Izvodjenje izvodjenje1, Izvodjenje izvodjenje2) {
		// Sortiranje po nazivu predstave i pocetku izvodjenja.
		String naziv1 = izvodjenje1.getPredstavaIzvodjenja()
				.getNazivPredstave().toUpperCase();
		String naziv2 = izvodjenje2.getPredstavaIzvodjenja()
				.getNazivPredstave().toUpperCase();
		if (naziv1.equals(naziv2)) {
			if (izvodjenje1.getPocetakIzvodjenja().before(
					izvodjenje2.getPocetakIzvodjenja())) {
				return 1;
			}
			return 0;
		}

		return naziv1.compareTo(naziv2);
	}

}
