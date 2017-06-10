package Konzola;

import java.util.Scanner;

import Entiteti.Biletar;
import Entiteti.Korisnik;
import Entiteti.Menadzer;
import Servisi.KorisnikServis;

public class Logovanje {

	public static String logovanje(KorisnikServis korisnikServis, Scanner sc) {
		boolean ulogovan = false;
		String uloga = "";
		while (ulogovan == false) {
			System.out.println("Unesite vase korisnicko ime: ");
			String korisnickoIme = Utility.ocitajTekst(sc);

			System.out.println("Unesite lozinku: ");
			String lozinka = Utility.ocitajTekst(sc);

			for (Korisnik korisnik : korisnikServis.getListaKorisnika()) {
				if ((korisnik.getUsernameKorisnika().equals(korisnickoIme))
						&& (korisnik.getPasswordKorisnika().equals(lozinka))) {
					if (korisnik.getClass() == Biletar.class) {
						uloga = "Biletar";
					} else if (korisnik.getClass() == Menadzer.class) {
						uloga = "Menadzer";
					}

					System.out.println("Uspesno ste se ulogovali kao korisnik: " + korisnik.getImeKorisnika() + " "
							+ korisnik.getPrezimeKorisnika());
					MainPetlja.setUlogovaniKorisnik(korisnik);
					ulogovan = true;
				}
			}

		}

		return uloga;
	}
}
