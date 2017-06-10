package Komparatori;

import java.util.Comparator;

import Entiteti.Izvodjenje;

public class IzvodjenjeGodinaPremijerePredstaveKomparator implements Comparator<Izvodjenje> {

	@Override
	public int compare(Izvodjenje izvodjenje1, Izvodjenje izvodjenje2) {
		// Sortiranje po godini premijere predstave.
		int god1 = izvodjenje1.getPredstavaIzvodjenja().getGodinaPremijerePredstave();
		int god2 = izvodjenje2.getPredstavaIzvodjenja().getGodinaPremijerePredstave();
		
		return god1 - god2;
	}

}
