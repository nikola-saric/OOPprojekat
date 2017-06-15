package Konzola;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Entiteti.Predstava;
import Servisi.PredstavaServis;

public class RadSaPredstavama {

	private static void pretragaPredstava(Scanner sc, PredstavaServis predstavaServis) {
		System.out.println("Unesite zeljenu opciju: ");
		System.out.println("1. Pronalazenje predstave po nazivu.");
		System.out.println("2. Pretraga i prikaz predstava.");
		int vasaOpcija = Utility.ocitajBroj(sc);

		switch (vasaOpcija) {
		case 1:
			// Pretraga predstave po tacnom nazivu.
			System.out.println("Unesite tacan naziv predstave: ");
			String nazivPredstave = Utility.ocitajTekst(sc);
			Predstava pronadjenaPredstava = predstavaServis.pronadjiPredstavu(nazivPredstave);
			if (pronadjenaPredstava == null) {
				System.out.println("Ne postoji predstava sa unetim imenom!");
			} else {
				System.out.println("==============================================================================================================================================================");
				System.out.println("|    Naziv predstave    |  Tip   |       Reziser      |                Glumci                  | Trajanje |     Produkcija     | Godina |        Opis        |");
				System.out.println("|------------------------------------------------------------------------------------------------------------------------------------------------------------|");
				System.out.println(pronadjenaPredstava);
				System.out.println("==============================================================================================================================================================");

			}
			break;
		case 2:
			// Pretraga predstave po nazivu i godini.
			System.out.println("Unesite nacin pretrage: ");
			System.out.println("1. Pretraga predstave po nazivu.");
			System.out.println("2. Pretraga predstave po godini premijere.");
			int opcijaPretrage = Utility.ocitajBroj(sc);
			ArrayList<Predstava> pronadjenePredstave = new ArrayList<>();
			switch (opcijaPretrage) {
			case 1:
				// Pretraga po nazivu.
				System.out.println("Unesite naziv predstave: ");
				nazivPredstave = Utility.ocitajTekst(sc);
				pronadjenePredstave = predstavaServis.pretragaPredstava(nazivPredstave, opcijaPretrage);
				break;
			case 2:
				// Pretraga po godini.
				System.out.println("Unesite godinu premijere predstave: ");
				String godPrem = Utility.ocitajTekst(sc);
				pronadjenePredstave = predstavaServis.pretragaPredstava(godPrem, opcijaPretrage);
				break;
			default:
				System.out.println("Uneli ste nepostojecu opciju pretrage!");
				break;
			}
			if (pronadjenePredstave.size() > 0) {
				int opcijaSortiranja = 0;
				while (opcijaSortiranja < 1 || opcijaSortiranja > 2) {
					System.out.println("Unesite nacin sortiranja pronadjenih predstava: ");
					System.out.println("1. Sortiranje predstava po nazivu.");
					System.out.println("2. Sortiranje predstava po godini premijere.");
					opcijaSortiranja = Utility.ocitajBroj(sc);
				}
				ArrayList<Predstava> sortiranePredstave = predstavaServis.sortiranjePredstava(pronadjenePredstave,
						opcijaSortiranja);
				System.out.println("==============================================================================================================================================================");
				System.out.println("|    Naziv predstave    |  Tip   |       Reziser      |                Glumci                  | Trajanje |     Produkcija     | Godina |        Opis        |");
				System.out.println("|------------------------------------------------------------------------------------------------------------------------------------------------------------|");
				for (Predstava predstava : sortiranePredstave) {
					System.out.println(predstava);
				}
				System.out.println("==============================================================================================================================================================");
			}
			break;
		default:
			System.out.println("Uneli ste nepostojecu opciju!");
			break;
		}
	}

	public static void radSaPredstavama(String uloga, Scanner sc, PredstavaServis predstavaServis) throws IOException {
		// Opcije Biletara
		if (uloga.equals("Biletar")) {
			pretragaPredstava(sc, predstavaServis);

			// Opcije menadzera
		} else if (uloga.equals("Menadzer")) {
			System.out.println("Unesite zeljenu opciju: ");
			System.out.println("1. Opcije pretrage predstava: ");
			System.out.println("2. Unos nove predstave.");
			System.out.println("3. Izmena postojece predstave.");
			System.out.println("4. Brisanje postojece predstave.");
			int vasaOpcija = Utility.ocitajBroj(sc);

			switch (vasaOpcija) {
			case 1:
				// Opcije pretrage predstava.
				pretragaPredstava(sc, predstavaServis);
				break;
			case 2:
				// Unos nove predstave.
				ArrayList<Predstava> svePredstave = predstavaServis.getListaPredstava();
				for (Predstava predstava : svePredstave) {
					System.out.println(predstava.getNazivPredstave());
				}
				boolean dostupanNaziv = false;
				String nazivNovePredstave = "";
				while (dostupanNaziv == false) {
					System.out.println("Unesite naziv nove predstave: ");
					nazivNovePredstave = Utility.ocitajTekst(sc);
					boolean postojeciNaziv = false;
					for (Predstava predstava : svePredstave) {
						if (predstava.getNazivPredstave().equalsIgnoreCase(nazivNovePredstave)) {
							postojeciNaziv = true;
						}
					}
					if (postojeciNaziv == false) {
						dostupanNaziv = true;
					}
				}
				boolean postojeciTipPredstave = false;
				int izabraniTipPredstave = 0;
				while (postojeciTipPredstave == false) {
					System.out.println("Unesite tip predstave: ");
					System.out.println("1. Drama.");
					System.out.println("2. Opera.");
					System.out.println("3. Balet.");
					izabraniTipPredstave = Utility.ocitajBroj(sc);
					if (izabraniTipPredstave > 0 || izabraniTipPredstave < 4) {
						postojeciTipPredstave = true;
					}
				}
				System.out.println("Unesite ime rezisera predstave: ");
				String reziserPredstave = Utility.ocitajTekst(sc);
				System.out.println("Unesite imena glumaca, odvojena zarezima: ");
				String imenaGlumacaPredstave = Utility.ocitajTekst(sc);
				System.out.println("Unesite trajanje predstave, u minutima: ");
				int trajanjePredstave = Utility.ocitajBroj(sc);
				System.out.println("Unesite naziv produkcije predstave: ");
				String produkcijaPredstave = Utility.ocitajTekst(sc);
				System.out.println("Unesite godinu premijere predsave: ");
				int godinaPremijerePredstave = Utility.ocitajBroj(sc);
				System.out.println("Unesite opis predstave: ");
				String opisPredstave = Utility.ocitajTekst(sc);
				predstavaServis.unosNovePredstave(nazivNovePredstave, izabraniTipPredstave, reziserPredstave,
						imenaGlumacaPredstave, trajanjePredstave, produkcijaPredstave, godinaPremijerePredstave,
						opisPredstave);
				System.out.println("Uspesno ste uneli novu predstavu.");
				break;
			case 3:
				// Izmena predstave.
				System.out.println("Unesite tacan naziv predstave koju zelite da izmenite: ");
				String nazivPredstaveIzmena = Utility.ocitajTekst(sc);
				Predstava pronadjenaPredstavaIzmena = predstavaServis.pronadjiPredstavu(nazivPredstaveIzmena);
				if (pronadjenaPredstavaIzmena == null) {
					System.out.println("Ne postoji predstava sa unetim nazivom!");
				} else {
					System.out.println(pronadjenaPredstavaIzmena);
					System.out.println("Da li zelite da izmenite tip predstave?");
					System.out.println("1. Da; 2. Ne");
					int izmena = Utility.ocitajBroj(sc);
					int izabraniTipPredstaveIzmena = 0;
					if (izmena == 1) {
						postojeciTipPredstave = false;
						while (postojeciTipPredstave == false) {
							System.out.println("Unesite tip predstave: ");
							System.out.println("1. Drama.");
							System.out.println("2. Opera.");
							System.out.println("3. Balet.");
							izabraniTipPredstaveIzmena = Utility.ocitajBroj(sc);
							if (izabraniTipPredstaveIzmena > 0 || izabraniTipPredstaveIzmena < 4) {
								postojeciTipPredstave = true;
							}
						}
					} else {
						izabraniTipPredstaveIzmena = pronadjenaPredstavaIzmena.getTipPredstave().ordinal();
					}
					System.out.println("Da li zelite da izmenite rezisera?");
					System.out.println("1. Da; 2. Ne");
					izmena = Utility.ocitajBroj(sc);
					String imeNovogRezisera = "";
					if (izmena == 1) {
						System.out.println("Unesite ime novog rezisera: ");
						imeNovogRezisera = Utility.ocitajTekst(sc);
					} else {
						imeNovogRezisera = pronadjenaPredstavaIzmena.getReziserPredstave();
					}
					System.out.println("Da li zelite da izmenite glumce?");
					System.out.println("1. Da; 2. Ne");
					izmena = Utility.ocitajBroj(sc);
					String noviGlumci = "";
					if (izmena == 1) {
						System.out.println("Unesite imena novih glumaca, odvojena zarezom: ");
						noviGlumci = Utility.ocitajTekst(sc);
					} else {
						noviGlumci = pronadjenaPredstavaIzmena.getGlumciPredstave();
					}
					System.out.println("Da li zelite da izmenite produkciju?");
					System.out.println("1. Da; 2. Ne");
					izmena = Utility.ocitajBroj(sc);
					String noviNazivProdukcije = "";
					if (izmena == 1) {
						System.out.println("Unesite naziv nove produkcije: ");
						noviNazivProdukcije = Utility.ocitajTekst(sc);
					} else {
						noviNazivProdukcije = pronadjenaPredstavaIzmena.getProdukcijaPredstave();
					}
					System.out.println("Da li zelite da izmenite godinu premijere?");
					System.out.println("1. Da; 2. Ne");
					izmena = Utility.ocitajBroj(sc);
					int novaGodinaPremijere = 0;
					if (izmena == 1) {
						System.out.println("Unesite novu godinu premijere: ");
						novaGodinaPremijere = Utility.ocitajBroj(sc);
					} else {
						novaGodinaPremijere = pronadjenaPredstavaIzmena.getGodinaPremijerePredstave();
					}
					System.out.println("Da li zelite da izmenite opis predstave?");
					System.out.println("1. Da; 2. Ne");
					izmena = Utility.ocitajBroj(sc);
					String noviOpis = "";
					if (izmena == 1) {
						System.out.println("Unesite novi opis predstave: ");
						noviOpis = Utility.ocitajTekst(sc);
					} else {
						noviOpis = pronadjenaPredstavaIzmena.getOpisPredstave();
					}
					predstavaServis.izmenaPredstave(pronadjenaPredstavaIzmena, izabraniTipPredstaveIzmena,
							imeNovogRezisera, noviGlumci, noviNazivProdukcije, novaGodinaPremijere, noviOpis);
					System.out.println("Uspesno ste izmenili predstavu.");

				}
				break;
			case 4:
				// Brisanje predstave.
				svePredstave = predstavaServis.getListaPredstava();
				for (Predstava predstava : svePredstave) {
					if (predstava.isAktivnostPredstave() == true) {
						System.out.println(predstava.getNazivPredstave());
					}
				}
				System.out.println("Unesite tacan naziv predstave koju zelite da izbrisete: ");
				String nzaivPredstaveBrisanje = Utility.ocitajTekst(sc);
				boolean postojecaPrestava = predstavaServis.brisanjePredstave(nzaivPredstaveBrisanje);
				if (postojecaPrestava == true) {
					System.out.println("Predstava je uspesno izbrisana");
				} else {
					System.out.println("Ne postoji predstava sa unetim nazivom!");
				}
				break;
			default:
				System.out.println("Uneli ste nepostojecu opciju!");
				break;
			}
		}
	}
}