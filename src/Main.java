import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        // Conversion des fichiers
        BinPacking binPacking1 = DataConverter.convertFile("data/binpack1d_00.txt");
        BinPacking binPacking2 = DataConverter.convertFile("data/binpack1d_00.txt");
        BinPacking binPacking3 = DataConverter.convertFile("data/binpack1d_00.txt");
        BinPacking binPacking4 = DataConverter.convertFile("data/binpack1d_00.txt");

        // Exercice 1
        System.out.println("----> Borne inférieure : ");
        System.out.println(binPacking1.lowerBound());

        // Exercice 2
        System.out.println("----> First fit decreasing : ");
        SolutionGenerator.firstFitDecreasing(binPacking1);
        System.out.println(binPacking1.getSolutionCount());
        System.out.println(binPacking1.toStringBins());

        // Exercice 3
//        LinearProgram linearProgram = new LinearProgram(binPacking1);
//        linearProgram.run();

        // Exercice 4
        System.out.println("----> One item by bin : ");
        SolutionGenerator.oneItemByBin(binPacking2);
        System.out.println(binPacking2.getSolutionCount());
        System.out.println(binPacking2.toStringBins());

        System.out.println("----> First Fit Randomly: ");
        SolutionGenerator.firstFitRandomly(binPacking3);
        System.out.println(binPacking3.getBins().size());
        System.out.println(binPacking3.toStringBins());

        System.out.println("----> First Fit Increasing : ");
        SolutionGenerator.firstFitIncreasing(binPacking4);
        System.out.println(binPacking4.getSolutionCount());
        System.out.println(binPacking4.toStringBins());


        // Exercice 5
        // On crée les opérateurs de voisinage de notre choix pour la métaheuristique
        List<NeighborhoodOperator> operators = new ArrayList<>();
        operators.add(NeighborhoodOperator.RELOCATE);
//        operators.add(NeighborhoodOperator.EXCHANGE);
        Metaheuristic metaheuristic = new Metaheuristic(operators, 1000, 2);


        // Exercice 7
        System.out.println("----> Recherche Tabou : ");
        BinPacking solutionTabuSearch = metaheuristic.tabuSearch(binPacking1, true);
        System.out.println(solutionTabuSearch.toStringBins());
        System.out.println(solutionTabuSearch.getSolutionCount());


    }
}
