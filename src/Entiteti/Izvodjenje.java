package Entiteti;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Izvodjenje {
	private int identifikatorIzvodjenja;
	private Date pocetakIzvodjenja;
	private double cenaKarteIzvodjenja;
	private Predstava predstavaIzvodjenja;
	private Scena scenaIzvodjenja;
	private List<Karta> prodateKarteIzvodjenja;
	private boolean aktivnostIzvodjenja;

	public Izvodjenje(int identifikatorIzvodjenja, Date pocetakIzvodjenja, double cenaKarteIzvodjenja,
			Predstava predstavaIzvodjenja, Scena scenaIzvodjenja, List<Karta> prodateKarteIzvodjenja,
			boolean aktivnostIzvodjenja) {
		this.identifikatorIzvodjenja = identifikatorIzvodjenja;
		this.pocetakIzvodjenja = pocetakIzvodjenja;
		this.cenaKarteIzvodjenja = cenaKarteIzvodjenja;
		this.predstavaIzvodjenja = predstavaIzvodjenja;
		this.scenaIzvodjenja = scenaIzvodjenja;
		this.prodateKarteIzvodjenja = prodateKarteIzvodjenja;
		this.aktivnostIzvodjenja = aktivnostIzvodjenja;
	}

	public Izvodjenje() {
		super();
	}

	@Override
	public String toString() {
		DateFormat df = new SimpleDateFormat("dd;MM;yyyy;HH:mm");
		String reportDate = df.format(pocetakIzvodjenja);
		String printIzvodjenja = String.format("%1$-5s %2$-23s %3$-7s %4$-16s %5$-13s %6$-1s %7$-6.2f %8$-1s %9$-8s %10$-1s",
				"|  " + identifikatorIzvodjenja, "| " + predstavaIzvodjenja.getNazivPredstave(),
				"| " + predstavaIzvodjenja.getTipPredstave(), "|" + reportDate,
				"| " + scenaIzvodjenja.getNazivScene(),"|", cenaKarteIzvodjenja,"|", "  " + prodateKarteIzvodjenja.size(), "|");
		return printIzvodjenja;
	}

	public int getIdentifikatorIzvodjenja() {
		return identifikatorIzvodjenja;
	}

	public void setIdentifikatorIzvodjenja(int identifikatorIzvodjenja) {
		this.identifikatorIzvodjenja = identifikatorIzvodjenja;
	}

	public Date getPocetakIzvodjenja() {
		return pocetakIzvodjenja;
	}

	public void setPocetakIzvodjenja(Date pocetakIzvodjenja) {
		this.pocetakIzvodjenja = pocetakIzvodjenja;
	}

	public double getCenaKarteIzvodjenja() {
		return cenaKarteIzvodjenja;
	}

	public void setCenaKarteIzvodjenja(double cenaKarteIzvodjenja) {
		this.cenaKarteIzvodjenja = cenaKarteIzvodjenja;
	}

	public Predstava getPredstavaIzvodjenja() {
		return predstavaIzvodjenja;
	}

	public void setPredstavaIzvodjenja(Predstava predstavaIzvodjenja) {
		this.predstavaIzvodjenja = predstavaIzvodjenja;
	}

	public Scena getScenaIzvodjenja() {
		return scenaIzvodjenja;
	}

	public void setScenaIzvodjenja(Scena scenaIzvodjenja) {
		this.scenaIzvodjenja = scenaIzvodjenja;
	}

	public List<Karta> getProdateKarteIzvodjenja() {
		return prodateKarteIzvodjenja;
	}

	public void setProdateKarteIzvodjenja(List<Karta> prodateKarteIzvodjenja) {
		this.prodateKarteIzvodjenja = prodateKarteIzvodjenja;
	}

	public boolean isAktivnostIzvodjenja() {
		return aktivnostIzvodjenja;
	}

	public void setAktivnostIzvodjenja(boolean aktivnostIzvodjenja) {
		this.aktivnostIzvodjenja = aktivnostIzvodjenja;
	}

}
