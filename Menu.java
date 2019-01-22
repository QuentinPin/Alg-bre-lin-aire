package determinant;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TAILLE
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez saisir le n :");
		int nb = sc.nextInt();

		// CHOIX MATRICE
		float[][] matrice = new float[nb][nb];

		int ligne;
		int colonne;

		for (int l = 0; l < nb; l++) {
			for (int c = 0; c < nb; c++) {
				ligne = l + 1;
				colonne = c + 1;
				System.out.println("Veuillez saisir un la valeur en a ligne : " + ligne + " colonne : " + colonne);
				float nombre = sc.nextFloat();
				matrice[l][c] = nombre;

			}
		}

		System.out.println("1 - Je choisie sur quel ligne/colonne calculer \n2 - Automatique");
		int choixCalcul = sc.nextInt();

		while (choixCalcul != 1 && choixCalcul != 2) {
			System.out.println("Erreur Choisir entre 1 et 2");
			choixCalcul = sc.nextInt();
		}

		if (choixCalcul == 1) {
			// CHOIX COLONNE OU LIGNE
			System.out.println("Taper \"1\" pour le le calcul en colonne ou \"2\" pour le calcule en ligne");
			int choixCOuL = sc.nextInt();

			while (choixCOuL != 1 && choixCOuL != 2) {
				System.out.println("Erreur Choisir entre c et l");
				choixCOuL = sc.nextInt();
			}

			if (choixCOuL == 2) {

				System.out.println("Choisir le numero de la ligne");
				int choixL = sc.nextInt() - 1;

				while (choixL < 1 && choixL > matrice.length) {
					System.out.println("Erreur choisir un nombre entre 1 et " + matrice.length);
					choixL = sc.nextInt();
				}
			} else if (choixCOuL == 1) {
				System.out.println("Choisir le numero de la colonne");
				int choixC = sc.nextInt() - 1;

				while (choixC < 1 && choixC > matrice.length) {
					System.out.println("Erreur choisir un nombre entre 1 et " + matrice.length);
					choixC = sc.nextInt();
				}
			}

		} else if (choixCalcul == 2) {

		}

	}

}
