package Servisi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

import Entiteti.Biletar;
import Entiteti.Karta;
import Entiteti.Korisnik;
import Entiteti.Menadzer;
import Entiteti.Predstava;
import Komparatori.KorisnikImeIPrezimeKomparator;
import Komparatori.KorisnikUlogaKomparator;
import Komparatori.KorisnikUsernameKomparator;

public class KorisnikServis {
	private ArrayList<Korisnik> listaKorisnika = new ArrayList<>();
	private PredstavaServis predstavaServis;
	private KartaServis kartaServis;
	private String sp = System.getProperty("file.separator");

	public KorisnikServis(PredstavaServis predstavaServis, KartaServis kartaServis) {
		this.predstavaServis = predstavaServis;
		this.kartaServis = kartaServis;
	}

	public void ucitajKorisnike(String kategorija) throws IOException {
		String trenutnaLinija;
		BufferedReader br = new BufferedReader(
				new FileReader("." + this.sp + "src" + this.sp + "Datoteke" + this.sp + kategorija));
		if (kategorija == "Menadzeri") {
			while ((trenutnaLinija = br.readLine()) != null) {
				String[] sL = trenutnaLinija.split("\\|");
				boolean aktivnostUcitanogMenadzera = false;
				if (sL[4].equals("true")) {
					aktivnostUcitanogMenadzera = true;
				} else if (sL[4].equals("false")) {
					aktivnostUcitanogMenadzera = false;
				}
				ArrayList<Predstava> unetePredstave = new ArrayList<>();
				String[] strPredstave = sL[5].split(";");
				for (int i = 0; i < strPredstave.length; i++) {
					for (Predstava predstava : this.predstavaServis.getListaPredstava()) {
						if (predstava.getNazivPredstave().equalsIgnoreCase(strPredstave[i])) {
							unetePredstave.add(predstava);
						}
					}
				}
				Menadzer ucitaniMenadzer = new Menadzer(sL[0], sL[1], sL[2], sL[3], aktivnostUcitanogMenadzera,
						unetePredstave);
				this.listaKorisnika.add(ucitaniMenadzer);
			}

		} else {
			while ((trenutnaLinija = br.readLine()) != null) {
				String[] sL = trenutnaLinija.split("\\|");
				boolean aktivnostUcitanogBiletara = false;
				if (sL[4].equals("true")) {
					aktivnostUcitanogBiletara = true;
				} else if (sL[4].equals("false")) {
					aktivnostUcitanogBiletara = false;
				}
				ArrayList<Karta> uneteKarte = new ArrayList<>();
				String[] strKarte = sL[5].split(";");
				for (int i = 0; i < strKarte.length; i++) {
					for (Karta karta : this.kartaServis.getListaKarata()) {
						if (karta.getSerijskiBrojKarte().equals(strKarte[i])) {
							uneteKarte.add(karta);
						}
					}
				}
				Biletar ucitaniBiletar = new Biletar(sL[0], sL[1], sL[2], sL[3], aktivnostUcitanogBiletara, uneteKarte);
				this.listaKorisnika.add(ucitaniBiletar);

			}
		}
		br.close();
	}

	public void upisiKorisnike() throws IOException {
		PrintWriter upisiMenadzere = new PrintWriter(
				new FileWriter("." + this.sp + "src" + this.sp + "Datoteke" + this.sp + "Menadzeri", false));
		PrintWriter upisiBiletare = new PrintWriter(
				new FileWriter("." + this.sp + "src" + this.sp + "Datoteke" + this.sp + "Biletari", false));
		for (Korisnik korisnik : this.listaKorisnika) {
			if (korisnik.getClass() == Menadzer.class) {

				Menadzer menadzer = (Menadzer) korisnik;
				String unetePredstave = "";
				for (Predstava predstava : menadzer.getDodatePredstave()) {
					unetePredstave += predstava.getNazivPredstave() + ";";
				}
				String strMenadzer = menadzer.getUsernameKorisnika() + "|" + menadzer.getPasswordKorisnika() + "|"
						+ menadzer.getImeKorisnika() + "|" + menadzer.getPrezimeKorisnika() + "|"
						+ menadzer.isAktivnostKorisnika() + "|" + unetePredstave;
				upisiMenadzere.println(strMenadzer);
			} else if (korisnik.getClass() == Biletar.class) {
				Biletar biletar = (Biletar) korisnik;
				String uneteKarte = "";
				for (Karta karta : biletar.getProdateKarte()) {
					uneteKarte += karta.getSerijskiBrojKarte() + ";";
				}
				String strBiletar = biletar.getUsernameKorisnika() + "|" + biletar.getPasswordKorisnika() + "|"
						+ biletar.getImeKorisnika() + "|" + biletar.getPrezimeKorisnika() + "|"
						+ biletar.isAktivnostKorisnika() + "|" + uneteKarte;
				upisiBiletare.println(strBiletar);
			}
		}
		upisiMenadzere.close();
		upisiBiletare.close();
	}

	public void unosNovogKorisnika(String username, String password, String ime, String prezime, String uloga)
			throws IOException {
		if (uloga.equalsIgnoreCase("menadzer")) {
			boolean aktivnostNovogMenadzera = true;
			Menadzer noviMenadzer = new Menadzer(username, password, ime, prezime, aktivnostNovogMenadzera,
					new ArrayList<Predstava>());
			this.listaKorisnika.add(noviMenadzer);
		} else if (uloga.equalsIgnoreCase("biletar")) {
			boolean aktivnostNovogBiletara = true;
			Biletar noviBiletar = new Biletar(username, password, ime, prezime, aktivnostNovogBiletara,
					new ArrayList<Karta>());
			this.listaKorisnika.add(noviBiletar);
		}
		this.upisiKorisnike();
	}

	public boolean brisanjeKorisnika(String username) throws IOException {
		boolean uspesnaIzmena = false;
		for (Korisnik korisnik : this.listaKorisnika) {
			if (korisnik.getUsernameKorisnika().equals(username)) {
				korisnik.setAktivnostKorisnika(false);
				uspesnaIzmena = true;
			}
		}
		this.upisiKorisnike();
		return uspesnaIzmena;
	}

	public boolean izmenaPodatakaKorisnika(String username, String noviPassword, String novoIme, String novoPrezime)
			throws IOException {
		boolean uspesnaIzmena = false;

		for (Korisnik korisnik : this.listaKorisnika) {
			if (korisnik.getUsernameKorisnika().equals(username)) {
				korisnik.setPasswordKorisnika(noviPassword);
				korisnik.setImeKorisnika(novoIme);
				korisnik.setPrezimeKorisnika(novoPrezime);
				uspesnaIzmena = true;
			}

		}
		this.upisiKorisnike();
		return uspesnaIzmena;
	}

	public ArrayList<Korisnik> pretragaKorisnika(String unetaRec, int opcijaPretrage) {
		ArrayList<Korisnik> pronadjeniKorisnici = new ArrayList<Korisnik>();
		if (unetaRec == "") {
			for (Korisnik korisnik : this.listaKorisnika) {
				if (korisnik.isAktivnostKorisnika() == true) {
					pronadjeniKorisnici.add(korisnik);
				}
			}
			return pronadjeniKorisnici;
		}
		switch (opcijaPretrage) {
		case 1:
			for (Korisnik korisnik : this.listaKorisnika) {
				if ((korisnik.getUsernameKorisnika().toLowerCase().contains(unetaRec.toLowerCase()))
						&& (korisnik.isAktivnostKorisnika() == true)) {
					pronadjeniKorisnici.add(korisnik);
				}
			}
			break;
		case 2:
			for (Korisnik korisnik : this.listaKorisnika) {
				String imeIPrezime = korisnik.getImeKorisnika() + " " + korisnik.getPrezimeKorisnika();
				if ((imeIPrezime.toLowerCase().contains(unetaRec.toLowerCase()))
						&& (korisnik.isAktivnostKorisnika() == true)) {
					pronadjeniKorisnici.add(korisnik);
				}
			}
			break;
		case 3:
			for (Korisnik korisnik : this.listaKorisnika) {
				if ((korisnik.getClass() == Biletar.class) && (korisnik.isAktivnostKorisnika() == true)) {
					Biletar biletar = (Biletar) korisnik;
					for (Karta karta : biletar.getProdateKarte()) {
						if (karta.getIzvodjenjeKarte().getPredstavaIzvodjenja().getNazivPredstave().toLowerCase()
								.contains(unetaRec.toLowerCase())) {
							pronadjeniKorisnici.add(korisnik);
						}
					}
				} else if ((korisnik.getClass() == Menadzer.class) && (korisnik.isAktivnostKorisnika() == true)) {
					Menadzer menadzer = (Menadzer) korisnik;
					for (Predstava predstava : menadzer.getDodatePredstave()) {
						if (predstava.getNazivPredstave().toLowerCase().contains(unetaRec.toLowerCase())) {
							pronadjeniKorisnici.add(korisnik);
						}
					}
				}
			}
			break;
		}
		return pronadjeniKorisnici;
	}

	public ArrayList<Korisnik> sortiranjeKorisnika(ArrayList<Korisnik> pronadjeniKorisnici, int opcijaSortiranja) {
		switch (opcijaSortiranja) {
		case 1:
			Collections.sort(pronadjeniKorisnici, new KorisnikUsernameKomparator());
			break;
		case 2:
			Collections.sort(pronadjeniKorisnici, new KorisnikImeIPrezimeKomparator());
			break;
		case 3:
			Collections.sort(pronadjeniKorisnici, new KorisnikUlogaKomparator());
			break;
		}
		return pronadjeniKorisnici;
	}

	public ArrayList<Korisnik> getListaKorisnika() {
		return listaKorisnika;
	}

	public void setListaKorisnika(ArrayList<Korisnik> listaKorisnika) {
		this.listaKorisnika = listaKorisnika;
	}

}