package fr.jdrape.adevent.adevent2017;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tester {

	public static void main(String[] args) {
		String chaine = "dsqd (62) -> ddsx, dsds";
		// String chaineSimple = "dsqd (62) -> ";
		String chaineSimple = "dsqd (62) -> sdsq, dsds";

		// Matcher match =
		// Pattern.compile("(^[a-zA-Z]{4})|([0-9]{2}$)").matcher(chaineSimple);
		// match.matches();
		// for (int i = 0; i < match.groupCount(); i++) {
		// System.out.println(match.group(i));
		// }

		String line = "This order was placed for QT3000! OK?";
		Pattern pattern = Pattern.compile("([a-zA-Z]*) [(]([0-9]*)[)](?: -> (([a-z]+).*))?");
		Matcher matcher = pattern.matcher(chaineSimple);
		// matcher.find();

		while (matcher.find()) {
			for (int i = 0; i < matcher.groupCount(); i++) {
				System.out.println("Group " + i + ":" + matcher.group(i));
			}
		}

		// TODO Auto-generated method stub

	}

	// public static void main(String args[]) {
	// String line = "This order was placed for QT3000! OK?";
	// String pattern = "(.*)(\\d+)(.*)";
	//
	// // Create a Pattern object
	// Pattern r = Pattern.compile(pattern);
	//
	// // Now create matcher object.
	// Matcher m = r.matcher(line);
	// m.find();
	// for (int i = 0; i < m.groupCount(); i++) {
	// System.out.println(m.group(i));
	// }
	// }

}
