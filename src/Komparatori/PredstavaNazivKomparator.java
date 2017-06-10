package Komparatori;

import java.util.Comparator;

import Entiteti.Predstava;

public class PredstavaNazivKomparator implements Comparator<Predstava> {

	@Override
	public int compare(Predstava predstava1, Predstava predstava2) {
		// Sortiranje po nazivu predstave.
		String naziv1 = predstava1.getNazivPredstave().toUpperCase();
		String naziv2 = predstava2.getNazivPredstave().toUpperCase();

		return naziv1.compareTo(naziv2);
	}

}
