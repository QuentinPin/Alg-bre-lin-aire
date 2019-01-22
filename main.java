
public class main {

	public static void main(String[] args) {
		float[][] matrice = {{5, 2, -2},{1, 3, -3},{-1, 1, 1}};
		System.out.println("Le déterminant est : " + calculerDeterminantParLigne(0, matrice));
	}
	
	public static float calculerDeterminantParLigne(int ligne,float[][] matrice){
		float res = 0;
		if (matrice.length>2){
			for(int i = 0; i<matrice.length; i++){
				float[][] tempo = copieSansLigneColonne(ligne, i, matrice);
				res += matrice[ligne][i]*Math.pow(-1, 1+i+1)*calculerDeterminantParLigne(0,tempo);
			}
		}else{
			res = matrice[0][0]* matrice[1][1]-matrice[1][0]*matrice[0][1];
		}
		return res;
	}
	
	public static float[][] copieSansLigneColonne(int ligne, int colonne, float[][] matrice){
		float[][] res = new float[matrice.length-1][matrice.length-1];
		for(){
			
		}
		return res;
	}

}
