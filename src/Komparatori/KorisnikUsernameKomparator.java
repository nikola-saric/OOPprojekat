package Komparatori;

import java.util.Comparator;

import Entiteti.Korisnik;

public class KorisnikUsernameKomparator implements Comparator<Korisnik> {
	
	@Override
	public int compare(Korisnik korisnik1, Korisnik korisnik2) {
		// Poredjenje po usernameu.
		String username1 = korisnik1.getUsernameKorisnika().toUpperCase();
		String username2 = korisnik2.getUsernameKorisnika().toUpperCase();

		return username1.compareTo(username2);
	}
}
