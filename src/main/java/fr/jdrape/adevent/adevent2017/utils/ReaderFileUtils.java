package fr.jdrape.adevent.adevent2017.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReaderFileUtils {

	public static List<Integer> getListeNombre(String nomFichier, String separator) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get("src", nomFichier));
		List<Integer> nombres = new ArrayList<>();
		String[] valeurs = lines.get(0).split(separator);
		// boucle sur chaque valeur
		for (String valeur : valeurs) {
			nombres.add(Integer.valueOf(valeur));
		}
		return nombres;
	}

	public static List<String> getListeChaine(String nomFichier) throws Exception {
		return Files.readAllLines(Paths.get(ReaderFileUtils.class.getResource("/" + nomFichier).toURI()));
	}

}
