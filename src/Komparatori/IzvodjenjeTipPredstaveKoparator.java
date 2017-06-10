package Komparatori;

import java.util.Comparator;

import Entiteti.Izvodjenje;
import Entiteti.TipPredstave;

public class IzvodjenjeTipPredstaveKoparator implements Comparator<Izvodjenje>{

	@Override
	public int compare(Izvodjenje izvodjenje1, Izvodjenje izvodjenje2) {
		// Sortiranje po tipu predstave.
		TipPredstave tip1 = izvodjenje1.getPredstavaIzvodjenja().getTipPredstave();
		TipPredstave tip2 = izvodjenje2.getPredstavaIzvodjenja().getTipPredstave();
		return tip1.compareTo(tip2);
	}

}
