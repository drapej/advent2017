package fr.jdrape.adevent.adevent2017.enumeration;

import java.util.Arrays;
import java.util.List;

public enum TournerEnum {

	DROITE(1, 0), HAUT(0, -1), GAUCHE(-1, 0), BAS(0, 1);

	int x;
	int y;

	private TournerEnum(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public static List<TournerEnum> getListe() {
		return Arrays.asList(DROITE, HAUT, GAUCHE, BAS);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
