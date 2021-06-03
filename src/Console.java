import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Console {
    Scanner scanner = new Scanner(System.in);
    BinPacking binPacking;

    public void run() throws IOException {
        int choix;
        System.out.println("Bienvenue !\n");

        while (true){
            menu1();
            choix = scanner.nextInt();
            switch (choix) {
                case 1:
                    choixBinPacking();
                    System.out.println("----> Borne inférieure : ");
                    System.out.println(binPacking.lowerBound());
                    break;
                case 2:
                    choixBinPacking();
                    System.out.println("----> Programmation Linéaire : ");
                    LinearProgram linearProgram = new LinearProgram(binPacking);
                    linearProgram.run();
                    break;
                case 3:
                    choixBinPacking();
                    System.out.println("----> First fit decreasing : ");
                    SolutionGenerator.firstFitDecreasing(binPacking);
                    affichageSolution();
                    break;
                case 4:
                    choixBinPacking();
                    System.out.println("----> One item by bin : ");
                    SolutionGenerator.oneItemByBin(binPacking);
                    affichageSolution();
                    break;
                case 5:
                    choixBinPacking();
                    System.out.println("----> First Fit Randomly: ");
                    SolutionGenerator.firstFitRandomly(binPacking);
                    affichageSolution();
                    break;
                case 6:
                    choixBinPacking();
                    System.out.println("----> First Fit Increasing : ");
                    SolutionGenerator.firstFitIncreasing(binPacking);
                    affichageSolution();
                    break;
                case 7:
                    recuitSimule();
                    break;
                case 8:
                    tabou();
                    break;
                case 9:
                    return ;
            }
        }
    }

    public void menu1() {
        System.out.println("1. Calcul de la borne inférieure");
        System.out.println("2. Lancement de la programmation linéaire");
        System.out.println("3. Lancement de l'algorithme First fit decreasing");
        System.out.println("4. Lancement de l'algorithme One item by bin");
        System.out.println("5. Lancement de l'algorithme First Fit Randomly");
        System.out.println("6. Lancement de l'algorithme First Fit Increasing");
        System.out.println("7. Lancement de la recherche Recuit simulé");
        System.out.println("8. Lancement de la recherche Tabou");
        System.out.println("9. Quitter");
    }

    public void menu2() {
        System.out.println("Veuillez sélectionner un algorithme pour générer une solution initiale");
        System.out.println("1. Solution initiale avec l'algorithme First fit decreasing");
        System.out.println("2. Solution initiale avec l'algorithme One item by bin");
        System.out.println("3. Solution initiale avec l'algorithme First Fit Randomly");
        System.out.println("4. Solution initiale avec l'algorithme First Fit Increasing");
    }

    public void menuBinPacking() {
        System.out.println("Liste des fichiers BinPacking existants avec comme arguments [taille d'un bin; nombre d'items]");
        System.out.println("0. Bin Packing 0 -> [9; 24]");
        System.out.println("1. Bin Packing 1 -> [150; 250]");
        System.out.println("2. Bin Packing 2 -> [150; 500]");
        System.out.println("3. Bin Packing 3 -> [1000; 60]");
        System.out.println("4. Bin Packing 4 -> [1000; 120]");
        System.out.println("5. Bin Packing 5 -> [1000; 249]");
        System.out.println("6. Bin Packing 6 -> [1000; 501]");
        System.out.println("7. Bin Packing 7 -> [100; 50]");
        System.out.println("8. Bin Packing 8 -> [120; 50]");
        System.out.println("9. Bin Packing 9 -> [120; 500]");
        System.out.println("10. Bin Packing 10 -> [150; 500]");
        System.out.println("11. Bin Packing 11 -> [1000; 141]");
        System.out.println("12. Bin Packing 12 -> [1000; 160]");
    }

    public void choixBinPacking() throws IOException {
        int choix = -1;
        do {
            menuBinPacking();
            choix = scanner.nextInt();
            switch (choix) {
                case 0:
                    binPacking = DataConverter.convertFile("data/binpack1d_00.txt");
                    break;
                case 1:
                    binPacking = DataConverter.convertFile("data/binpack1d_01.txt");
                    break;
                case 2:
                    binPacking = DataConverter.convertFile("data/binpack1d_02.txt");
                    break;
                case 3:
                    binPacking = DataConverter.convertFile("data/binpack1d_03.txt");
                    break;
                case 4:
                    binPacking = DataConverter.convertFile("data/binpack1d_04.txt");
                    break;
                case 5:
                    binPacking = DataConverter.convertFile("data/binpack1d_05.txt");
                    break;
                case 6:
                    binPacking = DataConverter.convertFile("data/binpack1d_06.txt");
                    break;
                case 7:
                    binPacking = DataConverter.convertFile("data/binpack1d_11.txt");
                    break;
                case 8:
                    binPacking = DataConverter.convertFile("data/binpack1d_12.txt");
                    break;
                case 9:
                    binPacking = DataConverter.convertFile("data/binpack1d_13.txt");
                    break;
                case 10:
                    binPacking = DataConverter.convertFile("data/binpack1d_14.txt");
                    break;
                case 11:
                    binPacking = DataConverter.convertFile("data/binpack1d_21.txt");
                    break;
                case 12:
                    binPacking = DataConverter.convertFile("data/binpack1d_31.txt");
                    break;
                default:
                    System.out.println("ERREUR : le numéro n'est pas correct ! Veuillez recommencer");
            }
        } while (choix < 0 || choix > 12);
        System.out.println("Conversion du fichier avec succès !\n");
    }

    public void choixMenu2() throws IOException {
        int choix;
        do {
            menu2();
            choix = scanner.nextInt();
            choixBinPacking();
            switch (choix) {
                case 1:
                    System.out.println("----> First fit decreasing : ");
                    SolutionGenerator.firstFitDecreasing(binPacking);
                    affichageSolution();
                    break;
                case 2:
                    System.out.println("----> One item by bin : ");
                    SolutionGenerator.oneItemByBin(binPacking);
                    affichageSolution();
                    break;
                case 3:
                    System.out.println("----> First Fit Randomly: ");
                    SolutionGenerator.firstFitRandomly(binPacking);
                    affichageSolution();
                    break;
                case 4:
                    System.out.println("----> First Fit Increasing : ");
                    SolutionGenerator.firstFitIncreasing(binPacking);
                    affichageSolution();
                    break;
                default :
                    System.out.println("ERREUR : le numéro n'est pas correct ! Veuillez recommencer");
            }
        } while (choix < 1 || choix > 4);
    }

    public void tabou() throws IOException {
        int choixTaille;
        choixMenu2();
        System.out.println("Rentrez la taille de la liste Tabou");
        choixTaille = scanner.nextInt();

        List<NeighborhoodOperator> operators = new ArrayList<>();
        operators.add(NeighborhoodOperator.RELOCATE);
        operators.add(NeighborhoodOperator.EXCHANGE);
        Metaheuristic metaheuristic = new Metaheuristic(operators, 10, choixTaille);

        System.out.println("----> Recherche Tabou : ");
        BinPacking solutionTabuSearch = metaheuristic.tabuSearch(binPacking, true);
        System.out.println(solutionTabuSearch.toStringBins());
        System.out.println("Nombre de bins : " + solutionTabuSearch.getSolutionCount());
        System.out.println("Fitness : " + solutionTabuSearch.getFitness() + "\n");
    }

    public void recuitSimule() throws IOException {
        int choixTemp;
        double choixCoefTemp;
        choixMenu2();

        do {
            System.out.println("Choisir une température (>1): ");
            choixTemp = scanner.nextInt();
        } while (choixTemp < 1);

        do {
            System.out.println("Choisir un coefficient de décroissance de la température (compris entre 0 et 1, et séparé d'une virgule): ");
            choixCoefTemp = scanner.nextDouble();
        } while (choixCoefTemp < 0 || choixCoefTemp > 1);

        List<NeighborhoodOperator> operators = new ArrayList<>();
        operators.add(NeighborhoodOperator.RELOCATE);
        operators.add(NeighborhoodOperator.EXCHANGE);
        Metaheuristic metaheuristic = new Metaheuristic(operators, 10, 2);

        System.out.println("----> Recherche Recuit Simulé : ");
        BinPacking solutionSimulatedAnneling = metaheuristic.simulatedAnnealing(binPacking, choixTemp, choixCoefTemp);
        System.out.println(solutionSimulatedAnneling.toStringBins());
        System.out.println("Nombre de bins : " + solutionSimulatedAnneling.getSolutionCount());
        System.out.println("Fitness : " + solutionSimulatedAnneling.getFitness() + "\n");
    }

    public void affichageSolution() {
        System.out.println(binPacking.toStringBins());
        System.out.println("Nombre de bins : " + binPacking.getSolutionCount());
        System.out.println("Fitness : " + binPacking.getFitness() + "\n");
    }
}
