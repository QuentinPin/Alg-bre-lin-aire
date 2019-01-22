
public class main {

	public static void main(String[] args) {
		float[][] matrice = { { 2, 2, -4 }, { 2, 1, -3 }, { 1, 0, 0} };
		System.out.println("Le déterminant est : " + calculDeterminantAutomatique(matrice));
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
		System.out.println(choix + " sur l'index " + index);
		if (choix.equals("ligne")) {
			return calculerDeterminantParLigne(index, matrice);
		}
		if (choix.equals("colonne")) {
			return calculerDeterminantParColonne(index, matrice);
		}
		return calculerDeterminantParLigne(0, matrice);
	}

}
