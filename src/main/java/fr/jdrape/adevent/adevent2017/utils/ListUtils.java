package fr.jdrape.adevent.adevent2017.utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ListUtils {

	/**
	 * Get max value of list.
	 * 
	 * @param list
	 * @return
	 */
	public static Integer maxValue(List<Integer> list) {
		return list.stream().mapToInt(Integer::intValue).max().getAsInt();
	}

	public static <E> List<Integer> indexOfValue(List<E> list, E value) {
		return IntStream.range(0, list.size()).filter(i -> list.get(i).equals(value)).boxed()
				.collect(Collectors.toList());
	}

	public static <E> void afficheList(List<E> list) {
		list.forEach(elt -> System.out.println(elt));
	}

	public static Integer sommeCaseAutour(int[][] tableau, int x, int y) {
		int somme = 0;

		for (int j = y - 1; j <= y + 1; j++) {
			for (int i = x - 1; i <= x + 1; i++) {
				// on ignore la case centrale
				if (i != x || j != y) {
					if (i >= 0 && i < tableau.length && j >= 0 && j < tableau.length) {
						somme += tableau[j][i];
					}
				}
			}
		}
		return somme;
	}

}
