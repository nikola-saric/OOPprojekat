package Entiteti;

public abstract class Korisnik {
	private String usernameKorisnika;
	private String passwordKorisnika;
	private String imeKorisnika;
	private String prezimeKorisnika;
	private boolean aktivnostKorisnika;

	public Korisnik(String usernameKorisnika, String passwordKorisnika, String imeKorisnika, String prezimeKorisnika,
			boolean aktivnostKorisnika) {
		this.usernameKorisnika = usernameKorisnika;
		this.passwordKorisnika = passwordKorisnika;
		this.imeKorisnika = imeKorisnika;
		this.prezimeKorisnika = prezimeKorisnika;
		this.aktivnostKorisnika = aktivnostKorisnika;
	}

	public Korisnik() {
		super();
	}

	@Override
	public String toString() {
		String printKorisnika = String.format("%1$-12s %2$-15s %3$-19s %4$-1s", "| " + usernameKorisnika,
				"| " + imeKorisnika, "| " + prezimeKorisnika, "|");
		return printKorisnika;
	}

	public String getUsernameKorisnika() {
		return usernameKorisnika;
	}

	public void setUsernameKorisnika(String usernameKorisnika) {
		this.usernameKorisnika = usernameKorisnika;
	}

	public String getPasswordKorisnika() {
		return passwordKorisnika;
	}

	public void setPasswordKorisnika(String passwordKorisnika) {
		this.passwordKorisnika = passwordKorisnika;
	}

	public String getImeKorisnika() {
		return imeKorisnika;
	}

	public void setImeKorisnika(String imeKorisnika) {
		this.imeKorisnika = imeKorisnika;
	}

	public String getPrezimeKorisnika() {
		return prezimeKorisnika;
	}

	public void setPrezimeKorisnika(String prezimeKorisnika) {
		this.prezimeKorisnika = prezimeKorisnika;
	}

	public boolean isAktivnostKorisnika() {
		return aktivnostKorisnika;
	}

	public void setAktivnostKorisnika(boolean aktivnostKorisnika) {
		this.aktivnostKorisnika = aktivnostKorisnika;
	}

}
