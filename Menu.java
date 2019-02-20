
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
				"1 - Je choisie sur quel ligne/colonne calculer \n2 - Automatique\n3 - Automatique Avec insertion zero\n4 - Calcule déterminant exposant m\n5 - Inverse\n6 - Cramer \n7 - Vandermonde");
		int choixCalcul = sc.nextInt();

		while (choixCalcul != 1 && choixCalcul != 2 && choixCalcul != 3 && choixCalcul != 4 && choixCalcul != 5
				&& choixCalcul != 6 && choixCalcul != 7) {
			System.out.println("Erreur Choisir entre 1 et 2 et 3 et 4 et 5 et 6 et 7");
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
		} else if (choixCalcul == 6) {
			float b[] = new float[nb];
			for (int i = 0; i < b.length; i++) {
				System.out.println("Choisir la valeur numéro " + (i + 1) + " de B");
				float choixB = sc.nextFloat();
				b[i] = choixB;
			}
			System.out.println(calculCramer(matrice, b));
		} else if (choixCalcul == 7) {

		}
	
