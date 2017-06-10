package Entiteti;

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
		return "Izvodjenje: identifikator:" + identifikatorIzvodjenja + ", naziv predstave: "
				+ predstavaIzvodjenja.getNazivPredstave() + ", tip predstave: " + predstavaIzvodjenja.getTipPredstave()
				+ ", pocetak: " + pocetakIzvodjenja + ", scena: " + scenaIzvodjenja.getNazivScene() + ", cena karte: "
				+ cenaKarteIzvodjenja + ", broj prodatih karata: " + prodateKarteIzvodjenja.size();
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
