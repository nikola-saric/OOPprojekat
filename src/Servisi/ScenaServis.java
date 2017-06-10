package Servisi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

import Entiteti.Scena;
import Entiteti.Sediste;
import Entiteti.TipPredstave;
import Komparatori.ScenaNazivKomparator;

public class ScenaServis {
	private ArrayList<Scena> listaScena = new ArrayList<>();
	private String sp = System.getProperty("file.separator");

	public void ucitajScene() throws IOException {
		String trenutnaLinija;
		BufferedReader bf = new BufferedReader(
				new FileReader("." + this.sp + "src" + this.sp + "Datoteke" + this.sp + "Scene"));
		while ((trenutnaLinija = bf.readLine()) != null) {
			String[] sL = trenutnaLinija.split("\\|");
			boolean aktivnostScene = false;
			if (sL[4].equals("true")) {
				aktivnostScene = true;
			} else if (sL[4].equals("false")) {
				aktivnostScene = false;
			}
			String[] strTipoviPredstave = sL[2].split("x");
			ArrayList<TipPredstave> tipoviPredstave = new ArrayList<>();
			if (strTipoviPredstave.length == 1) {
				tipoviPredstave.add(TipPredstave.valueOfOrdinal(Integer.parseInt(strTipoviPredstave[0])));
			} else if (strTipoviPredstave.length == 2) {
				tipoviPredstave.add(TipPredstave.valueOfOrdinal(Integer.parseInt(strTipoviPredstave[0])));
				tipoviPredstave.add(TipPredstave.valueOfOrdinal(Integer.parseInt(strTipoviPredstave[1])));
			} else if (strTipoviPredstave.length == 3) {
				tipoviPredstave.add(TipPredstave.valueOfOrdinal(Integer.parseInt(strTipoviPredstave[0])));
				tipoviPredstave.add(TipPredstave.valueOfOrdinal(Integer.parseInt(strTipoviPredstave[1])));
				tipoviPredstave.add(TipPredstave.valueOfOrdinal(Integer.parseInt(strTipoviPredstave[2])));
			}
			ArrayList<Sediste> listaSedista = new ArrayList<>();
			String[] strSedista = sL[3].split("x");
			int brojRedova = Integer.parseInt(strSedista[0]);
			int brojSedista = Integer.parseInt(strSedista[1]);
			for (int i = 1; i <= brojRedova; i++) {
				for (int j = 1; j <= brojSedista; j++) {
					listaSedista.add(new Sediste(i, j));
				}
			}
			Scena novaScena = new Scena(sL[0], sL[1], tipoviPredstave, listaSedista, aktivnostScene);
			this.listaScena.add(novaScena);
		}
		bf.close();
	}

	public void upisiScene() throws IOException {
		PrintWriter upisiScene = new PrintWriter(
				new FileWriter("." + this.sp + "src" + this.sp + "Datoteke" + this.sp + "Scene", false));
		for (Scena scena : this.listaScena) {
			Sediste poslednjeSediste = scena.getSedistaScene().get(scena.getSedistaScene().size() - 1);
			String sedista = poslednjeSediste.getRedSedista() + "x" + poslednjeSediste.getBrojSedista();
			String tipoviScene = "";
			if (scena.getPodrzaniTipoviScene().size() == 1) {
				tipoviScene = Integer.toString(scena.getPodrzaniTipoviScene().get(0).ordinal() + 1);
			} else if (scena.getPodrzaniTipoviScene().size() == 2) {
				tipoviScene = Integer.toString(scena.getPodrzaniTipoviScene().get(0).ordinal() + 1) + "x"
						+ Integer.toString(scena.getPodrzaniTipoviScene().get(1).ordinal() + 1);
			} else if (scena.getPodrzaniTipoviScene().size() == 3) {
				tipoviScene = Integer.toString(scena.getPodrzaniTipoviScene().get(0).ordinal() + 1) + "x"
						+ Integer.toString(scena.getPodrzaniTipoviScene().get(1).ordinal() + 1) + "x"
						+ Integer.toString(scena.getPodrzaniTipoviScene().get(2).ordinal() + 1);
			}
			String strZaUpis = scena.getNazivScene() + "|" + scena.getTipoviTonskogZapisaScene() + "|" + tipoviScene
					+ "|" + sedista + "|" + scena.isAktivnostScene();
			upisiScene.println(strZaUpis);
		}
		upisiScene.close();
	}

	public void unosNoveScene(String naziv, String tonskiTipovi, ArrayList<TipPredstave> podrzaniTipoviPredstava,
			int brojRedova, int brojSedista) {
		ArrayList<Sediste> listaSedista = new ArrayList<>();
		for (int i = 1; i <= brojRedova; i++) {
			for (int j = 1; j <= brojSedista; j++) {
				listaSedista.add(new Sediste(i, j));
			}
		}
		boolean aktivnostNoveScene = true;
		Scena novaScena = new Scena(naziv, tonskiTipovi, podrzaniTipoviPredstava, listaSedista, aktivnostNoveScene);
		this.listaScena.add(novaScena);
	}

	public ArrayList<Scena> prikazScena() {
		// sortira scene po nazivu.
		ArrayList<Scena> aktivneScene = new ArrayList<Scena>();
		for (Scena scena : this.listaScena) {
			if (scena.isAktivnostScene() == true) {
				aktivneScene.add(scena);
			}
		}
		Collections.sort(aktivneScene, new ScenaNazivKomparator());
		return aktivneScene;
	}

	public boolean brisanjeScene(String nazivScene) {
		boolean obrisanaScena = false;
		for (Scena scena : this.listaScena) {
			if (scena.getNazivScene().equalsIgnoreCase(nazivScene)) {
				scena.setAktivnostScene(false);
				obrisanaScena = true;
			}
		}
		return obrisanaScena;
	}

	public ArrayList<Scena> getListaScena() {
		return listaScena;
	}

	public void setListaScena(ArrayList<Scena> listaScena) {
		this.listaScena = listaScena;
	}

}