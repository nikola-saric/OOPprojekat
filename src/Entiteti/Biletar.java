package Entiteti;

import java.util.List;

public class Biletar extends Korisnik {
	private List<Karta> prodateKarte;

	public Biletar(String usernameKorisnika, String passwordKorisnika, String imeKorisnika, String prezimeKorisnika,
			boolean aktivnostKorisnika, List<Karta> prodateKarte) {
		super(usernameKorisnika, passwordKorisnika, imeKorisnika, prezimeKorisnika, aktivnostKorisnika);
		this.prodateKarte = prodateKarte;
	}

	public Biletar() {
		super();
	}

	@Override
	public String toString() {
		return "Biletar: " + super.toString() + ", prodate karte: " + prodateKarte;
	}

	public List<Karta> getProdateKarte() {
		return prodateKarte;
	}

	public void setProdateKarte(List<Karta> prodateKarte) {
		this.prodateKarte = prodateKarte;
	}

}
