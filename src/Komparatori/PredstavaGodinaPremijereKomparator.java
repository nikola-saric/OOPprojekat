package Komparatori;

import java.util.Comparator;

import Entiteti.Predstava;

public class PredstavaGodinaPremijereKomparator implements Comparator<Predstava> {

	@Override
	public int compare(Predstava predstava1, Predstava prestava2) {
		// Sortiranje po godini premijere predstave.

		return predstava1.getGodinaPremijerePredstave() - prestava2.getGodinaPremijerePredstave();
	}

}
