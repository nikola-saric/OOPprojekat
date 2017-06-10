package Konzola;

import java.io.IOException;

import Servisi.IzvodjenjeServis;
import Servisi.KartaServis;
import Servisi.KorisnikServis;
import Servisi.PredstavaServis;
import Servisi.ScenaServis;

public class Gasenje {

	public static void gasenjePrograma(KorisnikServis korisnikServis, PredstavaServis predstavaServis, IzvodjenjeServis izvodjenjeServis,
			ScenaServis scenaServis, KartaServis kartaServis) throws IOException {
		predstavaServis.upisiPredstave();
		scenaServis.upisiScene();
		izvodjenjeServis.upisiIzvodjenja();
		kartaServis.upisiKarte();
		korisnikServis.upisiKorisnike();
		System.exit(0);
	}
}
