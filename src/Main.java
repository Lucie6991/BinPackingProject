import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        Console console = new Console();
        console.run();
//
//        // Conversion des fichiers
//        BinPacking binPacking1 = DataConverter.convertFile("data/binpack1d_01.txt");
//        BinPacking binPacking2 = DataConverter.convertFile("data/binpack1d_01.txt");
//        BinPacking binPacking3 = DataConverter.convertFile("data/binpack1d_01.txt");
//        BinPacking binPacking4 = DataConverter.convertFile("data/binpack1d_01.txt");
//
////        // Exercice 1
////        System.out.println("----> Borne inférieure : ");
////        System.out.println(binPacking1.lowerBound());
////
////        // Exercice 2
//        System.out.println("----> First fit decreasing : ");
//        SolutionGenerator.firstFitDecreasing(binPacking1);
//        System.out.println(binPacking1.toStringBins());
//        System.out.println("Nombre de bins : " + binPacking1.getSolutionCount());
//        System.out.println("Fitness : " + binPacking1.getFitness());
//
//
//        // Exercice 3
////        LinearProgram linearProgram = new LinearProgram(binPacking1);
////        linearProgram.run();
////
//        // Exercice 4
//        System.out.println("----> One item by bin : ");
//        SolutionGenerator.oneItemByBin(binPacking2);
//        System.out.println(binPacking2.toStringBins());
//        System.out.println("Nombre de bins : " + binPacking2.getSolutionCount());
//        System.out.println("Fitness : " + binPacking2.getFitness());
////
////
//        System.out.println("----> First Fit Randomly: ");
//        SolutionGenerator.firstFitRandomly(binPacking3);
//        System.out.println(binPacking3.toStringBins());
//        System.out.println("Nombre de bins : " + binPacking3.getSolutionCount());
//        System.out.println("Fitness : " + binPacking3.getFitness());
//
//
//        System.out.println("----> First Fit Increasing : ");
//        SolutionGenerator.firstFitIncreasing(binPacking4);
//        System.out.println(binPacking4.toStringBins());
//        System.out.println("Nombre de bins : " + binPacking4.getSolutionCount());
//        System.out.println("Fitness : " + binPacking4.getFitness());
//
////
////
////        // Exercice 5
////        // On crée les opérateurs de voisinage de notre choix pour la métaheuristique
//        List<NeighborhoodOperator> operators = new ArrayList<>();
//        operators.add(NeighborhoodOperator.RELOCATE);
//        operators.add(NeighborhoodOperator.EXCHANGE);
//        Metaheuristic metaheuristic = new Metaheuristic(operators, 10, 2);
////
////
////        // Exercice 7
//        System.out.println("----> Recherche Tabou : ");
//        BinPacking solutionTabuSearch = metaheuristic.tabuSearch(binPacking1, true);
//        System.out.println(solutionTabuSearch.toStringBins());
//        System.out.println("Nombre de bins : " + solutionTabuSearch.getSolutionCount());
//        System.out.println("Fitness : " + solutionTabuSearch.getFitness());
//
//        System.out.println("----> Recherche Recuit Simulé : ");
//        BinPacking solutionSimulatedAnneling = metaheuristic.simulatedAnnealing(binPacking1, 100, 0.95);
//        System.out.println(solutionSimulatedAnneling.toStringBins());
//        System.out.println("Nombre de bins : " + solutionSimulatedAnneling.getSolutionCount());
//        System.out.println("Fitness : " + solutionSimulatedAnneling.getFitness());

    }
}
