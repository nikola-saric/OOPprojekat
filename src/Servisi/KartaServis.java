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

import Entiteti.Biletar;
import Entiteti.Izvodjenje;
import Entiteti.Karta;
import Entiteti.Korisnik;
import Entiteti.Sediste;
import Komparatori.KartaNazivPocetakIzdavanjeKomparator;
import Komparatori.KartaPopustKomparator;
import Komparatori.KartaVremeIzdavanjaKomparator;
import Konzola.MainPetlja;

public class KartaServis {
	private ArrayList<Karta> listaKarata = new ArrayList<>();
	private IzvodjenjeServis izvodjenjeServis;
	private String sp = System.getProperty("file.separator");

	public KartaServis(IzvodjenjeServis izvodjenjeServis) {
		this.izvodjenjeServis = izvodjenjeServis;
	}

	public void ucitajKarte() throws IOException {
		String trenutnaLinija;
		BufferedReader bf = new BufferedReader(new FileReader("." + this.sp
				+ "src" + this.sp + "Datoteke" + this.sp + "Karte"));
		while ((trenutnaLinija = bf.readLine()) != null) {
			String[] sL = trenutnaLinija.split("\\|");

			String serijskiBroj = sL[0];
			int popust = Integer.parseInt(sL[1]);

			String[] strVremeIzdavanja = sL[2].split(";");
			int danIzv = Integer.parseInt(strVremeIzdavanja[1]);
			int mesecIzv = Integer.parseInt(strVremeIzdavanja[0]) - 1;
			int godinaIzv = Integer.parseInt(strVremeIzdavanja[2]);
			int satIzv = Integer.parseInt(strVremeIzdavanja[3]);
			int minIzv = Integer.parseInt(strVremeIzdavanja[4]);
			Calendar calendar = new GregorianCalendar();
			calendar.set(godinaIzv, mesecIzv, danIzv, satIzv, minIzv);
			Date vremeIzdavanja = calendar.getTime();
			int identifikatorIzvodjenja = Integer.parseInt(sL[3]);

			Izvodjenje izvodjenjeKarta = null;
			for (Izvodjenje izvodjenje : this.izvodjenjeServis
					.getListaIzvodjenja()) {
				if (izvodjenje.getIdentifikatorIzvodjenja() == identifikatorIzvodjenja) {
					izvodjenjeKarta = izvodjenje;
				}
			}
			String[] sedista = sL[4].split("x");
			int redSedista = Integer.parseInt(sedista[0]);
			int brojSedista = Integer.parseInt(sedista[1]);

			Sediste sedisteKarte = null;
			for (Sediste sediste : izvodjenjeKarta.getScenaIzvodjenja()
					.getSedistaScene()) {
				if ((sediste.getRedSedista() == redSedista)
						&& (sediste.getBrojSedista() == brojSedista)) {
					sedisteKarte = sediste;
				}
			}
			double cena = izvodjenjeKarta.getCenaKarteIzvodjenja();
			if (popust > 0) {
				cena = cena - (cena / popust);
			}

			Karta ucitanaKarta = new Karta(serijskiBroj, cena, popust,
					vremeIzdavanja, izvodjenjeKarta, sedisteKarte);
			this.listaKarata.add(ucitanaKarta);
		}
		bf.close();
	}

	public void upisiKarte() throws IOException {
		PrintWriter upisiKartu = new PrintWriter(new FileWriter("." + this.sp
				+ "src" + this.sp + "Datoteke" + this.sp + "Karte", false));
		for (Karta karta : this.listaKarata) {
			DateFormat df = new SimpleDateFormat("MM;dd;yyyy;HH;mm");
			String upisiVremeIzdavanja = df.format(karta
					.getVremeIzdavanjaKarte());
			String sediste = karta.getSedisteKarte().getRedSedista() + "x"
					+ karta.getSedisteKarte().getBrojSedista();
			int identifikatorIzvodjenja = karta.getIzvodjenjeKarte()
					.getIdentifikatorIzvodjenja();

			String strUpisiKartu = karta.getSerijskiBrojKarte() + "|"
					+ karta.getPopustKarte() + "|" + upisiVremeIzdavanja + "|"
					+ identifikatorIzvodjenja + "|" + sediste;
			upisiKartu.println(strUpisiKartu);
		}
		upisiKartu.close();
	}

	public Karta pretragaPoSerijskomBroju(String serijskiBroj) {
		Karta pronadjenaKarta = null;
		for (Karta karta : this.getListaKarata()) {
			if (karta.getSerijskiBrojKarte().equals(serijskiBroj)) {
				pronadjenaKarta = karta;
			}
		}
		return pronadjenaKarta;
	}

	public ArrayList<Karta> sortiranje(int opcijaSortiranja) {
		ArrayList<Karta> sortiraneKarte = this.getListaKarata();
		switch (opcijaSortiranja) {
		case 1:
			// Sortiranje po vremenu izdavanja.
			Collections.sort(sortiraneKarte,
					new KartaVremeIzdavanjaKomparator());
			break;
		case 2:
			// Sortiranje po popustu.
			Collections.sort(sortiraneKarte, new KartaPopustKomparator());
			break;
		case 3:
			// Sortiranje po nazivu predstave, vremenu pocetka izvodjenja, i
			// vremenu izdavanja karte
			Collections.sort(sortiraneKarte,
					new KartaNazivPocetakIzdavanjeKomparator());
			break;
		}
		return sortiraneKarte;
	}

	public ArrayList<Sediste> slobodnaSedista(Izvodjenje izvodjenje) {
		ArrayList<Sediste> slobodnaSedista = izvodjenje.getScenaIzvodjenja()
				.getSedistaScene();
		for (Karta karta : izvodjenje.getProdateKarteIzvodjenja()) {
			if (slobodnaSedista.contains(karta.getSedisteKarte())) {
				System.out.println("Zauzeto sediste!");
				slobodnaSedista.remove(karta.getSedisteKarte());
			}
		}
		return slobodnaSedista;
	}

	public void prodajaKarte(Izvodjenje izvodjenje, Sediste sediste, int popust) {
		String serijskiBroj = Integer
				.toString(this.getListaKarata().size() + 1);
		double cena = izvodjenje.getCenaKarteIzvodjenja();
		if (popust > 0) {
			cena = cena - (cena / popust);
		}
		Calendar calendar = new GregorianCalendar();
		Date vremeIzdavanja = calendar.getTime();
		Karta podataKarta = new Karta(serijskiBroj, cena, popust,
				vremeIzdavanja, izvodjenje, sediste);
		this.getListaKarata().add(podataKarta);
		Korisnik ulogovaniKorisnik = MainPetlja.getUlogovaniKorisnik();
		if (ulogovaniKorisnik.getClass() == Biletar.class) {
			Biletar ulogovaniBiletar = (Biletar) ulogovaniKorisnik;
			ulogovaniBiletar.getProdateKarte().add(podataKarta);
		}
	}

	public ArrayList<Karta> getListaKarata() {
		return listaKarata;
	}

	public void setListaKarata(ArrayList<Karta> listaKarata) {
		this.listaKarata = listaKarata;
	}

	public IzvodjenjeServis getIzvodjenjeServis() {
		return izvodjenjeServis;
	}

}
