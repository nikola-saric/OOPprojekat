package Konzola;

import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

import Entiteti.Izvodjenje;
import Entiteti.Predstava;
import Entiteti.Scena;
import Servisi.IzvodjenjeServis;

public class RadSaIzvodjenjima {

	public static void pretragaIzvodjenja(Scanner sc,
			IzvodjenjeServis izvodjenjeServis) {

		System.out.println("Unesite nacin pretrage izvodjenja: ");
		System.out.println("1. Pretraga po nazivu predstave.");
		System.out.println("2. Pretraga po tipu predstave.");
		System.out.println("3. Pretraga po godini premijere predstave.");
		System.out.println("4. Pretraga po reziseru predstave.");
		System.out
				.println("5. Pretraga po glumcima koji ucestvuju u predstavi.");
		System.out.println("6. Pretraga po nazivu, reziseru i glumcima.");
		System.out.println("7. Pretraga po vremenu pocetka predstave.");
		System.out.println("8. Pretraga po nazivu scene.");
		int vasaOpcija = Utility.ocitajBroj(sc);
		ArrayList<Izvodjenje> pronadjenaIzvodjenja = new ArrayList<>();
		switch (vasaOpcija) {
		case 1:
			System.out.println("Unesite naziv predstave: ");
			String nazivPredstavePret = Utility.ocitajTekst(sc);
			pronadjenaIzvodjenja = izvodjenjeServis.pretragaStringova(
					nazivPredstavePret, vasaOpcija);
			break;
		case 2:
			boolean postojeciTip = false;
			int odabraniTip = 0;
			while (postojeciTip == false) {
				System.out.println("Izaberite tip predstave: ");
				System.out.println("1. Drama; 2. Opera; 3. Balet");
				odabraniTip = Utility.ocitajBroj(sc);
				if ((odabraniTip > 0) && (odabraniTip < 4)) {
					postojeciTip = true;
				}
				System.out
						.println("Uneli ste nepostojeci tip, pokusajte ponovo!");
			}
			pronadjenaIzvodjenja = izvodjenjeServis
					.pretragaPoTipuPredstave(odabraniTip);
			break;
		case 3:
			System.out.println("Unesite godinu premijere predstave: ");
			int godinaPremPret = Utility.ocitajBroj(sc);
			pronadjenaIzvodjenja = izvodjenjeServis
					.pretragaPoGodiniPremijere(godinaPremPret);
			break;
		case 4:
			System.out.println("Unesite ime rezisera predstave: ");
			String reziserPredstavePret = Utility.ocitajTekst(sc);
			pronadjenaIzvodjenja = izvodjenjeServis.pretragaStringova(
					reziserPredstavePret, vasaOpcija);
			break;
		case 5:
			System.out.println("Unesite imena glumaca: ");
			String glumciPredstavePret = Utility.ocitajTekst(sc);
			pronadjenaIzvodjenja = izvodjenjeServis.pretragaStringova(
					glumciPredstavePret, vasaOpcija);
			break;
		case 6:
			System.out.println("Unesite naziv predstave, rezisera i glumce: ");
			String nazRezGlumPret = Utility.ocitajTekst(sc);
			pronadjenaIzvodjenja = izvodjenjeServis.pretragaStringova(
					nazRezGlumPret, vasaOpcija);
			break;
		case 7:
			Date pocDatum = null;
			Date krajDatum = null;
			boolean tacanDatum = false;
			while (tacanDatum == false) {
				System.out.println("Unesite pocetnu godinu: ");
				int pocGodina = Utility.ocitajBroj(sc);
				System.out.println("Unesite pocetni mesec: ");
				int pocMesec = Utility.ocitajBroj(sc);
				System.out.println("Unesite pocetni dan: ");
				int pocDan = Utility.ocitajBroj(sc);
				System.out.println("Unesite pocetni sat: ");
				int pocSat = Utility.ocitajBroj(sc);
				System.out.println("Unesite pocetni min: ");
				int pocMinut = Utility.ocitajBroj(sc);

				System.out.println("Unesite krajnju godinu: ");
				int krajGodina = Utility.ocitajBroj(sc);
				System.out.println("Unesite krajnji mesec: ");
				int krajMesec = Utility.ocitajBroj(sc);
				System.out.println("Unesite krajnji dan: ");
				int krajDan = Utility.ocitajBroj(sc);
				System.out.println("Unesite krajnji sat: ");
				int krajSat = Utility.ocitajBroj(sc);
				System.out.println("Unesite krajnji min: ");
				int krajMinut = Utility.ocitajBroj(sc);
				Calendar calendar = new GregorianCalendar();
				calendar.set(pocGodina, pocMesec, pocDan, pocSat, pocMinut);
				pocDatum = calendar.getTime();
				calendar.set(krajGodina, krajMesec, krajDan, krajSat, krajMinut);
				krajDatum = calendar.getTime();
				if (pocDatum.before(krajDatum)) {
					tacanDatum = true;
				} else {
					System.out.println("Uneti krajnji datum je pre pocetnog!");
				}
			}
			pronadjenaIzvodjenja = izvodjenjeServis.pretragaPoVremenuPocetka(
					pocDatum, krajDatum);
			break;
		case 8:
			System.out.println("Unesite naziv scene: ");
			String nazivScenePret = Utility.ocitajTekst(sc);
			pronadjenaIzvodjenja = izvodjenjeServis.pretragaStringova(
					nazivScenePret, vasaOpcija);
			break;
		default:
			System.out.println("Uneli ste nepostojecu opciju!");
			break;
		}
		// Sortiranje pronadjenih izvodjenja.
		if (pronadjenaIzvodjenja.size() > 0) {
			int opcijaSortiranja = 0;
			while (opcijaSortiranja < 1 || opcijaSortiranja > 7) {
				System.out.println("Izaberite opciju sortiranja: ");
				System.out.println("1. Sortiranje po nazivu predstave.");
				System.out
						.println("2. Sortiranje po vremenu pocetka izvodjenja.");
				System.out
						.println("3. Sortiranje po vremenu pocetka i predstave.");
				System.out.println("4. Sortiranje po tipu predstave.");
				System.out.println("5. Sortiranje po godini premijere.");
				System.out
						.println("6. Sortiranje po tipu i godini premijere predstave.");
				System.out.println("7. Sortiranje po nazivu scene.");
			}
			ArrayList<Izvodjenje> sortiranaIzvodjenja = izvodjenjeServis
					.sortiranjeIzvodjenja(pronadjenaIzvodjenja,
							opcijaSortiranja);
			for (Izvodjenje izvodjenje : sortiranaIzvodjenja) {
				System.out.println(izvodjenje);
			}
		}
	}

	public static Date unosDatuma(Scanner sc) {
		Calendar calendar = new GregorianCalendar();
		int godinaIzvodjenja = Year.now().getValue();

		boolean postojeciDan = false;
		int danIzvodjenja = 0;
		while (postojeciDan == false) {
			System.out.println("Unesite dan izvodjenja: ");
			danIzvodjenja = Utility.ocitajBroj(sc);
			if (danIzvodjenja > 0 && danIzvodjenja < 32) {
				postojeciDan = true;
			} else {
				System.out.println("Uneli ste nepostojeci dan u mesecu!");
			}
		}
		boolean postojeciMesec = false;
		int mesecIzvodjenja = 0;
		while (postojeciMesec == false) {
			System.out.println("Unesite mesec izvodjenja: ");
			mesecIzvodjenja = Utility.ocitajBroj(sc) - 1;
			if (mesecIzvodjenja > -1 && mesecIzvodjenja < 12) {
				postojeciMesec = true;
			} else {
				System.out.println("Uneli ste nepostojeci mesec!");
			}
		}
		boolean postojeciSat = false;
		int satIzvodjenja = 0;
		while (postojeciSat == false) {
			System.out.println("Unesite sat izvodjenja: ");
			satIzvodjenja = Utility.ocitajBroj(sc);
			if (satIzvodjenja > -1 && satIzvodjenja < 24) {
				postojeciSat = true;
			} else {
				System.out.println("Uneli ste nepostojeci sat!");
			}
		}
		boolean postojeciMinut = false;
		int minutIzvodjenja = 0;
		while (postojeciMinut == false) {
			System.out.println("Unesite minute izvodjenja: ");
			minutIzvodjenja = Utility.ocitajBroj(sc);
			if (minutIzvodjenja > -1 && minutIzvodjenja < 60) {
				postojeciMinut = true;
			} else {
				System.out.println("Uneli ste nepostojeci broj minuta!");
			}
		}
		calendar.set(godinaIzvodjenja, mesecIzvodjenja, danIzvodjenja,
				satIzvodjenja, minutIzvodjenja);
		Date pocetakIzvodjenja = calendar.getTime();

		return pocetakIzvodjenja;
	}

	public static void radSaIzvodjenjima(String uloga, Scanner sc,
			IzvodjenjeServis izvodjenjeServis) throws IOException {
		// Opcije Biletara
		if (uloga.equals("Biletar")) {
			pretragaIzvodjenja(sc, izvodjenjeServis);
			// Opcije Menadzera
		} else if (uloga.equals("Menadzer")) {
			System.out.println("Unesite zeljenu opciju: ");
			System.out.println("1. Pretraga izvodjenja.");
			System.out.println("2. Unos novog izvodjenja.");
			System.out.println("3. Brisanje izvodjenja.");
			int vasaOpcija = Utility.ocitajBroj(sc);

			switch (vasaOpcija) {
			case 1:
				pretragaIzvodjenja(sc, izvodjenjeServis);
				break;
			case 2:
				for (Predstava predstava : izvodjenjeServis
						.getPredstavaServis().getListaPredstava()) {
					System.out.println(predstava.getNazivPredstave());
				}
				boolean postojecaPredstava = false;
				String nazivPredstave = "";
				Predstava predstavaIzvodjenja = null;
				while (postojecaPredstava == false) {
					System.out.println("Unesite naziv predstave izvodjenja: ");
					nazivPredstave = Utility.ocitajTekst(sc);
					for (Predstava predstava : izvodjenjeServis
							.getPredstavaServis().getListaPredstava()) {
						if (predstava.getNazivPredstave().equalsIgnoreCase(
								nazivPredstave)) {
							predstavaIzvodjenja = predstava;
							postojecaPredstava = true;
						}
					}
				}
				Date pocetakIzvodjenja = unosDatuma(sc);

				for (Scena scena : izvodjenjeServis.getScenaServis()
						.getListaScena()) {
					System.out.println(scena.getNazivScene());
				}
				boolean postojecaScena = false;
				String nazivScene = "";
				Scena scenaIzvodjenja = null;
				while (postojecaScena == false) {
					System.out.println("Unesite naziv scene izvodjenja");
					nazivScene = Utility.ocitajTekst(sc);
					// Proverava da li postoji scena sa unetim imenom, ako
					// postoji proverava se da li se na pronadjenoj sceni vec
					// izvodi predstava, ili je scena slobodna.
					for (Scena scena : izvodjenjeServis.getScenaServis()
							.getListaScena()) {
						if (scena.getNazivScene().equalsIgnoreCase(nazivScene)) {
							Date krajIzvodjenja = izvodjenjeServis
									.krajIzvodjenja(predstavaIzvodjenja,
											pocetakIzvodjenja);

							boolean zauzetaScena = false;
							for (Izvodjenje izvodjenje : izvodjenjeServis
									.getListaIzvodjenja()) {
								Date getKrajIzvodjenja = izvodjenjeServis
										.krajIzvodjenja(izvodjenje
												.getPredstavaIzvodjenja(),
												izvodjenje
														.getPocetakIzvodjenja());
								if ((izvodjenje.getScenaIzvodjenja() == scena)
										&& (pocetakIzvodjenja.before(izvodjenje
												.getPocetakIzvodjenja()) == true)
										&& (krajIzvodjenja.before(izvodjenje
												.getPocetakIzvodjenja()) == false)) {
									System.out
											.println("Izabrana scena je zauzeta, unesite drugu!");
									zauzetaScena = true;
								} else if ((izvodjenje.getScenaIzvodjenja() == scena)
										&& (izvodjenje.getPocetakIzvodjenja()
												.before(pocetakIzvodjenja) == true)
										&& (getKrajIzvodjenja
												.before(pocetakIzvodjenja) == false)) {
									System.out
											.println("Izabrana scena je zauzeta, unesite drugu!");
									zauzetaScena = true;
								}
							}
							if (zauzetaScena == false) {
								scenaIzvodjenja = scena;
								postojecaScena = true;
							}
						}
					}
				}
				System.out.println("Unesite cenu karte ovog izvodjenja: ");
				double cenaIzvodjenja = Utility.ocitajDouble(sc);
				izvodjenjeServis.dodajIzvodjenje(predstavaIzvodjenja,
						scenaIzvodjenja, pocetakIzvodjenja, cenaIzvodjenja);
				System.out.println("Uspesno ste dodali novo izvodjenje!");
				break;
			case 3:
				// Brisanje izvodjenja.
				for (Izvodjenje izvodjenje : izvodjenjeServis
						.getListaIzvodjenja()) {
					System.out.println(izvodjenje);
				}
				System.out
						.println("Unesite identifikator izvodjenja koje zelite da obrisete: ");
				int identifikatorIzvodjenja = Utility.ocitajBroj(sc);
				boolean uspesnoBrisanjeIzvodjenja = izvodjenjeServis
						.brisanjeIzvodjenja(identifikatorIzvodjenja);
				if (uspesnoBrisanjeIzvodjenja == true) {
					System.out
							.println("Odabrano izvodjenje je uspesno izbrisano.");
				} else {
					System.out
							.println("Ne postoji izvodjenje sa unetim identifikatorom ili za njega postoje prodate karte.");
				}
				break;
			default:
				System.out.println("Uneli ste nepostojecu opciju!");
				break;
			}

		}
	}
}
