package Servisi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

import Entiteti.Korisnik;
import Entiteti.Menadzer;
import Entiteti.Predstava;
import Entiteti.TipPredstave;
import Komparatori.PredstavaGodinaPremijereKomparator;
import Komparatori.PredstavaNazivKomparator;
import Konzola.MainPetlja;

public class PredstavaServis {
	private ArrayList<Predstava> listaPredstava = new ArrayList<>();
	private String sp = System.getProperty("file.separator");

	public void ucitajPredstave() throws IOException {
		String trenutnaLinija;
		BufferedReader bf = new BufferedReader(new FileReader("." + this.sp
				+ "src" + this.sp + "Datoteke" + this.sp + "Predstave"));

		while ((trenutnaLinija = bf.readLine()) != null) {
			String[] sL = trenutnaLinija.split("\\|");
			TipPredstave tipPredstave = TipPredstave.valueOfOrdinal(Integer
					.parseInt(sL[1]));
			int trajanjePredstave = Integer.parseInt(sL[4]);
			int godinaPremijere = Integer.parseInt(sL[6]);
			boolean aktivnostPredstave = false;
			if (sL[8].equals("true")) {
				aktivnostPredstave = true;
			} else if (sL[8].equals("false")) {
				aktivnostPredstave = false;
			}
			Predstava ucitanaPredstava = new Predstava(sL[0], tipPredstave,
					sL[2], sL[3], trajanjePredstave, sL[5], godinaPremijere,
					sL[7], aktivnostPredstave);
			this.listaPredstava.add(ucitanaPredstava);
		}
		bf.close();
	}

	public void upisiPredstave() throws IOException {
		PrintWriter upisiPredstave = new PrintWriter(new FileWriter("."
				+ this.sp + "src" + this.sp + "Datoteke" + this.sp
				+ "Predstave", false));
		for (Predstava predstava : this.listaPredstava) {
			String strPredstava = predstava.getNazivPredstave() + "|"
					+ (predstava.getTipPredstave().ordinal() + 1) + "|"
					+ predstava.getReziserPredstave() + "|"
					+ predstava.getGlumciPredstave() + "|"
					+ predstava.getTrajanjePredstave() + "|"
					+ predstava.getProdukcijaPredstave() + "|"
					+ predstava.getGodinaPremijerePredstave() + "|"
					+ predstava.getOpisPredstave() + "|"
					+ predstava.isAktivnostPredstave();
			upisiPredstave.println(strPredstava);
		}
		upisiPredstave.close();
	}

	public Predstava pronadjiPredstavu(String nazivPredstave) {
		Predstava pronadjenaPredstava = null;
		for (Predstava predstava : this.listaPredstava) {
			if ((predstava.getNazivPredstave().equalsIgnoreCase(nazivPredstave))
					&& (predstava.isAktivnostPredstave() == true)) {
				pronadjenaPredstava = predstava;
			}
		}
		return pronadjenaPredstava;
	}

	public ArrayList<Predstava> pretragaPredstava(String unetaRec,
			int opcijaPretrage) {
		ArrayList<Predstava> pronadjenePredstave = new ArrayList<>();
		if (unetaRec.equals("")) {
			for (Predstava predstava : this.listaPredstava) {
				if (predstava.isAktivnostPredstave() == true) {
					pronadjenePredstave.add(predstava);
				}
			}
		} else {
			if (opcijaPretrage == 1) {
				for (Predstava predstava : this.listaPredstava) {
					if ((predstava.getNazivPredstave().toLowerCase()
							.contains(unetaRec.toLowerCase()))
							&& (predstava.isAktivnostPredstave() == true)) {
						pronadjenePredstave.add(predstava);
					}
				}
			} else if (opcijaPretrage == 2) {
				for (Predstava predstava : this.listaPredstava) {
					String godPrem = Integer.toString(predstava
							.getGodinaPremijerePredstave());
					if ((godPrem.contains(unetaRec) && (predstava
							.isAktivnostPredstave() == true))) {
						pronadjenePredstave.add(predstava);
					}
				}
			}
		}
		return pronadjenePredstave;
	}

	public ArrayList<Predstava> sortiranjePredstava(
			ArrayList<Predstava> pronadjenePredstave, int opcijaSortiranja) {
		switch (opcijaSortiranja) {
		case 1:
			// Sortiranje po nazivu predstave.
			Collections.sort(pronadjenePredstave,
					new PredstavaNazivKomparator());
			break;
		case 2:
			// Sortiranje po godini premijere predstave.
			Collections.sort(pronadjenePredstave,
					new PredstavaGodinaPremijereKomparator());
			break;
		}
		return pronadjenePredstave;
	}

	public void izmenaPredstave(Predstava pronadjenaPredstava, int noviTip,
			String noviReziser, String noviGlumci, String novaProdukcija,
			int novaGodPrem, String noviOpis) {
		pronadjenaPredstava.setTipPredstave(TipPredstave
				.valueOfOrdinal(noviTip));
		pronadjenaPredstava.setReziserPredstave(noviReziser);
		pronadjenaPredstava.setGlumciPredstave(noviGlumci);
		pronadjenaPredstava.setProdukcijaPredstave(novaProdukcija);
		pronadjenaPredstava.setGodinaPremijerePredstave(novaGodPrem);
		pronadjenaPredstava.setOpisPredstave(noviOpis);
	}

	public boolean brisanjePredstave(String nazivPredstave) {
		boolean postojecaPredstava = false;
		for (Predstava predstava : this.listaPredstava) {
			if (predstava.getNazivPredstave().equalsIgnoreCase(nazivPredstave)) {
				predstava.setAktivnostPredstave(false);
				postojecaPredstava = true;
			}
		}
		return postojecaPredstava;
	}

	public void unosNovePredstave(String naziv, int tipPredstave,
			String imeRezisera, String glumci, int trajanje, String produkcija,
			int godinaPremijere, String opis) {
		boolean aktivnostPredstave = true;
		Predstava novaPredstava = new Predstava(naziv,
				TipPredstave.valueOfOrdinal(tipPredstave), imeRezisera, glumci,
				trajanje, produkcija, godinaPremijere, opis, aktivnostPredstave);
		this.listaPredstava.add(novaPredstava);
		Korisnik ulogovaniKorisnik = MainPetlja.getUlogovaniKorisnik();
		if (ulogovaniKorisnik.getClass() == Menadzer.class) {
			Menadzer ulogovaniMenadzer = (Menadzer) ulogovaniKorisnik;
			ulogovaniMenadzer.getDodatePredstave().add(novaPredstava);
		}
	}

	public ArrayList<Predstava> getListaPredstava() {
		return listaPredstava;
	}

	public void setListaPredstava(ArrayList<Predstava> listaPredstava) {
		this.listaPredstava = listaPredstava;
	}

}