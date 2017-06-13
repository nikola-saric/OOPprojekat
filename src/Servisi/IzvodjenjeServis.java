package Servisi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;

import Entiteti.Izvodjenje;
import Entiteti.Karta;
import Entiteti.Predstava;
import Entiteti.Scena;
import Entiteti.TipPredstave;
import Komparatori.IzvodjenjeGodinaPremijerePredstaveKomparator;
import Komparatori.IzvodjenjeNazivIPocetakKomparator;
import Komparatori.IzvodjenjeNazivPredstaveKomparator;
import Komparatori.IzvodjenjeNazivSceneKomparator;
import Komparatori.IzvodjenjeTipIGodinaKomparator;
import Komparatori.IzvodjenjeTipPredstaveKoparator;
import Komparatori.IzvodjenjeVremePocetkaKomparator;

public class IzvodjenjeServis {
	private ArrayList<Izvodjenje> listaIzvodjenja = new ArrayList<Izvodjenje>();
	private String sp = System.getProperty("file.separator");
	private PredstavaServis predstavaServis;
	private ScenaServis scenaServis;

	public IzvodjenjeServis(PredstavaServis predstavaServis, ScenaServis scenaServis) {
		this.predstavaServis = predstavaServis;
		this.scenaServis = scenaServis;
	}

	public void ucitajIzvodjenja() throws IOException {
		String trenutnaLinija;
		BufferedReader bf = new BufferedReader(
				new FileReader("." + this.sp + "src" + this.sp + "Datoteke" + this.sp + "Izvodjenja"));
		while ((trenutnaLinija = bf.readLine()) != null) {
			String[] sL = trenutnaLinija.split("\\|");
			boolean aktivnostIzvodjenja = false;
			if (sL[5].equals("true")) {
				aktivnostIzvodjenja = true;
			} else if (sL[5].equals("false")) {
				aktivnostIzvodjenja = false;
			}
			int identifikatorIzvodjenja = Integer.parseInt(sL[0]);
			double cenaIzvodjenja = Double.parseDouble(sL[2]);
			String nazivPredstaveIzvodjenja = sL[3];
			Predstava predstavaIzvodjenja = null;
			for (Predstava predstava : this.predstavaServis.getListaPredstava()) {
				if (predstava.getNazivPredstave().equalsIgnoreCase(nazivPredstaveIzvodjenja)) {
					predstavaIzvodjenja = predstava;
				}
			}
			String nazivSceneIzvodjenja = sL[4];
			Scena scenaIzvodjenja = null;
			for (Scena scena : this.scenaServis.getListaScena()) {
				if (scena.getNazivScene().equalsIgnoreCase(nazivSceneIzvodjenja)) {
					scenaIzvodjenja = scena;
				}
			}
			ArrayList<Karta> listaProdatihKarataIzvodjenja = new ArrayList<>();
			String[] strVremeIzvodjenja = sL[1].split(";");
			int danIzv = Integer.parseInt(strVremeIzvodjenja[1]);
			int mesecIzv = Integer.parseInt(strVremeIzvodjenja[0]) - 1;
			int godinaIzv = Integer.parseInt(strVremeIzvodjenja[2]);
			int satIzv = Integer.parseInt(strVremeIzvodjenja[3]);
			int minIzv = Integer.parseInt(strVremeIzvodjenja[4]);
			Calendar calendar = new GregorianCalendar();
			calendar.set(godinaIzv, mesecIzv, danIzv, satIzv, minIzv);
			Date vremeIzvodjenja = calendar.getTime();

			Izvodjenje ucitanoIzvodjenje = new Izvodjenje(identifikatorIzvodjenja, vremeIzvodjenja, cenaIzvodjenja,
					predstavaIzvodjenja, scenaIzvodjenja, listaProdatihKarataIzvodjenja, aktivnostIzvodjenja);
			this.listaIzvodjenja.add(ucitanoIzvodjenje);
		}
		bf.close();
	}

	public void upisiIzvodjenja() throws IOException {
		PrintWriter upisiIzvodjenja = new PrintWriter(
				new FileWriter("." + this.sp + "src" + this.sp + "Datoteke" + this.sp + "Izvodjenja", false));
		for (Izvodjenje izvodjenje : this.listaIzvodjenja) {
			DateFormat df = new SimpleDateFormat("MM;dd;yyyy;HH;mm");
			String reportDate = df.format(izvodjenje.getPocetakIzvodjenja());
			String prodateKarte = "";
			for (Karta karta : izvodjenje.getProdateKarteIzvodjenja()) {
				prodateKarte += karta.getSerijskiBrojKarte() + ";";
			}
			String strUpisIzv = izvodjenje.getIdentifikatorIzvodjenja() + "|" + reportDate + "|"
					+ izvodjenje.getCenaKarteIzvodjenja() + "|"
					+ izvodjenje.getPredstavaIzvodjenja().getNazivPredstave() + "|"
					+ izvodjenje.getScenaIzvodjenja().getNazivScene() + "|" + izvodjenje.isAktivnostIzvodjenja() + "|"
					+ prodateKarte;
			upisiIzvodjenja.println(strUpisIzv);
		}
		upisiIzvodjenja.close();
	}

	public void ucitajKarteIzvodjenja(KartaServis kartaServis) throws NumberFormatException, IOException {
		String trenutnaLinija;
		BufferedReader bf = new BufferedReader(
				new FileReader("." + this.sp + "src" + this.sp + "Datoteke" + this.sp + "Izvodjenja"));
		while ((trenutnaLinija = bf.readLine()) != null) {
			String[] sL = trenutnaLinija.split("\\|");
			if (sL.length == 7) {
				String[] strUcitaneKarte = sL[6].split(";");
				for (Izvodjenje izvodjenje : this.listaIzvodjenja) {
					if (izvodjenje.getIdentifikatorIzvodjenja() == Integer.parseInt(sL[0])) {
						for (int i = 0; i < strUcitaneKarte.length; i++) {
							for (Karta karta : kartaServis.getListaKarata()) {
								if (karta.getSerijskiBrojKarte().equals(strUcitaneKarte[i])) {
									izvodjenje.getProdateKarteIzvodjenja().add(karta);
								}
							}
						}
					}
				}
			}
		}
		bf.close();
	}

	public ArrayList<Izvodjenje> pretragaStringova(String unetaRec, int izabranaOpcija) {
		ArrayList<Izvodjenje> pronadjenaIzvodjenja = new ArrayList<>();
		if (unetaRec == "") {
			for (Izvodjenje izvodjenje : this.listaIzvodjenja) {
				if (izvodjenje.isAktivnostIzvodjenja() == true) {
					pronadjenaIzvodjenja.add(izvodjenje);
				}
			}
		} else if (izabranaOpcija == 1) {
			for (Izvodjenje izvodjenje : this.listaIzvodjenja) {
				if ((izvodjenje.getPredstavaIzvodjenja().getNazivPredstave().toLowerCase()
						.contains(unetaRec.toLowerCase())) && (izvodjenje.isAktivnostIzvodjenja() == true)) {
					pronadjenaIzvodjenja.add(izvodjenje);
				}

			}
		} else if (izabranaOpcija == 4) {
			for (Izvodjenje izvodjenje : this.listaIzvodjenja) {
				if ((izvodjenje.getPredstavaIzvodjenja().getReziserPredstave().toLowerCase()
						.contains(unetaRec.toLowerCase())) && izvodjenje.isAktivnostIzvodjenja() == true) {
					pronadjenaIzvodjenja.add(izvodjenje);
				}

			}
		} else if (izabranaOpcija == 5) {
			for (Izvodjenje izvodjenje : this.listaIzvodjenja) {
				if ((izvodjenje.getPredstavaIzvodjenja().getGlumciPredstave().toLowerCase()
						.contains(unetaRec.toLowerCase())) && izvodjenje.isAktivnostIzvodjenja() == true) {
					pronadjenaIzvodjenja.add(izvodjenje);
				}
			}
		} else if (izabranaOpcija == 6) {
			for (Izvodjenje izvodjenje : this.listaIzvodjenja) {
				String nazRezGlum = izvodjenje.getPredstavaIzvodjenja().getNazivPredstave() + " "
						+ izvodjenje.getPredstavaIzvodjenja().getReziserPredstave() + " "
						+ izvodjenje.getPredstavaIzvodjenja().getGlumciPredstave();
				if ((nazRezGlum.toLowerCase().contains(unetaRec.toLowerCase()))
						&& izvodjenje.isAktivnostIzvodjenja() == true) {
					pronadjenaIzvodjenja.add(izvodjenje);
				}
			}
		} else if (izabranaOpcija == 8) {
			for (Izvodjenje izvodjenje : this.listaIzvodjenja) {
				if ((izvodjenje.getScenaIzvodjenja().getNazivScene().toLowerCase().contains(unetaRec.toLowerCase()))
						&& izvodjenje.isAktivnostIzvodjenja() == true) {
					pronadjenaIzvodjenja.add(izvodjenje);
				}
			}
		}

		return pronadjenaIzvodjenja;
	}

	public ArrayList<Izvodjenje> pretragaPoTipuPredstave(int tip) {
		ArrayList<Izvodjenje> pronadjenaIzvodjenja = new ArrayList<>();

		for (Izvodjenje izvodjenje : this.listaIzvodjenja) {
			if ((izvodjenje.getPredstavaIzvodjenja().getTipPredstave().equals(TipPredstave.valueOfOrdinal(tip)))
					&& (izvodjenje.isAktivnostIzvodjenja() == true)) {
				pronadjenaIzvodjenja.add(izvodjenje);
			}
		}
		return pronadjenaIzvodjenja;
	}

	public ArrayList<Izvodjenje> pretragaPoGodiniPremijere(int godinaPremijere) {
		ArrayList<Izvodjenje> pronadjenaIzvodjenja = new ArrayList<>();
		for (Izvodjenje izvodjenje : this.listaIzvodjenja) {
			if ((izvodjenje.getPredstavaIzvodjenja().getGodinaPremijerePredstave() == godinaPremijere)
					&& (izvodjenje.isAktivnostIzvodjenja())) {
				pronadjenaIzvodjenja.add(izvodjenje);
			}
		}
		return pronadjenaIzvodjenja;
	}

	public ArrayList<Izvodjenje> pretragaPoVremenuPocetka(Date pocDatum, Date krajDatum) {
		ArrayList<Izvodjenje> pronadjenaIzvodjenja = new ArrayList<>();
		for (Izvodjenje izvodenje : this.getListaIzvodjenja()) {
			if ((pocDatum.before(izvodenje.getPocetakIzvodjenja()))
					&& (krajDatum.after(izvodenje.getPocetakIzvodjenja()))) {
				pronadjenaIzvodjenja.add(izvodenje);
			}
		}
		return pronadjenaIzvodjenja;
	}

	public ArrayList<Izvodjenje> sortiranjeIzvodjenja(ArrayList<Izvodjenje> pronadjenaIzvodjenja,
			int opcijaSortiranja) {
		switch (opcijaSortiranja) {
		case 1:
			// Sortiranje po nazivu predstave izvodjenja.
			Collections.sort(pronadjenaIzvodjenja, new IzvodjenjeNazivPredstaveKomparator());
			break;
		case 2:
			// Sortiranje po pocetku izvodjenja.
			Collections.sort(pronadjenaIzvodjenja, new IzvodjenjeVremePocetkaKomparator());
			break;
		case 3:
			// Sortiranje po nazivu predstave i pocetku izvodjenja.
			Collections.sort(pronadjenaIzvodjenja, new IzvodjenjeNazivIPocetakKomparator());
			break;
		case 4:
			// Sortiranje po tipu predstave.
			Collections.sort(pronadjenaIzvodjenja, new IzvodjenjeTipPredstaveKoparator());
			break;
		case 5:
			// Sortiranje po godini premijere predstave.
			Collections.sort(pronadjenaIzvodjenja, new IzvodjenjeGodinaPremijerePredstaveKomparator());
			break;
		case 6:
			// Sortiranje po tipu i godini premijere predstave.
			Collections.sort(pronadjenaIzvodjenja, new IzvodjenjeTipIGodinaKomparator());
			break;
		case 7:
			// Sortiranje po nazivu scene.
			Collections.sort(pronadjenaIzvodjenja, new IzvodjenjeNazivSceneKomparator());
			break;
		}
		return pronadjenaIzvodjenja;
	}

	// Vraca Date zavrsetka izvodjenja na osnovu trajanja predstave i Date
	// pocetka izvodjenja.
	public Date krajIzvodjenja(Predstava predstava, Date pocetakIzvodjenja) {
		int trajanjeIzvodjenja = predstava.getTrajanjePredstave();
		DateFormat df = new SimpleDateFormat("MM;dd;yyyy;HH;mm");
		String pI[] = df.format(pocetakIzvodjenja).split(";");
		Calendar calendar = new GregorianCalendar();
		calendar.set(Integer.parseInt(pI[2]), Integer.parseInt(pI[0]), Integer.parseInt(pI[1]), Integer.parseInt(pI[3]),
				Integer.parseInt(pI[4]) + trajanjeIzvodjenja);
		Date krajIzvodjenja = calendar.getTime();

		return krajIzvodjenja;

	}

	public void dodajIzvodjenje(Predstava predstava, Scena scena, Date pocetakIzvodjenja, double cena)
			throws IOException {
		ArrayList<Karta> listaProdatihKarata = new ArrayList<>();
		boolean aktivnostIzvodjenja = true;
		int identifikatorIzvodjenja = this.listaIzvodjenja.size() + 1;

		Izvodjenje novoIzvodjenje = new Izvodjenje(identifikatorIzvodjenja, pocetakIzvodjenja, cena, predstava, scena,
				listaProdatihKarata, aktivnostIzvodjenja);
		this.listaIzvodjenja.add(novoIzvodjenje);
		this.upisiIzvodjenja();
	}

	public boolean brisanjeIzvodjenja(int identifikator) throws IOException {
		boolean uspesnoObrisanoIzvodjenje = false;
		for (Izvodjenje izvodjenje : this.listaIzvodjenja) {
			if (izvodjenje.getIdentifikatorIzvodjenja() == identifikator
					&& izvodjenje.getProdateKarteIzvodjenja().size() == 0) {
				izvodjenje.setAktivnostIzvodjenja(false);
			}
		}
		this.upisiIzvodjenja();
		return uspesnoObrisanoIzvodjenje;
	}

	public ArrayList<Izvodjenje> getListaIzvodjenja() {
		return listaIzvodjenja;
	}

	public void setListaIzvodjenja(ArrayList<Izvodjenje> listaIzvodjenja) {
		this.listaIzvodjenja = listaIzvodjenja;
	}

	public PredstavaServis getPredstavaServis() {
		return predstavaServis;
	}

	public void setPredstavaServis(PredstavaServis predstavaServis) {
		this.predstavaServis = predstavaServis;
	}

	public ScenaServis getScenaServis() {
		return scenaServis;
	}

	public void setScenaServis(ScenaServis scenaServis) {
		this.scenaServis = scenaServis;
	}

}