package Konzola;
import java.util.Scanner;

public class Utility {
	static int ocitajBroj(Scanner sc) {
		int ceoBroj = 0;
		boolean notRead = true;
		do {
			if (sc.hasNextInt()) {
				ceoBroj = sc.nextInt();
				notRead = false;
			} else {
				if (!sc.nextLine().isEmpty()) {
					System.out.println("Greska! - Pokusajte ponovo.");
				}
			}
			sc.nextLine();
		} while (notRead);
		return ceoBroj;
	}

	static double ocitajDouble(Scanner sc) {
		double doubleBroj = 0;
		boolean notRead = true;
		do {
			if (sc.hasNextInt()) {
				doubleBroj = sc.nextDouble();
				notRead = false;
			} else {
				if (!sc.nextLine().isEmpty()) {
					System.out.println("Greska! - Pokusajte ponovo.");
				}
			}
			sc.nextLine();
		} while (notRead);
		return doubleBroj;
	}

	static String ocitajTekst(Scanner sc) {
		String tekst = sc.nextLine();
		return tekst;
	}

}