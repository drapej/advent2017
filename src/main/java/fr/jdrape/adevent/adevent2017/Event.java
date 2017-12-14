package fr.jdrape.adevent.adevent2017;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import fr.jdrape.adevent.adevent2017.bean.Noeud;
import fr.jdrape.adevent.adevent2017.enumeration.TournerEnum;
import fr.jdrape.adevent.adevent2017.utils.ChaineUtils;
import fr.jdrape.adevent.adevent2017.utils.ListUtils;
import fr.jdrape.adevent.adevent2017.utils.ReaderFileUtils;

public class Event {

	public static void main(String[] args) throws Exception {
		day7_1();
	}

	static void day7_1() throws Exception {
		List<String> lines = ReaderFileUtils.getListeChaine("jour7.txt");

		Pattern pattern = Pattern.compile("([a-zA-Z]*) [(]([0-9]*)[)](?: -> (([a-z]+).*))?");

		Map<String, Noeud> liste = new HashMap<>();
		Noeud current;
		String[] detail;
		for (String line : lines) {

			Matcher matcher = pattern.matcher(line);
			matcher.find();
			detail = StringUtils.split(matcher.group(3), ",");
			current = new Noeud(Integer.valueOf(matcher.group(2)), matcher.group(1));
			liste.put(current.getNom(), current);

			// gestion des fils
			if (ArrayUtils.isNotEmpty(detail)) {
				// pour chacun on l'ajoute au parent
				Noeud nodeFils;
				for (String fils : detail) {
					// s'il existe dans le temporaire on le récupère
					if (liste.containsKey(fils.trim())) {
						nodeFils = liste.get(fils.trim());
					} else {
						// si on le crée
						nodeFils = new Noeud(null, fils.trim());
					}
					current.addFils(nodeFils);
				}
			}

			// si un noeud contient en tant que fils le noeud courant, on l'ajoute à ce
			// noeud
			for (Noeud noeud : liste.values()) {
				if (noeud.getFils().containsKey(current.getNom())) {
					noeud.addFils(current);
				}
			}
		}

		Noeud parentNode = null;
		for (Noeud node : liste.values()) {
			if (node.getParent() == null) {
				parentNode = node;
				break;
			}
		}

		for (Noeud fils : parentNode.getFils().values()) {
			fils.calculerPoidsTotalWeight();
		}

		checkPoids(parentNode);

	}

	static boolean checkPoids(Noeud noeud) {

		Noeud noeudLourd = null;
		Noeud noeudNormal = null;
		boolean existeDifference = false;
		for (Noeud fils : noeud.getFils().values()) {
			if (noeudLourd == null) {
				noeudNormal = fils;
				noeudLourd = fils;
			} else if (!noeudLourd.getFilsWeight().equals(fils.getFilsWeight())) {
				existeDifference = true;
				if (noeudLourd.getFilsWeight() < fils.getFilsWeight()) {
					noeudNormal = noeudLourd;
					noeudLourd = fils;
				} else {
					noeudNormal = fils;
				}
			}
		}

		if (existeDifference) {
			if (checkPoids(noeudLourd)) {
				System.out.println("Noeud " + noeudLourd.getPoids() + " " + noeudLourd.getFilsWeight() + " "
						+ noeudNormal.getFilsWeight());
				return false;
			}
		}

		return true;
	}

	static void day6_2() throws Exception {
		List<Integer> nombres = ReaderFileUtils.getListeNombre("jour6.txt", "\t");
		List<String> chaine = new ArrayList<>();
		System.out.println(StringUtils.join(nombres.toArray(), "|"));
		boolean isValeurNotFound = true;
		chaine.add(StringUtils.join(nombres.toArray(), "|"));
		String firstSame = "";
		int nextSameValue = 0;
		while (isValeurNotFound) {
			Integer maxValue = ListUtils.maxValue(nombres);
			List<Integer> indexConcerne = ListUtils.indexOfValue(nombres, maxValue);
			int index = indexConcerne.get(0);
			// on remet ï¿½ 0
			int nombreRestant = nombres.get(index);
			nombres.set(index, 0);
			int current;
			while (nombreRestant > 0) {
				current = (index + 1 + maxValue - nombreRestant) % nombres.size();
				nombres.set(current, nombres.get(current) + 1);
				nombreRestant--;
			}
			System.out.println(StringUtils.join(nombres.toArray(), "|"));

			if (chaine.contains(StringUtils.join(nombres.toArray(), "|"))) {
				// si mï¿½me chaine
				// si premier coup, on stocke l'info
				if (firstSame.isEmpty()) {
					firstSame = StringUtils.join(nombres.toArray(), "|");
				} else if (firstSame.equals(StringUtils.join(nombres.toArray(), "|"))) {
					isValeurNotFound = false;
					break;
				}
			}
			if (!firstSame.isEmpty()) {
				nextSameValue++;
			}
			chaine.add(StringUtils.join(nombres.toArray(), "|"));
		}
		System.out.println(nextSameValue);
	}

	static void day6_1() throws Exception {
		List<Integer> nombres = ReaderFileUtils.getListeNombre("jour6demo.txt", "\t");
		List<String> chaine = new ArrayList<>();
		System.out.println(StringUtils.join(nombres.toArray(), "|"));
		boolean isValeurNotFound = true;
		chaine.add(StringUtils.join(nombres.toArray(), "|"));

		while (isValeurNotFound) {
			Integer maxValue = ListUtils.maxValue(nombres);
			List<Integer> indexConcerne = ListUtils.indexOfValue(nombres, maxValue);
			int index = indexConcerne.get(0);
			// on remet ï¿½ 0
			int nombreRestant = nombres.get(index);
			nombres.set(index, 0);
			int current;
			while (nombreRestant > 0) {
				current = (index + 1 + maxValue - nombreRestant) % nombres.size();
				nombres.set(current, nombres.get(current) + 1);
				nombreRestant--;
			}
			System.out.println(StringUtils.join(nombres.toArray(), "|"));
			if (!chaine.contains(StringUtils.join(nombres.toArray(), "|"))) {
				chaine.add(StringUtils.join(nombres.toArray(), "|"));
			} else {
				isValeurNotFound = false;
				break;
			}
		}
		System.out.println(chaine.size());
	}

	static void day3_2() {
		Integer nombre = 277678;
		// Integer nombre = 25;
		int taille = Double.valueOf(Math.sqrt(nombre)).intValue();
		if (taille % 2 == 0) {
			taille++;
		}
		int[][] tableau = new int[taille][taille];
		int currentValeur = 1;
		int currentX = taille / 2 + 1;
		int currentY = currentX;
		int nombreChangement = 1;
		tableau[currentY - 1][currentX - 1] = currentValeur++;
		// on boucle tant qu'on n'a pas atteint le chiffre voulu
		while ((tableau[currentY - 1][currentX - 1]) <= nombre) {

			// on effectue l'opï¿½ration autant de fois qu'on a fait un changement
			// System.out.println(nombreChangement + " " + (nombreChangement % 4));

			for (int i = 0; i < ((nombreChangement / 2) + (nombreChangement % 2)); i++) {
				currentX += TournerEnum.getListe().get(((nombreChangement - 1) % 4)).getX();
				currentY += TournerEnum.getListe().get(((nombreChangement - 1) % 4)).getY();
				// System.out.println(
				// currentX + "-" + currentY + "-" + ListUtils.sommeCaseAutour(tableau, currentX
				// - 1, currentY - 1)
				// + "-" + TournerEnum.getListe().get((nombreChangement - 1) % 4));
				tableau[currentY - 1][currentX - 1] = ListUtils.sommeCaseAutour(tableau, currentX - 1, currentY - 1);
				currentValeur++;
				if ((tableau[currentY - 1][currentX - 1]) > nombre) {
					System.out.println(tableau[currentY - 1][currentX - 1] + " | " + currentValeur);
					break;
				}
			}
			nombreChangement++;

		}

		// // Affichage du tableau
		// for (int[] is : tableau) {
		// for (int i : is) {
		// System.out.print(i + "\t");
		// }
		// System.out.println("");
		// }

	}

	static void day3_1() {
		Integer nombre = 277678;
		int taille = Double.valueOf(Math.sqrt(nombre)).intValue();
		if (taille % 2 == 0) {
			taille++;
		}
		System.out.println("Taille grille : " + taille);
		int[][] tableau = new int[taille][taille];
		int currentValeur = 1;
		int currentX = taille / 2 + 1;
		int currentY = currentX;
		int nombreChangement = 1;
		tableau[currentY - 1][currentX - 1] = currentValeur++;
		// on boucle tant qu'on n'a pas atteint le chiffre voulu
		while (currentValeur < nombre) {

			// on effectue l'opï¿½ration autant de fois qu'on a fait un changement
			System.out.println(nombreChangement + " " + (nombreChangement % 4));

			for (int i = 0; i < ((nombreChangement / 2) + (nombreChangement % 2)); i++) {
				currentX += TournerEnum.getListe().get(((nombreChangement - 1) % 4)).getX();
				currentY += TournerEnum.getListe().get(((nombreChangement - 1) % 4)).getY();
				// System.out.println(currentX + "-" + currentY + "-" + currentValeur + "-"
				// + TournerEnum.getListe().get((nombreChangement - 1) % 4));
				tableau[currentY - 1][currentX - 1] = currentValeur++;
				if (currentValeur > nombre) {
					break;
				}
			}
			nombreChangement++;

		}

		// Affichage du tableau
		for (int[] is : tableau) {
			for (int i : is) {
				System.out.print(i + "\t");
			}
			System.out.println("");
		}

	}

	static void day5_2() throws Exception {
		List<String> lines = ReaderFileUtils.getListeChaine("jour5.txt");
		// convertit string en liste nombre
		List<Integer> nombres = new ArrayList<>();
		for (String valeur : lines) {
			nombres.add(Integer.valueOf(valeur));
		}

		int currentPos = 0;
		int nombreStep = 0;
		int oldPos;
		while (currentPos < nombres.size()) {
			nombreStep++;
			oldPos = currentPos;
			currentPos += nombres.get(currentPos);
			// on ajoute +1 ï¿½ l'ancienne position
			if (nombres.get(oldPos) >= 3) {
				nombres.set(oldPos, nombres.get(oldPos) - 1);
			} else {
				nombres.set(oldPos, nombres.get(oldPos) + 1);
			}

		}
		System.out.println(nombreStep);
	}

	static void day5_1() throws Exception {
		List<String> lines = ReaderFileUtils.getListeChaine("jour5.txt");
		// convertit string en liste nombre
		List<Integer> nombres = new ArrayList<>();
		for (String valeur : lines) {
			nombres.add(Integer.valueOf(valeur));
		}

		int currentPos = 0;
		int nombreStep = 0;
		int oldPos;
		while (currentPos < nombres.size()) {
			nombreStep++;
			oldPos = currentPos;
			currentPos += nombres.get(currentPos);
			// on ajoute +1 ï¿½ l'ancienne position
			nombres.set(oldPos, nombres.get(oldPos) + 1);

		}
		System.out.println(nombreStep);
	}

	static void day4_2() throws Exception {
		List<String> lines = ReaderFileUtils.getListeChaine("jour4.txt");
		int nombre = 0;

		// boucle sur chaque ligne
		boucleLigne: for (String item : lines) {
			String[] valeurs = item.split(" ");

			// boucle sur chaque mot
			for (int i = 0; i < valeurs.length; i++) {
				for (int j = 0; j < valeurs.length; j++) {
					// si meme indice on ignore
					if (i != j) {
						if (ChaineUtils.areAnnagramme(valeurs[i], valeurs[j])) {
							continue boucleLigne;
						}
					}
				}
			}
			nombre++;

		}

		System.out.println(nombre);
	}

	static void day4_1() throws Exception {
		List<String> lines = ReaderFileUtils.getListeChaine("jour4.txt");
		int nombre = 0;

		// boucle sur chaque ligne
		Set<String> list;
		for (String item : lines) {
			list = new HashSet<>();
			String[] valeurs = item.split(" ");
			list.addAll(Arrays.asList(valeurs));
			if (list.size() == valeurs.length) {
				nombre++;
			}

		}

		System.out.println(nombre);
	}

	static void day2_2() throws Exception {
		List<String> lines = ReaderFileUtils.getListeChaine("dateJour2");
		Integer somme = 0;

		List<Integer> nombres;
		// boucle sur chaque ligne
		for (String item : lines) {
			String[] valeurs = item.split("\t");
			nombres = new ArrayList<>();
			// boucle sur chaque valeur
			for (String valeur : valeurs) {
				nombres.add(Integer.valueOf(valeur));
			}

			for (int i = 0; i < nombres.size(); i++) {
				for (int j = 0; j < nombres.size(); j++) {
					// si meme indice on ignore
					if (i != j) {
						if (Math.floorMod(nombres.get(i), nombres.get(j)) == 0) {
							somme += Math.floorDiv(nombres.get(i), nombres.get(j));
							break;
						}
					}
				}
			}

		}

		System.out.println(somme);
	}

	static void day2_1() throws Exception {
		List<String> lines = ReaderFileUtils.getListeChaine("dateJour2");
		Integer somme = 0;

		// boucle sur chaque ligne
		for (String item : lines) {
			String[] valeurs = item.split("\t");
			List<Integer> nombres = new ArrayList<>();
			// boucle sur chaque valeur
			for (String valeur : valeurs) {
				nombres.add(Integer.valueOf(valeur));
			}
			// tri
			Collections.sort(nombres, Integer::compareTo);
			somme += nombres.get(nombres.size() - 1) - nombres.get(0);

		}

		System.out.println(somme);
	}

}
