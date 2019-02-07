package algebreLineaire;

import java.util.Scanner;

public class main {

	public static void main(String[] args) {Scanner sc = new Scanner(System.in);
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
			"1 - Je choisie sur quel ligne/colonne calculer \n2 - Automatique\n3 - Automatique Avec insertion zero\n4 - Calcule déterminant exposant m\n5 - Inverse\n6 - Cramer");
	int choixCalcul = sc.nextInt();

	while (choixCalcul != 1 && choixCalcul != 2 && choixCalcul != 3 && choixCalcul != 4 && choixCalcul != 5 && choixCalcul!= 6) {
		System.out.println("Erreur Choisir entre 1 et 2 et 3 et 4 et 5 et 6");
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
	} else if (choixCalcul == 4) {

		System.out.println("Choisir la puissance");
		int choixM = sc.nextInt();
		while (choixM < 1) {
			System.out.println("Erreur choisir un nombre suppérieur ou égale a 1");
			choixM = sc.nextInt();
		}
		calculerDeterminantExposantN(choixM, matrice);
	} else if (choixCalcul == 5) {
		calculerMatriceInverse(matrice);
	} else if (choixCalcul == 6){
		float b[] = new float[nb];
		for (int i = 0; i < b.length; i++) {
			System.out.println("Choisir la valeur numéro "+ (i+1) +" de B");
			float choixB = sc.nextFloat();
			b[i] = choixB;
		}
		System.out.println(calculCramer(matrice, b));
	}
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
		String choix = "ligne";
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
		//System.out.println("Automatique sur la " + choix + " num�ro " + (index + 1));
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
		String choix = "ligne";
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
		System.out.println("Z�ro cr�er sur la " + choix + " num�ro " + (index + 1));
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
			afficheMatrice(matrice);
			return calculerDeterminantParLigne(index, matrice);
		} else {
			if (maxZero == matrice.length - 1) {
				calculerDeterminantParColonne(index, matrice);
			}
			nbNonzero = matrice.length - maxZero;
			int indexNonZero = 0;
			for (int i = 0; i < matrice.length; i++) {
				if (matrice[i][index] != 0) {
					indexNonZero = i;
					break;
				}
			}
			for (int ligne = 0; ligne < matrice.length; ligne++) {
				if (matrice[ligne][index] != 0 && ligne != indexNonZero) {
					float x = matrice[ligne][index] / matrice[indexNonZero][index];
					for (int c = 0; c < matrice.length; c++) {
						matrice[ligne][c] -= x * matrice[indexNonZero][c];
					}
				}
			}
			afficheMatrice(matrice);
			return calculerDeterminantParColonne(index, matrice);
		}
	}
	
	public static void calculerDeterminantExposantN(int m, float[][] matrice) {
		float[][] matriceExposantN = new float[matrice.length][matrice.length];
		float[][] tempo = new float[matrice.length][matrice.length];
		for (int i = 0; i < matrice.length; i++) {
			for (int j = 0; j < matrice.length; j++) {
				tempo[i][j] = matrice[i][j];
			}
		}
		for (int exposant = 1; exposant < m; exposant++) {
			for (int i = 0; i < matrice.length; i++) {
				for (int j = 0; j < matrice.length; j++) {
					float resultatMultiplicationUnPoint = 0;
					for (int k = 0; k < matriceExposantN.length; k++) {
						resultatMultiplicationUnPoint += tempo[i][k] * matrice[k][j];
					}
					matriceExposantN[i][j] = resultatMultiplicationUnPoint;
				}
			}
			for (int i = 0; i < matrice.length; i++) {
				for (int j = 0; j < matrice.length; j++) {
					tempo[i][j] = matriceExposantN[i][j];
				}
			}
		}
		System.out.println("det(A^m) = " + calculDeterminantAvecInsertionZero(matriceExposantN) + " et (det(A))^m = " + Math.pow(calculDeterminantAvecInsertionZero(matrice), m));
	}

	public static void afficheMatrice(float[][] matrice) {
		for (int i = 0; i < matrice.length; i++) {
			for (int j = 0; j < matrice.length; j++) {
				System.out.print(matrice[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("\n");
	}
	
	public static float calculerDeterminantDeVonDerMnde(int n, float [][] matrice){
		
		return 0.0f;
	}
	
	public static float[][] calculerMatriceInverse(float[][] matrice){
		float[][] matriceInverse = new float[matrice.length][matrice.length];
		float determiant = calculDeterminantAutomatique(matrice);
		if (determiant == 0) {
			System.out.println("La matrice donne un déterminant égale a 0 donc elle n'est pas inversible.");
		}else {
			System.out.print ("A^(-1) = 1/" + determiant + " * \n"); 
			afficheMatrice(caclulTransposeeComatrice(matrice));
		}
		return matriceInverse;
	}
	
	public static float[][] caclulTransposeeComatrice(float[][] matrice){
		float[][] comatrice = new float [matrice.length][matrice.length];
		if (matrice.length == 2) {
			comatrice [0][0] = matrice [1][1];
			comatrice [1][1] = matrice [0][0];
			comatrice [0][1] = - matrice [0][1];
			comatrice [1][0] = - matrice [1][0];
			return comatrice;
		}else {
			for (int i = 0; i < matrice.length ; i++) {
				for (int y = 0; y < matrice.length ; y++ ) {
					comatrice [i][y] = (float) (Math.pow(-1, i+y)*calculDeterminantAutomatique(copieSansLigneColonne(i, y, matrice)));
				}
			}
			//Transposé
			float[][] transposeMatrice = new float [matrice.length][matrice.length];
			for (int i = 0; i < matrice.length ; i++) {
				for (int y = 0; y < matrice.length ; y++) {
					transposeMatrice [i][y] = comatrice [y][i];
				}
			}
			return transposeMatrice;
		}
	}
	
	public static String calculCramer(float[][] matrice, float[] b) {
		float determinant = calculDeterminantAutomatique(matrice);
		String solution = "";
		if(determinant != 0) {
			solution += "La solution unique est : {(";
			for (int i = 0; i < matrice.length ; i++) {
				solution += calculDeterminantAutomatique(transformeMatricePourCramer(matrice, b, i))/determinant;
				if (i != matrice.length-1)
					solution += ", ";
			}
			solution += ")}";
		}else {
			solution = "La matrice n'est pas inversible donc le système n'est pas de Cramer et donc n'admet aucune solution.";
		}
		return solution;
	}
	
	public static float[][] transformeMatricePourCramer(float[][] matrice, float[] b, int indice){
		float[][] matriceTransformer  = new float[matrice.length][matrice.length];
		for (int i = 0; i < matrice.length; i++) {
			for (int j = 0; j < matrice.length; j++) {
				matriceTransformer[i][j] = matrice[i][j];
			}
		}
		for(int i = 0; i < matrice.length; i++) {
			matriceTransformer[i][indice] = b[i];
		}
		return matriceTransformer;
	}
	
	
}
