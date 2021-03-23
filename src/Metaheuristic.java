import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static java.lang.Math.min;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class Metaheuristic {

    private final List<NeighborhoodOperator> neighborhoodsOperators;
    private final int maxIter;
    private final int tabuListSize;

    public Metaheuristic(List<NeighborhoodOperator> neighborhoodsOperators, int maxIter, int tabuListSize) {
        this.neighborhoodsOperators = neighborhoodsOperators;
        this.maxIter = maxIter;
        this.tabuListSize = tabuListSize;
    }

    public void simulatedAnnealing(BinPacking initialSolution) {
        // TODO : RECUIT SIMULE
    }

    /**
     * Représente la méthode de recherche Tabou avec une taille de la liste Tabou fixée, et un nombre maxiumum d'itération
     *
     * @param initialSolution la solution de départ
     * @return la solution minimale trouvée une fois le nombre maximum d'itération réalisé.
     */
    public BinPacking tabuSearch(BinPacking initialSolution) {
        BinPacking solutionMin = initialSolution;
        int nbBinsMin = initialSolution.getBins().size();
        List<BinPacking> tabuList = new ArrayList<>();
        BinPacking actualBin = initialSolution;
        BinPacking nextBin;
        for (int i = 0; i < maxIter; i++) {
            List<BinPacking> neighborhood = getNeighborhood(actualBin);
            // On enlève ceux qui sont dans la TabuList (en comparant les valeurs et non les adresses)
            List<BinPacking> C = neighborhood.stream().filter(neighbour -> {
                for (BinPacking m : tabuList) {
                    if (m.isIdenticTo(neighbour)) {
                        return false;
                    }
                }
                return true;
            }).collect(toList());
            if (C.isEmpty()) {
                // S'il n'a pas de voisins, c'est fini on retourne la meilleure solution
                return solutionMin;
            }
            nextBin = C.stream()
                    .min(comparing(bin -> bin.getBins().size()))
                    .get();

            int delta = nextBin.getBins().size() - actualBin.getBins().size();
            if (delta >= 0) {
                // On l'ajoute à la TabuList, mais si elle est remplie, on supprime le plus ancien.
                if (tabuList.size() == tabuListSize) {
                    tabuList.remove(0);
                }
                tabuList.add(actualBin);
            }
            // Mise à jour du minimum
            solutionMin = nextBin.getBins().size() < nbBinsMin ? nextBin : solutionMin;
            nbBinsMin = min(nextBin.getBins().size(), nbBinsMin);
            // On recommence avec le nouveau bin
            actualBin = nextBin;
        }
        return solutionMin;
    }

    /**
     * Méthode qui permet de récupérer toutes les solutions voisine à une solution donnée.
     * L'opérateur de voisinage est choisi aléatoirement dans la liste des opérateurs.
     *
     * @param solution la solution
     * @return la liste de solutions voisines
     */
    private List<BinPacking> getNeighborhood(BinPacking solution) {
        List<BinPacking> neighbours = new ArrayList<>();
        // On choisit un opérateur de voisinage au hasard
        Random random = new Random();
        NeighborhoodOperator operator = neighborhoodsOperators.get(random.nextInt(neighborhoodsOperators.size()));
        if (operator.equals(NeighborhoodOperator.RELOCATE)) {
            neighbours = getNeighborhoodRelocate(solution);
        } else if (operator.equals(NeighborhoodOperator.EXCHANGE)) {
            neighbours = getNeighborhoodExchange(solution);
        }
        return neighbours;
    }

    /**
     * Méthode qui récupère le voisinage d'une solution avec l'opérateur Relocate
     *
     * @param solution la solution
     * @return la liste des voisins avec la méthode Relocate.
     */
    private List<BinPacking> getNeighborhoodRelocate(BinPacking solution) {
        List<BinPacking> neighbours = new ArrayList<>();
        BinPacking neighbour;
        for (int itemIndex = 0; itemIndex < solution.getItems().size(); itemIndex++) {
            for (int binIndex = 0; binIndex < solution.getBins().size(); binIndex++) {
                // On copie le binPacking pour lui appliquer la méthode relocate
                neighbour = solution.clone();
                Optional<BinPacking> binPackingOpt = NeighborhoodOperator.relocateItem(neighbour, itemIndex, binIndex);
                binPackingOpt.ifPresent(neighbours::add);
            }
        }
        return neighbours;
    }

    /**
     * Méthode qui récupère le voisinage d'une solution avec l'opérateur Exchange
     *
     * @param solution la solution
     * @return la liste des voisins avec la méthode Exchange.
     */
    private List<BinPacking> getNeighborhoodExchange(BinPacking solution) {
        BinPacking neighbour;
        List<BinPacking> neighbours = new ArrayList<>();
        for (int itemIndex1 = 0; itemIndex1 < solution.getItems().size(); itemIndex1++) {
            for (int itemIndex2 = itemIndex1; itemIndex2 < solution.getItems().size(); itemIndex2++) {
                // On copie le binPacking pour lui appliquer la méthode relocate
                neighbour = solution.clone();
                Optional<BinPacking> binPackingOpt = NeighborhoodOperator.exchangeItems(neighbour, itemIndex1, itemIndex2);
                binPackingOpt.ifPresent(neighbours::add);
            }
        }
        return neighbours;
    }
}
