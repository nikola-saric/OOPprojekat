package Entiteti;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Karta {
	private String serijskiBrojKarte;
	private double cenaKarte;
	private int popustKarte;
	private Date vremeIzdavanjaKarte;
	private Izvodjenje izvodjenjeKarte; // Pripada sali izvodjenja!
	private Sediste sedisteKarte;

	public Karta(String serijskiBrojKarte, double cenaKarte, int popustKarte, Date vremeIzdavanjaKarte,
			Izvodjenje izvodjenjeKarte, Sediste sedisteKarte) {
		this.serijskiBrojKarte = serijskiBrojKarte;
		this.cenaKarte = cenaKarte;
		this.popustKarte = popustKarte;
		this.vremeIzdavanjaKarte = vremeIzdavanjaKarte;
		this.izvodjenjeKarte = izvodjenjeKarte;
		this.sedisteKarte = sedisteKarte;
	}

	public Karta() {
		super();
	}

	@Override
	public String toString() {
		String strPopust = "";
		if (popustKarte != 0) {
			strPopust = Integer.toString(popustKarte);
		}
		DateFormat df = new SimpleDateFormat("dd;MM;yyyy;HH:mm");
		String pocetakIzvodjenja = df.format(izvodjenjeKarte.getPocetakIzvodjenja());
		String vremeIzdavanja = df.format(vremeIzdavanjaKarte);

		String printKarta = String.format("%1$-6s %2$-23s %3$-16s %4$-13s %5$-1s %6$-6.2f %7$-6s %8$-13s %9$-1s",
				"| " + serijskiBrojKarte, "| " + izvodjenjeKarte.getPredstavaIzvodjenja().getNazivPredstave(),
				"|" + pocetakIzvodjenja, "| " + izvodjenjeKarte.getScenaIzvodjenja().getNazivScene(), "|", cenaKarte,
				"|  " + strPopust, "|" + vremeIzdavanja, "|");
		return printKarta;
	}

	public String getSerijskiBrojKarte() {
		return serijskiBrojKarte;
	}

	public void setSerijskiBrojKarte(String serijskiBrojKarte) {
		this.serijskiBrojKarte = serijskiBrojKarte;
	}

	public double getCenaKarte() {
		return cenaKarte;
	}

	public void setCenaKarte(double cenaKarte) {
		this.cenaKarte = cenaKarte;
	}

	public int getPopustKarte() {
		return popustKarte;
	}

	public void setPopustKarte(int popustKarte) {
		this.popustKarte = popustKarte;
	}

	public Date getVremeIzdavanjaKarte() {
		return vremeIzdavanjaKarte;
	}

	public void setVremeIzdavanjaKarte(Date vremeIzdavanjaKarte) {
		this.vremeIzdavanjaKarte = vremeIzdavanjaKarte;
	}

	public Izvodjenje getIzvodjenjeKarte() {
		return izvodjenjeKarte;
	}

	public void setIzvodjenjeKarte(Izvodjenje izvodjenjeKarte) {
		this.izvodjenjeKarte = izvodjenjeKarte;
	}

	public Sediste getSedisteKarte() {
		return sedisteKarte;
	}

	public void setSedisteKarte(Sediste sedisteKarte) {
		this.sedisteKarte = sedisteKarte;
	}
}
