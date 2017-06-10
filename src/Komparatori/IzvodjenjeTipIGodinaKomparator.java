package Komparatori;

import java.util.Comparator;

import Entiteti.Izvodjenje;
import Entiteti.TipPredstave;

public class IzvodjenjeTipIGodinaKomparator implements Comparator<Izvodjenje> {

	@Override
	public int compare(Izvodjenje izvodjenje1, Izvodjenje izvodjenje2) {
		// Sortiranje po tipu i godini premijere predstave.
		TipPredstave tip1 = izvodjenje1.getPredstavaIzvodjenja()
				.getTipPredstave();
		TipPredstave tip2 = izvodjenje2.getPredstavaIzvodjenja()
				.getTipPredstave();
		int godinaPremijere1 = izvodjenje1.getPredstavaIzvodjenja()
				.getGodinaPremijerePredstave();
		int godinaPremijere2 = izvodjenje1.getPredstavaIzvodjenja()
				.getGodinaPremijerePredstave();

		if (tip1.equals(tip2)) {
			return godinaPremijere1 - godinaPremijere2;
		}

		return tip1.compareTo(tip2);
	}
}
