package Komparatori;

import java.util.Comparator;

import Entiteti.Korisnik;

public class KorisnikImeIPrezimeKomparator implements Comparator<Korisnik> {

	@Override
	public int compare(Korisnik korisnik1, Korisnik korisnik2) {
		// Poredjenje po imenu i prezimunu korisnika.
		String imeIprezime1 = korisnik1.getImeKorisnika() + " " + korisnik1.getPrezimeKorisnika().toUpperCase();
		String imeIprezime2 = korisnik2.getImeKorisnika() + " " + korisnik2.getPrezimeKorisnika().toUpperCase();

		return imeIprezime1.compareTo(imeIprezime2);
	}

}
