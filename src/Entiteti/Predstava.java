package Entiteti;

public class Predstava {
	private String nazivPredstave;
	private TipPredstave tipPredstave;
	private String reziserPredstave;
	private String glumciPredstave;
	private int trajanjePredstave;
	private String produkcijaPredstave;
	private int godinaPremijerePredstave;
	private String opisPredstave;
	private boolean aktivnostPredstave;

	public Predstava(String nazivPredstave, TipPredstave tipPredstave, String reziserPredstave, String glumciPredstave,
			int trajanjePredstave, String produkcijaPredstave, int godinaPremijerePredstave, String opisPredstave,
			boolean aktivnostPredstave) {
		this.nazivPredstave = nazivPredstave;
		this.tipPredstave = tipPredstave;
		this.reziserPredstave = reziserPredstave;
		this.glumciPredstave = glumciPredstave;
		this.trajanjePredstave = trajanjePredstave;
		this.produkcijaPredstave = produkcijaPredstave;
		this.godinaPremijerePredstave = godinaPremijerePredstave;
		this.opisPredstave = opisPredstave;
		this.aktivnostPredstave = aktivnostPredstave;
	}

	public Predstava() {
		super();
	}

	@Override
	public String toString() {
		String glumci = this.glumciPredstave;
		if (glumci.length() > 38) {
			glumci = glumci.substring(0, 38);
		}
		String naziv = this.nazivPredstave;
		if (naziv.length() > 21) {
			naziv = naziv.substring(0, 21);
		}
		String reziser = this.reziserPredstave;
		if (reziser.length() > 18) {
			reziser = reziser.substring(0, 18);
		}
		String produkcija = this.produkcijaPredstave;
		if (produkcija.length() > 18) {
			produkcija = produkcija.substring(0, 18);
		}
		String opis = this.opisPredstave;
		if (opis.length() > 18) {
			opis = opis.substring(0, 18);
		}
		String printPredstava = String.format("%1$-23s %2$-8s %3$-20s %4$-40s %5$-10s %6$-20s %7$-8s %8$-20s %9$-1s",
				"| " + nazivPredstave, "| " + tipPredstave, "| " + reziserPredstave, "| " + glumci,
				"|   " + trajanjePredstave, "| " + produkcijaPredstave, "|  " + godinaPremijerePredstave,
				"| " + opisPredstave, "|");

		return printPredstava;
	}

	public String getNazivPredstave() {
		return nazivPredstave;
	}

	public void setNazivPredstave(String nazivPredstave) {
		this.nazivPredstave = nazivPredstave;
	}

	public TipPredstave getTipPredstave() {
		return tipPredstave;
	}

	public void setTipPredstave(TipPredstave tipPredstave) {
		this.tipPredstave = tipPredstave;
	}

	public String getReziserPredstave() {
		return reziserPredstave;
	}

	public void setReziserPredstave(String reziserPredstave) {
		this.reziserPredstave = reziserPredstave;
	}

	public String getGlumciPredstave() {
		return glumciPredstave;
	}

	public void setGlumciPredstave(String glumciPrestave) {
		this.glumciPredstave = glumciPrestave;
	}

	public int getTrajanjePredstave() {
		return trajanjePredstave;
	}

	public void setTrajanjePredstave(int trajanjePredstave) {
		this.trajanjePredstave = trajanjePredstave;
	}

	public String getProdukcijaPredstave() {
		return produkcijaPredstave;
	}

	public void setProdukcijaPredstave(String produkcijaPredstave) {
		this.produkcijaPredstave = produkcijaPredstave;
	}

	public int getGodinaPremijerePredstave() {
		return godinaPremijerePredstave;
	}

	public void setGodinaPremijerePredstave(int godinaPremijerePredstave) {
		this.godinaPremijerePredstave = godinaPremijerePredstave;
	}

	public String getOpisPredstave() {
		return opisPredstave;
	}

	public void setOpisPredstave(String opisPredstave) {
		this.opisPredstave = opisPredstave;
	}

	public boolean isAktivnostPredstave() {
		return aktivnostPredstave;
	}

	public void setAktivnostPredstave(boolean aktivnostPredstave) {
		this.aktivnostPredstave = aktivnostPredstave;
	}

}
