package Konzola;

import java.util.ArrayList;
import java.util.Scanner;

import Entiteti.Biletar;
import Entiteti.Karta;
import Entiteti.Korisnik;
import Entiteti.Menadzer;
import Entiteti.Predstava;
import Servisi.KorisnikServis;

public class RadSaKorisnicima {
	public static void radSaKorisnicima(KorisnikServis korisnikServis, Scanner sc) {
		System.out.println("Izaberite zeljenu opciju: ");
		System.out.println("1. Pretraga i prikaz korisnika.");
		System.out.println("2. Unos novog korisnika.");
		System.out.println("3. Izmena korisnika.");
		System.out.println("4. Brisanje korisnika.");
		int vasaOpcija = Utility.ocitajBroj(sc);

		switch (vasaOpcija) {
		case 1:
			// Pretraga korisnika.
			int opcijaPretrage = 0;
			boolean uspesnaPretraga = false;
			while (uspesnaPretraga == false) {
				System.out.println("Izaberite opciju pretrage korisnika: ");
				System.out.println("1. Pretraga korisnika po username-u.");
				System.out.println("2. Pretraga korisnika po imenu i prezimenu.");
				System.out.println("3. Pretraga korisnika po nazivu predstave.");
				opcijaPretrage = Utility.ocitajBroj(sc);
				if ((opcijaPretrage < 1) || (opcijaPretrage > 3)) {
					System.out.println("Uneli ste nepostojecu opciju za pretragu!");
				} else {
					uspesnaPretraga = true;
				}
			}
			System.out.println("Unesite zeljenu rec pretrage: ");
			String unetaRec = Utility.ocitajTekst(sc);
			ArrayList<Korisnik> pronadjeniKorisnici = korisnikServis.pretragaKorisnika(unetaRec, opcijaPretrage);
			int brojPronadjenihKorisnika = pronadjeniKorisnici.size();
			if (brojPronadjenihKorisnika == 0) {
				System.out.println("Za unetu rec pretrage nema pronadjenih korisnika!");
			} else if (brojPronadjenihKorisnika == 1) {
				System.out.println(pronadjeniKorisnici.get(0));
				if (pronadjeniKorisnici.get(0).getClass() == Biletar.class) {
					Biletar biletar = (Biletar) pronadjeniKorisnici.get(0);
					for (Karta karta : biletar.getProdateKarte()) {
						System.out.print(karta.getSerijskiBrojKarte());
					}
					System.out.println("Broj prodatih karata je: " + biletar.getProdateKarte().size());
				} else {
					Menadzer menadzer = (Menadzer) pronadjeniKorisnici.get(0);
					for (Predstava predstava : menadzer.getDodatePredstave()) {
						System.out.print(predstava.getNazivPredstave());
					}
				}
			} else {
				// Sortiranje pronadjenih korisnika.
				int opcijaSortiranja = 0;
				boolean uspesnoSortiranje = false;
				while (uspesnoSortiranje == false) {
					System.out.println("Izaberite nacin sortiranja pronadjenih korisnika: ");
					System.out.println("1. Sortiranje po korisnickom imenu.");
					System.out.println("2. Sortiranje po imenu i prezimenu.");
					System.out.println("3. Sortiranje po tipu korisnika.");
					opcijaSortiranja = Utility.ocitajBroj(sc);
					if ((opcijaSortiranja < 1) || (opcijaSortiranja > 3)) {
						System.out.println("Uneli ste nepostojecu opciju za pretragu!");
					} else {
						uspesnoSortiranje = true;
					}
				}
				pronadjeniKorisnici = korisnikServis.sortiranjeKorisnika(pronadjeniKorisnici, opcijaSortiranja);
				for (Korisnik korisnik : pronadjeniKorisnici) {
					System.out.println(korisnik);
				}
			}
			break;
		case 2:
			// Unos novog korisnika.
			boolean ispravanUsername = false;
			String noviUsername = "";
			while (ispravanUsername == false) {
				System.out.println("Unesite username novog korisnika: ");
				noviUsername = Utility.ocitajTekst(sc);
				boolean zauzetUsername = false;
				for (Korisnik korisnik : korisnikServis.getListaKorisnika()) {
					if (korisnik.getUsernameKorisnika().equals(noviUsername)) {
						zauzetUsername = true;
					}
				}
				if (zauzetUsername == false) {
					ispravanUsername = true;
				} else {
					System.out.println("Uneti username je zauzet!");
				}
			}
			System.out.println("Unesite password novog korisnika: ");
			String noviPassword = Utility.ocitajTekst(sc);
			System.out.println("Unesite ime novog korisnika: ");
			String novoIme = Utility.ocitajTekst(sc);
			System.out.println("Unesite prezime novog korisnika: ");
			String novoPrezime = Utility.ocitajTekst(sc);

			boolean ispravnaUloga = false;
			String novaUloga = "";
			while (ispravnaUloga == false) {
				System.out.println("Unesite ulogu novog korisnika: ");
				novaUloga = Utility.ocitajTekst(sc);
				if ((novaUloga.equalsIgnoreCase("menadzer")) || (novaUloga.equalsIgnoreCase("biletar"))) {
					ispravnaUloga = true;
				} else {
					System.out.println("Uneli ste nepostojecu ulogu!");
					System.out.println("Dostupne uloge su: menadzer ili biletar.");
				}
			}
			korisnikServis.unosNovogKorisnika(noviUsername, noviPassword, novoIme, novoPrezime, novaUloga);
			System.out.println("Uspesno ste uneli novog korisnika!");
			break;
		case 3:
			// Izmena korisnika.
			System.out.println("Unesite username korisnika cije podatke zelite da izmenite: ");
			String usernameZaIzmenu = Utility.ocitajTekst(sc);
			System.out.println("Unesite novi password korisnika: ");
			String novaSifra = Utility.ocitajTekst(sc);
			System.out.println("Unesite novo ime korisnika: ");
			String novoImeKorisnika = Utility.ocitajTekst(sc);
			System.out.println("Unesite novo prezime korisnika: ");
			String novoPrezimeKorisnika = Utility.ocitajTekst(sc);
			boolean uspesnaIzmena = korisnikServis.izmenaPodatakaKorisnika(usernameZaIzmenu, novaSifra,
					novoImeKorisnika, novoPrezimeKorisnika);
			if (uspesnaIzmena == true) {
				System.out.println("Password je uspesno izmenjen!");
			} else {
				System.out.println("Doslo je do greske! ne postoji korisnik sa tim username-om.");
			}
			break;
		case 4:
			// Brisanje korisnika.
			System.out.println("Unesite username korisnika koga zelite da izbrisete: ");
			String usernameZaBrisanje = Utility.ocitajTekst(sc);
			boolean uspesnoBrisanje = korisnikServis.brisanjeKorisnika(usernameZaBrisanje);
			if (uspesnoBrisanje == true) {
				System.out.println("Korisnik je uspesno izbrisan!");
			} else {
				System.out.println("Doslo je do greske! ne postoji korisnik sa tim username-om.");
			}
			break;
		default:
			System.out.println("Uneli ste nepostojecu opciju!");
			break;
		}

	}
}