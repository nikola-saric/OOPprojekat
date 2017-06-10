package Komparatori;

import java.util.Comparator;

import Entiteti.Biletar;
import Entiteti.Korisnik;
import Entiteti.Menadzer;

public class KorisnikUlogaKomparator implements Comparator<Korisnik> {

	@Override
	public int compare(Korisnik korisnik1, Korisnik korisnik2) {
		if (korisnik1.getClass() == korisnik2.getClass()) {
			return 0;
		} else if (korisnik1.getClass() == Menadzer.class && korisnik2.getClass() == Biletar.class) {
			return 1;
		}

		return -1;
	}
}
