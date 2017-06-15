package Konzola;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Entiteti.Scena;
import Entiteti.TipPredstave;
import Servisi.ScenaServis;

public class RadSaScenama {
	public static void radSaScenama(Scanner sc, ScenaServis scenaServis) throws IOException {
		System.out.println("Unesite zeljenu opciju: ");
		System.out.println("1. Unos nove scene.");
		System.out.println("2. Prikaz svih scena.");
		System.out.println("3. Brisanje scene.");
		int vasaOpcija = Utility.ocitajBroj(sc);

		switch (vasaOpcija) {
		case 1:
			// Unos nove scene.
			boolean dostupanNaziv = false;
			String nazivNoveScene = "";
			while (dostupanNaziv == false) {
				System.out.println("Unesite naziv nove scene: ");
				nazivNoveScene = Utility.ocitajTekst(sc);
				boolean postojeciNaziv = false;
				for (Scena scena : scenaServis.getListaScena()) {
					if (scena.getNazivScene().equalsIgnoreCase(nazivNoveScene)) {
						postojeciNaziv = true;
					}
				}
				if (postojeciNaziv == false) {
					dostupanNaziv = true;
				}
			}
			System.out.println("Unesite podrzane tipove tonskog zapisa nove scene: ");
			String tipoviToNZapNoveScene = Utility.ocitajTekst(sc);
			ArrayList<TipPredstave> podrzaniTipoviNoveScene = new ArrayList<>();
			System.out.println("Da li nova scena podrzava dramu?: ");
			System.out.println("1. Da; 2. Ne");
			int drama = Utility.ocitajBroj(sc);
			if (drama == 1) {
				podrzaniTipoviNoveScene.add(TipPredstave.valueOfOrdinal(1));
			}
			System.out.println("Da li nova scena podrzava operu?: ");
			System.out.println("1. Da; 2. Ne");
			int opera = Utility.ocitajBroj(sc);
			if (opera == 1) {
				podrzaniTipoviNoveScene.add(TipPredstave.valueOfOrdinal(2));
			}
			System.out.println("Da li nova scena podrzava balet?: ");
			System.out.println("1. Da; 2. Ne");
			int balet = Utility.ocitajBroj(sc);
			if (balet == 1) {
				podrzaniTipoviNoveScene.add(TipPredstave.valueOfOrdinal(3));
			}
			System.out.println("Unesite broj redova sedista nove scene: ");
			int brojRedova = Utility.ocitajBroj(sc);
			System.out.println("Unesite broj sedista po redu: ");
			int brojSedista = Utility.ocitajBroj(sc);
			scenaServis.unosNoveScene(nazivNoveScene, tipoviToNZapNoveScene, podrzaniTipoviNoveScene, brojRedova,
					brojSedista);
			break;

		case 2:
			// Prikaz scena sortiranih po nazivu.
			System.out.println("Scene sortirane po nazivu: ");
			System.out.println("=================================================================");
			System.out.println("|  Naziv scene  | Tonski zapis |   Tipovi predstave  | Sedista  |");
			System.out.println("|---------------------------------------------------------------|");
			ArrayList<Scena> listaSortiranihScena = scenaServis.prikazScena();
			for (Scena scena : listaSortiranihScena) {
				System.out.println(scena);
			}
			System.out.println("=================================================================");
			
			break;
		case 3:
			// Brisanje scene.
			System.out.println("Unesite naziv scene koju zelite da obrisete: ");
			String nazivScene = Utility.ocitajTekst(sc);
			boolean obrisanaScena = scenaServis.brisanjeScene(nazivScene);
			if (obrisanaScena == true) {
				System.out.println("Scena je uspesno obrisana.");
			} else {
				System.out.println("Ne postoji scena sa unetim imenom!");
			}
			break;
		default:
			System.out.println("Uneli ste nepostojecu opciju!");
			break;
		}
	}
}