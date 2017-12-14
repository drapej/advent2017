package fr.jdrape.adevent.adevent2017;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.jdrape.adevent.adevent2017.enumeration.EnumOperateur;
import fr.jdrape.adevent.adevent2017.utils.ReaderFileUtils;

public class Event8 {

	public static void main(String[] args) throws Exception {
		List<String> lines = ReaderFileUtils.getListeChaine("jour8.txt");
		String[] chaineCoupe;
		Map<String, Integer> mapsValues = new HashMap<>();
		Integer valeurATester;
		for (String line : lines) {
			chaineCoupe = line.split(" ");
			// 0 : nom
			// 1 : opératopn
			// 2 : valeur
			// 4 : nom condition
			// 5 operatuer
			// 6 : valeur testé
			valeurATester = mapsValues.getOrDefault(chaineCoupe[4], 0);

			switch (EnumOperateur.getEnumOperateur(chaineCoupe[5])) {
			case DIFF:
				break;
			case EGAL:
				break;
			case INF:
				break;
			case INF_EGAL:
				break;
			case SUP:
				break;
			case SUP_EGAL:
				break;

			default:
				System.out.println("erreur");
				break;
			}

		}

	}

}
