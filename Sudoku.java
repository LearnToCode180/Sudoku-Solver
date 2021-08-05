import choco.Choco;
import choco.cp.model.CPModel;
import choco.cp.solver.CPSolver;
import choco.kernel.model.Model;
import choco.kernel.model.variables.integer.IntegerVariable;
import choco.kernel.solver.Solver;

public class Sudoku {
	public static void main(String[] args) {
		
		//Taille du problème
		int nbvar = 9;
		
		//Declaration de la grille
		int[][] grille = {
				             {5, 0, 3, 0, 0, 4, 2, 0, 0},
				             {2, 7, 0, 0, 0, 0, 0, 0, 1},
				             {0, 0, 0, 0, 2, 0, 0, 0, 0},
				             {0, 2, 0, 4, 0, 0, 0, 0, 7},
				             {4, 3, 0, 0, 8, 0, 0, 0, 2},
				             {0, 5, 0, 0, 0, 0, 8, 0, 0},
				             {0, 0, 0, 0, 0, 0, 0, 0, 0},
				             {0, 0, 0, 5, 9, 0, 0, 0, 4},
				             {3, 0, 5, 0, 1, 0, 9, 0, 6}
				     	 };
		
		//Declaration du modèle
		Model m = new CPModel();
		
		//Déclaration des variables et des domaines
		IntegerVariable[][] var= new IntegerVariable[nbvar][nbvar];
		
		for(int i = 0; i<nbvar; i++){
			for (int j = 0; j < nbvar; j++) {
				var[i][j] = Choco.makeIntVar("X"+(i+1)+(j+1), 1, nbvar);
				m.addVariable(var[i][j]);
			}
		}
		
		//Déclaration des contraintes
		
		//C1 : Lignes
		for(int i = 0; i<nbvar; i++){
			for(int j = 0; j<nbvar; j++){
				for (int k = j; k < nbvar; k++) {
					if(k != j)
						m.addConstraint(Choco.neq(var[i][j], var[i][k]));
				}
			}
		}
		
		//C2 : Colonnes
		for(int i = 0; i<nbvar; i++){
			for(int j = 0; j<nbvar; j++){
				for (int k = 0; k < nbvar; k++) {
					if(k != j)
						m.addConstraint(Choco.neq(var[j][i], var[k][i]));
				}
			}
		}
		
		//C3 : Carré
		for (int a = 0; a < nbvar; a += 3) {
			for (int b = 0; b < nbvar; b += 3) {
				for(int i = a; i < a + 3; i++) {
					for(int j = b; j < b + 3; j++) {
						for (int k = a; k < a + 3; k++) {
							for (int l = b; l < b + 3; l++) {
								if (k != i || l != j)
									m.addConstraint(Choco.neq(var[i][j], var[k][l]));
							}
						}
					}
				}
			}
		}
		
		for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grille[i][j] != 0) {
                    m.addConstraint(Choco.eq(var[i][j], grille[i][j]));
                }
            }
        }
		
		
		//Declaration du solveur
		Solver s =new CPSolver();
		//lecture du modele par le solveur
		s.read(m);
		//recherche de la premier solution
		s.solve();
		
		
		//Affichage des resultats
		int cmpCol = 0;
		int cmpLig = 0;
		
		for(int i = 0; i<nbvar; i++){
			cmpLig++;
			for (int j = 0; j < nbvar; j++) {
				System.out.print(s.getVar(var[i][j]) + " ");
				cmpCol++;
				if(cmpCol == 3) {
					System.out.print(" | ");
					cmpCol = 0;
				}
			}
			System.out.print("\n");
			if(cmpLig == 3) {
				cmpLig = 0;
				System.out.print("\n");
			}
		}
	}
}
