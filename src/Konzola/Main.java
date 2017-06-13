package Konzola;

import java.io.IOException;
import java.util.Scanner;

import Servisi.IzvodjenjeServis;
import Servisi.KartaServis;
import Servisi.KorisnikServis;
import Servisi.PredstavaServis;
import Servisi.ScenaServis;

public class Main {
	public static void ucitajPodatke(KorisnikServis korisnikServis, PredstavaServis predstavaServis,
			ScenaServis scenaServis, IzvodjenjeServis izvodjenjeServis, KartaServis kartaServis) throws IOException {
		predstavaServis.ucitajPredstave();
		scenaServis.ucitajScene();
		izvodjenjeServis.ucitajIzvodjenja();
		kartaServis.ucitajKarte();
		korisnikServis.ucitajKorisnike("Menadzeri");
		korisnikServis.ucitajKorisnike("Biletari");
		izvodjenjeServis.ucitajKarteIzvodjenja(kartaServis);
	}

	public static void main(String[] args) throws IOException {
		PredstavaServis predstavaServis = new PredstavaServis();
		ScenaServis scenaServis = new ScenaServis();
		IzvodjenjeServis izvodjenjeServis = new IzvodjenjeServis(predstavaServis, scenaServis);
		KartaServis kartaServis = new KartaServis(izvodjenjeServis);
		KorisnikServis korisnikServis = new KorisnikServis(predstavaServis, kartaServis);
		Scanner sc = new Scanner(System.in);
		ucitajPodatke(korisnikServis, predstavaServis, scenaServis, izvodjenjeServis, kartaServis);
		MainPetlja.mainPetlja(korisnikServis, predstavaServis, izvodjenjeServis, scenaServis, kartaServis, sc);

		//formatiranje stringova!
		//https://www.dotnetperls.com/format-java
		//1. Srediti formatiranje svih ispisa!
		//3. Odraditi unit testove.
		
	}

}