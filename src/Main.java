import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public Main() throws IOException {
    }

    public static void main(String[] args) throws IOException {

        Console console = new Console();
        //console.run();
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


        BinPacking binPacking1 = DataConverter.convertFile("data/binpack1d_00.txt");
        BinPacking binPacking2 = DataConverter.convertFile("data/binpack1d_01.txt");
        BinPacking binPacking3 = DataConverter.convertFile("data/binpack1d_02.txt");
        BinPacking binPacking4 = DataConverter.convertFile("data/binpack1d_03.txt");
        BinPacking binPacking5 = DataConverter.convertFile("data/binpack1d_04.txt");
        BinPacking binPacking6 = DataConverter.convertFile("data/binpack1d_05.txt");
        BinPacking binPacking7 = DataConverter.convertFile("data/binpack1d_06.txt");
        BinPacking binPacking8 = DataConverter.convertFile("data/binpack1d_11.txt");
        BinPacking binPacking9 = DataConverter.convertFile("data/binpack1d_12.txt");
        BinPacking binPacking10 = DataConverter.convertFile("data/binpack1d_13.txt");
        BinPacking binPacking11 = DataConverter.convertFile("data/binpack1d_14.txt");
        BinPacking binPacking12 = DataConverter.convertFile("data/binpack1d_21.txt");
        BinPacking binPacking13 = DataConverter.convertFile("data/binpack1d_31.txt");

        List<BinPacking> bps = new ArrayList<>();
        bps.add(binPacking1);
        bps.add(binPacking2);
        bps.add(binPacking3);
        bps.add(binPacking4);
        bps.add(binPacking5);
        bps.add(binPacking6);
        bps.add(binPacking7);
        bps.add(binPacking8);
        bps.add(binPacking9);
        bps.add(binPacking10);
        bps.add(binPacking11);
        bps.add(binPacking12);
        bps.add(binPacking13);

        for (BinPacking bp : bps) {
            System.out.println("----> Borne inférieure : ");
            System.out.println(bp.lowerBound());

            System.out.println("----> First fit decreasing : ");
            BinPacking bpClone = bp.clone();
            SolutionGenerator.firstFitDecreasing(bpClone);
            System.out.println(bpClone.toStringBins());
            System.out.println("Nombre de bins : " + bpClone.getSolutionCount());
            System.out.println("Fitness : " + bpClone.getFitness());

            System.out.println("----> One item by bin : ");
            bpClone = bp.clone();
            SolutionGenerator.oneItemByBin(bpClone);
            System.out.println(bpClone.toStringBins());
            System.out.println("Nombre de bins : " + bpClone.getSolutionCount());
            System.out.println("Fitness : " + bpClone.getFitness());

            System.out.println("----> First Fit Randomly: ");
            bpClone = bp.clone();
            SolutionGenerator.firstFitRandomly(bpClone);
            System.out.println(bpClone.toStringBins());
            System.out.println("Nombre de bins : " + bpClone.getSolutionCount());
            System.out.println("Fitness : " + bpClone.getFitness());

            System.out.println("----> First Fit Increasing : ");
            bpClone = bp.clone();
            SolutionGenerator.firstFitIncreasing(bpClone);
            System.out.println(bpClone.toStringBins());
            System.out.println("Nombre de bins : " + bpClone.getSolutionCount());
            System.out.println("Fitness : " + bpClone.getFitness());
        }


    }
}
