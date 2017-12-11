package fr.jdrape.adevent.adevent2017.utils;

import org.apache.commons.lang3.StringUtils;

public class ChaineUtils {

	public static boolean areAnnagramme(String mot1, String mot2) {
		// longueur dif �rente
		if (mot1.length() != mot2.length()) {
			return false;
		}
		// m�me mot
		if (StringUtils.equalsIgnoreCase(mot1, mot2)) {
			return true;
		}
		long total1 = multiplyAsciiChar(mot1);
		long total2 = multiplyAsciiChar(mot2);

		return total1 == total2;

	}

	public static long multiplyAsciiChar(String mot) {
		long total = 1;
		for (int i = 0; i < mot.length(); i++) {
			total *= mot.charAt(i);
		}
		return total;
	}

}
