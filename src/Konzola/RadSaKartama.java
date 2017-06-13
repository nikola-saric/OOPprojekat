package Konzola;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Entiteti.Izvodjenje;
import Entiteti.Karta;
import Entiteti.Sediste;
import Servisi.KartaServis;

public class RadSaKartama {
	private static void pretragaKartePoSrijskomBroju(Scanner sc,
			KartaServis kartaServis) {
		System.out
				.println("Unesite serijski broj karte koju zelite da prikazete: ");
		String serijskiBrojKarte = Utility.ocitajTekst(sc);
		Karta pronadjenaKarta = kartaServis
				.pretragaPoSerijskomBroju(serijskiBrojKarte);
		if (pronadjenaKarta == null) {
			System.out.println("Ne postoji karta sa unetim serijskim brojem.");
		} else {
			System.out.println(pronadjenaKarta);
		}
	}

	private static void prikazSvihKarata(Scanner sc, KartaServis kartaServis) {
		int opcijaSortiranja = 0;
		while (opcijaSortiranja < 1 && opcijaSortiranja > 3) {
			System.out.println("Odaberite nacin sortiranja karata: ");
			System.out.println("1. Sortiranje po vremenu izdavanja.");
			System.out.println("2. Sortiranje po popustu.");
			System.out
					.println("3. Sortiranje po nazivu predstave, vremenu pocetka izvodjenja, i vremenu izdavanja karte.");
			opcijaSortiranja = Utility.ocitajBroj(sc);
		}
		ArrayList<Karta> sortiraneKarte = kartaServis
				.sortiranje(opcijaSortiranja);
		for (Karta karta : sortiraneKarte) {
			System.out.println(karta);
		}
	}

	private static boolean prodajaKarte(Scanner sc, KartaServis kartaServis) throws IOException {
		Izvodjenje izvodjenjeKarte = null;
		boolean postojeceIzvodjenje = false;
		for (Izvodjenje izvodjenje : kartaServis.getIzvodjenjeServis()
				.getListaIzvodjenja()) {
			System.out.println(izvodjenje.getIdentifikatorIzvodjenja() + "   "
					+ izvodjenje.getPredstavaIzvodjenja().getNazivPredstave());
		}
		while (postojeceIzvodjenje == false) {
			System.out.println("Unesite identifikator izvodjenja: ");
			int identifikatorIzvodjenja = Utility.ocitajBroj(sc);
			for (Izvodjenje izvodjenje : kartaServis.getIzvodjenjeServis()
					.getListaIzvodjenja()) {
				if (izvodjenje.getIdentifikatorIzvodjenja() == identifikatorIzvodjenja) {
					izvodjenjeKarte = izvodjenje;
					postojeceIzvodjenje = true;
				}
			}
		}
		ArrayList<Sediste> slobodnaSedista = kartaServis
				.slobodnaSedista(izvodjenjeKarte);
		if (slobodnaSedista.size() == 0) {
			return false;
		}
		for (Sediste sediste : slobodnaSedista) {
			System.out.println(sediste);
		}
		Sediste unetoSediste = null;
		boolean postojeceSediste = false;
		while (postojeceSediste == false) {
			System.out.println("Unesite red sedista: ");
			int redSedista = Utility.ocitajBroj(sc);
			System.out.println("Unesite broj sedista: ");
			int brojSedista = Utility.ocitajBroj(sc);

			for (Sediste sediste : slobodnaSedista) {
				if ((sediste.getRedSedista() == redSedista)
						&& (sediste.getBrojSedista() == brojSedista)) {
					unetoSediste = sediste;
					postojeceSediste = true;
				}
			}
		}
		boolean pravilanPopust = false;
		int popust = 0;
		while (pravilanPopust == false) {
			System.out.println("Unesite popust u %: ");
			popust = Utility.ocitajBroj(sc);
			if (popust > -1) {
				pravilanPopust = true;
			}
		}
		kartaServis.prodajaKarte(izvodjenjeKarte, unetoSediste, popust);
		return true;
	}

	public static void radSaKartama(String uloga, Scanner sc,
			KartaServis kartaServis) throws IOException {
		if (uloga.equalsIgnoreCase("biletar")) {
			System.out.println("Odaberite zeljenu opciju: ");
			System.out.println("1. Pretraga karte po serijskom broju.");
			System.out.println("2. Prikaz svih karata.");
			System.out.println("3. Prodaja karte.");
			int vasaOpcija = Utility.ocitajBroj(sc);

			switch (vasaOpcija) {
			case 1:
				pretragaKartePoSrijskomBroju(sc, kartaServis);
				break;
			case 2:
				prikazSvihKarata(sc, kartaServis);
				break;
			case 3:
				boolean prodajaKarte = prodajaKarte(sc, kartaServis);
				if (prodajaKarte == true) {
					System.out.println("Karta je uspesno prodata.");
				} else {
					System.out
							.println("Za uneto izvodjenje su rasprodata sva sedista!");
				}
				break;
			default:
				System.out.println("Uneli ste nepostojecu opciju!");
				break;
			}
		} else if (uloga.equalsIgnoreCase("menadzer")) {
			System.out.println("Odaberite zeljenu opciju: ");
			System.out.println("1. Pretraga karte po serijskom broju.");
			System.out.println("2. Prikaz svih karata.");
			int vasaOpcija = Utility.ocitajBroj(sc);

			switch (vasaOpcija) {
			case 1:
				pretragaKartePoSrijskomBroju(sc, kartaServis);
				break;
			case 2:
				prikazSvihKarata(sc, kartaServis);
				break;
			default:
				System.out.println("Uneli ste nepostojecu opciju!");
				break;
			}
		}
	}
}
