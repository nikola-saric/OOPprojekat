package Entiteti;


public enum TipPredstave {
	DRAMA(1), OPERA(2), BALET(3);

	int broj;

	private TipPredstave(int broj) {
		this.broj = broj;
	}

	public static TipPredstave valueOfOrdinal(int o) {
		switch (o) {
		case 1:
			return TipPredstave.DRAMA;
		case 2:
			return TipPredstave.OPERA;
		case 3:
			return TipPredstave.BALET;
		default:
			return null;
		}
	}
	
}
