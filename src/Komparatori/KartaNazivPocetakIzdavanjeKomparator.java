package Komparatori;

import java.util.Comparator;
import java.util.Date;

import Entiteti.Karta;

public class KartaNazivPocetakIzdavanjeKomparator implements Comparator<Karta> {

	@Override
	public int compare(Karta karta1, Karta karta2) {
		// Sortiranje po nazivu predstave, vremenu pocetka izvodjenja, i vremenu
		// izdavanja karte
		String naziv1 = karta1.getIzvodjenjeKarte().getPredstavaIzvodjenja()
				.getNazivPredstave().toUpperCase();
		String naziv2 = karta2.getIzvodjenjeKarte().getPredstavaIzvodjenja()
				.getNazivPredstave().toUpperCase();
		Date pocetak1 = karta1.getIzvodjenjeKarte().getPocetakIzvodjenja();
		Date pocetak2 = karta2.getIzvodjenjeKarte().getPocetakIzvodjenja();
		if (naziv1.equals(naziv2)) {
			if (pocetak1.equals(pocetak2)) {
				if (karta1.getVremeIzdavanjaKarte().equals(
						karta2.getVremeIzdavanjaKarte())) {
					return 0;
				} else if (karta1.getVremeIzdavanjaKarte().before(
						karta2.getVremeIzdavanjaKarte())) {
					return -1;
				}
				return 1;
			} else if (pocetak1.before(pocetak2)) {
				return -1;
			}
			return 1;
		}
		return naziv1.compareTo(naziv2);
	}

}
