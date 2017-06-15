package Entiteti;

import java.util.ArrayList;


public class Menadzer extends Korisnik {
	private ArrayList<Predstava> dodatePredstave;

	public Menadzer(String usernameKorisnika, String passwordKorisnika, String imeKorisnika, String prezimeKorisnika,
			boolean aktivnostKorisnika, ArrayList<Predstava> dodatePredstave) {
		super(usernameKorisnika, passwordKorisnika, imeKorisnika, prezimeKorisnika, aktivnostKorisnika);
		this.dodatePredstave = dodatePredstave;
	}

	public Menadzer() {
		super();
	}

	@Override
	public String toString() {
		return "| Menadzer " + super.toString();
	}

	public ArrayList<Predstava> getDodatePredstave() {
		return dodatePredstave;
	}

	public void setDodatePredstave(ArrayList<Predstava> dodatePredstave) {
		this.dodatePredstave = dodatePredstave;
	}

}
