package Entiteti;

public class Sediste {
	private int redSedista;
	private int brojSedista;

	public Sediste(int red, int broj) {
		this.redSedista = red;
		this.brojSedista = broj;
	}

	public Sediste() {
		super();
	}

	@Override
	public String toString() {
		return "Sediste: red: " + redSedista + ", broj: " + brojSedista;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + brojSedista;
		result = prime * result + redSedista;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj.getClass() == Sediste.class) {
			Sediste sediste = (Sediste) obj;
			if (this.redSedista == sediste.getRedSedista() && this.brojSedista == sediste.getBrojSedista()) {
				return true;
			}else if(this == sediste) {
				return true;
			}
		}
		return false;
	}

	public int getRedSedista() {
		return redSedista;
	}

	public void setRedSedista(int red) {
		this.redSedista = red;
	}

	public int getBrojSedista() {
		return brojSedista;
	}

	public void setBrojSedista(int broj) {
		this.brojSedista = broj;
	}

}
