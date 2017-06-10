package Entiteti;

import java.util.ArrayList;
import java.util.List;

public class Scena {
	private String nazivScene;
	private String tipoviTonskogZapisaScene;
	private List<TipPredstave> podrzaniTipoviScene;
	private ArrayList<Sediste> sedistaScene;
	private boolean aktivnostScene;

	public Scena(String nazivScene, String tipoviTonskogZapisaScene, List<TipPredstave> podrzaniTipoviScene,
			ArrayList<Sediste> sedistaScene, boolean aktivnostScene) {
		this.nazivScene = nazivScene;
		this.tipoviTonskogZapisaScene = tipoviTonskogZapisaScene;
		this.podrzaniTipoviScene = podrzaniTipoviScene;
		this.sedistaScene = sedistaScene;
		this.aktivnostScene = aktivnostScene;
	}

	public Scena() {
		super();
	}

	@Override
	public String toString() {
		return "Scena: naziv scene: " + nazivScene + ", tipovi tonskog zapisac scene: " + tipoviTonskogZapisaScene
				+ ", podrzani tipovi scene: " + podrzaniTipoviScene;
	}

	public String getNazivScene() {
		return nazivScene;
	}

	public void setNazivScene(String nazivScene) {
		this.nazivScene = nazivScene;
	}

	public String getTipoviTonskogZapisaScene() {
		return tipoviTonskogZapisaScene;
	}

	public void setTipoviTonskogZapisaScene(String tipoviTonskogZapisaScene) {
		this.tipoviTonskogZapisaScene = tipoviTonskogZapisaScene;
	}

	public List<TipPredstave> getPodrzaniTipoviScene() {
		return podrzaniTipoviScene;
	}

	public void setPodrzaniTipoviScene(List<TipPredstave> podrzaniTipoviScene) {
		this.podrzaniTipoviScene = podrzaniTipoviScene;
	}

	public ArrayList<Sediste> getSedistaScene() {
		return sedistaScene;
	}

	public void setSedistaScene(ArrayList<Sediste> sedistaScene) {
		this.sedistaScene = sedistaScene;
	}

	public boolean isAktivnostScene() {
		return aktivnostScene;
	}

	public void setAktivnostScene(boolean aktivnostScene) {
		this.aktivnostScene = aktivnostScene;
	}

}
