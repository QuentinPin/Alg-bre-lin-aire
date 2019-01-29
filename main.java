import java.util.Scanner;

public class main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez saisir le n :");
		int nb = sc.nextInt();

		// CHOIX MATRICE
		float[][] matrice = new float[nb][nb];

		int ligne;
		int colonne;

		// Création de la matrice
		for (int l = 0; l < nb; l++) {
			for (int c = 0; c < nb; c++) {
				ligne = l + 1;
				colonne = c + 1;
				System.out.println("Veuillez saisir un la valeur en a ligne : " + ligne + " colonne : " + colonne);
				float nombre = sc.nextFloat();
				matrice[l][c] = nombre;

			}
		}

		System.out.println(
				"1 - Je choisie sur quel ligne/colonne calculer \n2 - Automatique\n3 - Automatique Avec insertion zero");
		int choixCalcul = sc.nextInt();

		while (choixCalcul != 1 && choixCalcul != 2 && choixCalcul != 3) {
			System.out.println("Erreur Choisir entre 1 et 2 et 3");
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
				System.out.println("Le déterminant est : " + calculerDeterminantParLigne(choixL, matrice));
			} else if (choixCOuL == 1) {
				System.out.println("Choisir le numero de la colonne");
				int choixC = sc.nextInt() - 1;
				while (choixC < 1 && choixC > matrice.length) {
					System.out.println("Erreur choisir un nombre entre 1 et " + matrice.length);
					choixC = sc.nextInt();
				}
				System.out.println("Le déterminant est : " + calculerDeterminantParLigne(choixC, matrice));
			}

		} else if (choixCalcul == 2) { // Automatique
			System.out.println("Le déterminant est : " + calculDeterminantAutomatique(matrice));
		} else if (choixCalcul == 3) {
			System.out.println("Le déterminant est : " + calculDeterminantAvecInsertionZero(matrice));
		}
		// float[][] matrice = { { 2, 2, -4 }, { 2, 1, -3 }, { 1, 0, 0} };

	}

	public static float calculerDeterminantParLigne(int ligne, float[][] matrice) {
		float res = 0;
		if (matrice.length > 2) {
			for (int i = 0; i < matrice.length; i++) {
				float[][] tempo = copieSansLigneColonne(ligne, i, matrice);
				res += matrice[ligne][i] * Math.pow(-1, ligne + i) * calculerDeterminantParLigne(0, tempo);
			}
		} else {
			res = matrice[0][0] * matrice[1][1] - matrice[1][0] * matrice[0][1];
		}
		return res;
	}

	public static float calculerDeterminantParColonne(int colonne, float[][] matrice) {
		float res = 0;
		if (matrice.length > 2) {
			for (int i = 0; i < matrice.length; i++) {
				float[][] tempo = copieSansLigneColonne(i, colonne, matrice);
				res += matrice[i][colonne] * Math.pow(-1, colonne + i) * calculerDeterminantParLigne(0, tempo);
			}
		} else {
			res = matrice[0][0] * matrice[1][1] - matrice[1][0] * matrice[0][1];
		}
		return res;
	}

	public static float[][] copieSansLigneColonne(int ligne, int colonne, float[][] matrice) {
		float[][] res = new float[matrice.length - 1][matrice.length - 1];
		int coloneInserer = 0;
		int ligneInserer = 0;
		for (int i = 0; i < matrice.length; i++) {
			for (int y = 0; y < matrice.length; y++) {
				if (i != ligne && y != colonne) {
					res[ligneInserer][coloneInserer] = matrice[i][y];
					coloneInserer++;
					if (coloneInserer >= matrice.length - 1) {
						coloneInserer = 0;
						ligneInserer++;
					}
				}
			}
		}
		return res;
	}

	public static float calculDeterminantAutomatique(float[][] matrice) {
		int cptZero = 0;
		int maxZero = 0;
		String choix = "";
		int index = 0;
		for (int i = 0; i < matrice.length; i++) {
			for (int y = 0; y < matrice.length; y++) {
				if (matrice[i][y] == 0) {
					cptZero++;
				}
			}
			if (cptZero > maxZero) {
				maxZero = cptZero;
				index = i;
				choix = "ligne";
			}
			cptZero = 0;
		}
		cptZero = 0;
		for (int i = 0; i < matrice.length; i++) {
			for (int y = 0; y < matrice.length; y++) {
				if (matrice[y][i] == 0) {
					cptZero++;
				}

			}
			if (cptZero > maxZero) {
				maxZero = cptZero;
				index = i;
				choix = "colonne";
			}
			cptZero = 0;
		}
		System.out.println("Automatique sur la " + choix + " numéro " + (index + 1));
		if (choix.equals("ligne")) {
			return calculerDeterminantParLigne(index, matrice);
		}
		if (choix.equals("colonne")) {
			return calculerDeterminantParColonne(index, matrice);
		}
		return calculerDeterminantParLigne(0, matrice);
	}

	public static float calculDeterminantAvecInsertionZero(float[][] matrice) {
		int cptZero = 0;
		int maxZero = 0;
		String choix = "";
		int index = 0;
		for (int i = 0; i < matrice.length; i++) {
			for (int y = 0; y < matrice.length; y++) {
				if (matrice[i][y] == 0) {
					cptZero++;
				}
			}
			if (cptZero > maxZero) {
				maxZero = cptZero;
				index = i;
				choix = "ligne";
			}
			cptZero = 0;
		}
		cptZero = 0;
		for (int i = 0; i < matrice.length; i++) {
			for (int y = 0; y < matrice.length; y++) {
				if (matrice[y][i] == 0) {
					cptZero++;
				}

			}
			if (cptZero > maxZero) {
				maxZero = cptZero;
				index = i;
				choix = "colonne";
			}
			cptZero = 0;
		}
		int nbNonzero = 0;
		System.out.println("Zéro créer sur la " + choix + " numéro " + (index + 1));
		if (choix.equals("ligne")) {
			if (maxZero == matrice.length - 1) {
				calculerDeterminantParLigne(index, matrice);
			}
			nbNonzero = matrice.length - maxZero;
			int indexNonZero = 0;
			for (int i = 0; i < matrice.length; i++) {
				if (matrice[index][i] != 0) {
					indexNonZero = i;
					break;
				}
			}
			for (int colonne = 0; colonne < matrice.length; colonne++) {
				if (matrice[index][colonne] != 0 && colonne != indexNonZero) {
					float x = matrice[index][colonne] / matrice[index][indexNonZero];
					for (int l = 0; l < matrice.length; l++) {
						matrice[l][colonne] -= x * matrice[l][indexNonZero];
					}
				}
			}
			return calculerDeterminantParLigne(index, matrice);
		} else {

		}
		return 0;
	}

	public static void afficheMatrice(float[][] matrice) {
		for (int i = 0; i < matrice.length; i++) {
			for (int j = 0; j < matrice.length; j++) {
				System.out.print(matrice[i][j] + "\t");
			}
			System.out.println();
		}
	}
}
