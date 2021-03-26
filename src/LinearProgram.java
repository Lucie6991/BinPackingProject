import com.google.ortools.Loader;
import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPObjective;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;

import java.util.ArrayList;

public class LinearProgram {

    public BinPacking binPacking;
    public static int numBins;
    public static int numItems;
    private static int binCapacity;
    private static ArrayList<Integer> sizeItems = new ArrayList<>();

    public LinearProgram(BinPacking binPacking) {
        this.binPacking = binPacking;
        numItems = binPacking.getNbItem();
        numBins = numItems;
        binCapacity = binPacking.getBinCapacity();
        for (Item item : binPacking.getItems()) {
            sizeItems.add(item.getSize());
        }
    }

    public void run() {
        Loader.loadNativeLibraries();

        // Création du solveur linéaire
        MPSolver solver = MPSolver.createSolver("SCIP");
        if (solver == null) {
            System.out.println("Could not create solver SCIP");
            return;
        }

        // Création des variables
        MPVariable[][] x = new MPVariable[numItems][numBins];
        for (int i = 0; i < numItems; i++) {
            for (int j = 0; j < numBins; j++) {
                x[i][j] = solver.makeIntVar(0, 1, "");
                // 1 si l'item i est dans le bin j
            }
        }

        MPVariable[] y = new MPVariable[numBins];
        for (int j = 0; j <numBins; j++) {
            y[j] = solver.makeIntVar(0, 1, "");
            // 1 si le bin j est utilisé
        }

        // Définition des contraintes
        // sum (sur j) Xij = 1
        for (int i = 0; i < numItems; ++i) {
            MPConstraint constraint = solver.makeConstraint(1, 1, "");
            for (int j = 0; j < numBins; ++j) {
                constraint.setCoefficient(x[i][j], 1);
            }
        }

        // sum (sur i) Ti Xij <= T Yj
        double infinity = Double.POSITIVE_INFINITY;
        for (int j = 0; j < numBins; j++) {
            MPConstraint constraint = solver.makeConstraint(0, infinity, "");
            constraint.setCoefficient(y[j], binCapacity);
            for (int i = 0; i < numItems; i++) {
                constraint.setCoefficient(x[i][j], -sizeItems.get(i));
            }
        }

        // Xij <= Yj : forcé par les autres contraintes


        // Objectif : minimiser le nombre de bins
        MPObjective objective = solver.objective();
        for (int j = 0; j < numBins; j++) {
            objective.setCoefficient(y[j], 1);
        }
        objective.setMinimization();

        // Utilisation du solveur et affichage du résultat
        MPSolver.ResultStatus resultStatus = solver.solve();

        // On vérifie si la solution est optimale
        if (resultStatus == MPSolver.ResultStatus.OPTIMAL) {
            for (int j = 0; j < numBins; j++) {
                if (y[j].solutionValue() == 1) {
                    System.out.println("\nBin " + j + " : ");
                    for (int i = 0; i < numItems; i++) {
                        if (x[i][j].solutionValue() == 1) {
                            System.out.println("--> Item " + i + " = taille: " + sizeItems.get(i));
                        }
                    }
                }
            }
            System.out.println("\nNombre de bins utilisés : " + objective.value());
        }
        else {
            System.out.println("Le problème n'a pas de solution optimale");
        }
    }
}
