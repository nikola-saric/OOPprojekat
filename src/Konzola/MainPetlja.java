package Konzola;

import java.io.IOException;
import java.util.Scanner;

import Entiteti.Korisnik;
import Servisi.IzvodjenjeServis;
import Servisi.KartaServis;
import Servisi.KorisnikServis;
import Servisi.PredstavaServis;
import Servisi.ScenaServis;

public class MainPetlja {
	public static Korisnik ulogovaniKorisnik = null;

	public static void mainPetlja(KorisnikServis korisnickiServis, PredstavaServis predstavaServis,
			IzvodjenjeServis izvodjenjeServis, ScenaServis scenaServis, KartaServis kartaServis, Scanner sc)
			throws IOException {

		String uloga = Logovanje.logovanje(korisnickiServis, sc);

		if (uloga == "Biletar") {
			boolean kraj = false;
			while (kraj == false) {
				System.out.println("Izaberite zeljenu opciju: ");
				System.out.println("1. Rad sa predstavama.");
				System.out.println("2. Rad sa izvodjenjima.");
				System.out.println("3. Rad sa kartama.");
				System.out.println("4. Odjavljivanje.");
				System.out.println("0. Gasenje programa.");
				int vasaOpcija = Utility.ocitajBroj(sc);

				switch (vasaOpcija) {
				case 1:
					System.out.println("Rad sa predstavama.");
					RadSaPredstavama.radSaPredstavama(uloga, sc, predstavaServis);
					break;
				case 2:
					System.out.println("Rad sa izvodjenjima.");
					RadSaIzvodjenjima.radSaIzvodjenjima(uloga, sc, izvodjenjeServis);
					break;
				case 3:
					System.out.println("Rad sa kartama.");
					RadSaKartama.radSaKartama(uloga, sc, kartaServis);
					break;
				case 4:
					mainPetlja(korisnickiServis, predstavaServis, izvodjenjeServis, scenaServis, kartaServis, sc);
					break;
				case 0:
					Gasenje.gasenjePrograma(korisnickiServis, predstavaServis, izvodjenjeServis, scenaServis,
							kartaServis);
					kraj = true;
					break;
				default:
					System.out.println("Uneli ste nepostojecu opciju!");
					break;
				}
			}
		} else if (uloga == "Menadzer") {
			boolean kraj = false;
			while (kraj == false) {
				System.out.println("Izaberite zeljenu opciju: ");
				System.out.println("1. Rad sa predstavama.");
				System.out.println("2. Rad sa izvodjenjima.");
				System.out.println("3. Rad sa kartama.");
				System.out.println("4. Rad sa korisnicima.");
				System.out.println("5. Rad sa scenama.");
				System.out.println("6. Odjavljivanje.");
				System.out.println("0. Gasenje programa.");
				int vasaOpcija = Utility.ocitajBroj(sc);

				switch (vasaOpcija) {
				case 1:
					System.out.println("Rad sa predstavama.");
					RadSaPredstavama.radSaPredstavama(uloga, sc, predstavaServis);
					break;
				case 2:
					System.out.println("Rad sa izvodjenjima.");
					RadSaIzvodjenjima.radSaIzvodjenjima(uloga, sc, izvodjenjeServis);
					break;
				case 3:
					System.out.println("Rad sa kartama.");
					RadSaKartama.radSaKartama(uloga, sc, kartaServis);
					break;
				case 4:
					System.out.println("Rad sa korisnicima.");
					RadSaKorisnicima.radSaKorisnicima(korisnickiServis, sc);
					break;
				case 5:
					System.out.println("Rad sa scenama.");
					RadSaScenama.radSaScenama(sc, scenaServis);
					break;
				case 6:
					mainPetlja(korisnickiServis, predstavaServis, izvodjenjeServis, scenaServis, kartaServis, sc);
					break;
				case 0:
					Gasenje.gasenjePrograma(korisnickiServis, predstavaServis, izvodjenjeServis, scenaServis,
							kartaServis);
					kraj = true;
					break;
				default:
					System.out.println("Uneli ste nepostojecu opciju!");
					break;
				}
			}
		}
	}

	public static Korisnik getUlogovaniKorisnik() {
		return ulogovaniKorisnik;
	}

	public static void setUlogovaniKorisnik(Korisnik ulogovaniKorisnik) {
		MainPetlja.ulogovaniKorisnik = ulogovaniKorisnik;
	}

}
