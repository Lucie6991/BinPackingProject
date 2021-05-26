import java.util.*;

import static java.lang.Math.min;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class Metaheuristic {

    private final List<NeighborhoodOperator> neighborhoodsOperators;
    private final int maxIter;
    private final int tabuListSize;
    private boolean symmetricalElementaryTransformations;

    public Metaheuristic(List<NeighborhoodOperator> neighborhoodsOperators, int maxIter, int tabuListSize) {
        this.neighborhoodsOperators = neighborhoodsOperators;
        this.maxIter = maxIter;
        this.tabuListSize = tabuListSize;
    }

    public void simulatedAnnealing(BinPacking initialSolution) {
        // TODO : RECUIT SIMULE
    }

    /**
     * Représente la méthode de recherche Tabou avec une taille de la liste Tabou fixée, et un nombre maximum d'itération
     *
     * @param initialSolution la solution de départ
     * @return la solution minimale trouvée une fois le nombre maximum d'itération réalisé.
     */
    public BinPacking tabuSearch(BinPacking initialSolution, boolean symmetricalElementaryTransformations) {
        // Initialisation des variables
        this.symmetricalElementaryTransformations = symmetricalElementaryTransformations;
        List<Map<NeighborhoodOperator, Integer[]>> T = new ArrayList<>();
        BinPacking solutionMin = initialSolution;
        int nbBinsMin = initialSolution.getBins().size();
        BinPacking actualBin = initialSolution;
        BinPacking nextBin;
        for (int i = 0; i < maxIter; i++) {
            List<BinPacking> C = getNeighborhoodWithoutExceptions(actualBin, T);
            // On enlève ceux qui sont dans la TabuList (en comparant les valeurs et non les adresses)
            if (C.isEmpty()) {
                // S'il n'a pas de voisin, c'est fini on retourne la meilleure solution
                return solutionMin;
            }
            // On choisi le nextBin avec le max de fitness
            nextBin = C.stream()
                    .max(comparing(BinPacking::getFitness))
                    .get();

            // delta est la différence de fitness
            int delta = nextBin.getFitness() - actualBin.getFitness();
            if (delta >= 0) {
                // On l'ajoute à la TabuList, mais si elle est remplie, on supprime le plus ancien
                // (suivant la règle des transformations élémentaires symétriques)
                if (!symmetricalElementaryTransformations && T.size() == tabuListSize) {
                    T.remove(0);
                }
                else if (symmetricalElementaryTransformations && T.size() == tabuListSize * 2) {
                    T.remove(0);
                    T.remove(0);
                }
                T.addAll(nextBin.getElementaryTransformationListOpt().get());
            }
            // Mise à jour du minimum en maximisant la fitness
            solutionMin = nextBin.getFitness() > nbBinsMin ? nextBin : solutionMin;
            nbBinsMin = min(nextBin.getFitness(), nbBinsMin);
            // On recommence avec le nouveau bin
            actualBin = nextBin;
        }
        return solutionMin;
    }

    /**
     * Méthode qui permet de récupérer toutes les solutions voisine à une solution donnée.
     * L'opérateur de voisinage est choisi aléatoirement dans la liste des opérateurs.
     * Les solutions utilisant les transformations données en exceptions ne sont pas créés
     *
     * @param solution la solution
     * @param T        la liste des exceptions. Liste remplie dans le cas de la méthode Tabou
     * @return la liste de solutions voisines
     */
    private List<BinPacking> getNeighborhoodWithoutExceptions(BinPacking solution, List<Map<NeighborhoodOperator, Integer[]>> T) {
        List<BinPacking> neighbours = new ArrayList<>();
        // On choisit un opérateur de voisinage au hasard
        Random random = new Random();
        NeighborhoodOperator operator = neighborhoodsOperators.get(random.nextInt(neighborhoodsOperators.size()));
        if (operator.equals(NeighborhoodOperator.RELOCATE)) {
            List<Integer[]> exceptions = T.stream().filter(map -> map.containsKey(NeighborhoodOperator.RELOCATE)).flatMap(map -> map.values().stream()).collect(toList());
            neighbours = getNeighborhoodRelocate(solution, exceptions);
        } else if (operator.equals(NeighborhoodOperator.EXCHANGE)) {
            List<Integer[]> exceptions = T.stream().filter(map -> map.containsKey(NeighborhoodOperator.EXCHANGE)).flatMap(map -> map.values().stream()).collect(toList());
            neighbours = getNeighborhoodExchange(solution, exceptions);
        }
        return neighbours;
    }

    /**
     * Méthode qui récupère le voisinage d'une solution avec l'opérateur Relocate
     *
     * @param solution   la solution
     * @param exceptions la liste des transformations à ne pas générer
     * @return la liste des voisins avec la méthode Relocate.
     */
    private List<BinPacking> getNeighborhoodRelocate(BinPacking solution, List<Integer[]> exceptions) {
        List<BinPacking> neighbours = new ArrayList<>();
        BinPacking neighbour;
        for (int itemIndex = 0; itemIndex < solution.getItems().size(); itemIndex++) {
            for (int binIndex = 0; binIndex < solution.getBins().size(); binIndex++) {
                // Si la transformation n'est pas dans les exceptions
                int finalItemIndex = itemIndex;
                int finalBinIndex = binIndex;
                if (exceptions.stream().noneMatch(e -> e[0] == finalItemIndex && e[1] == finalBinIndex)) {
                    // On copie le binPacking pour lui appliquer la méthode relocate
                    neighbour = solution.clone();
                    Optional<BinPacking> binPackingOpt = NeighborhoodOperator.relocateItem(neighbour, itemIndex, binIndex, symmetricalElementaryTransformations);
                    binPackingOpt.ifPresent(neighbours::add);
                }
            }
        }
        return neighbours;
    }

    /**
     * Méthode qui récupère le voisinage d'une solution avec l'opérateur Exchange
     *
     * @param solution   la solution
     * @param exceptions la liste des transformations à ne pas générer
     * @return la liste des voisins avec la méthode Exchange.
     */
    private List<BinPacking> getNeighborhoodExchange(BinPacking solution, List<Integer[]> exceptions) {
        BinPacking neighbour;
        List<BinPacking> neighbours = new ArrayList<>();
        for (int itemIndex1 = 0; itemIndex1 < solution.getItems().size(); itemIndex1++) {
            for (int itemIndex2 = itemIndex1; itemIndex2 < solution.getItems().size(); itemIndex2++) {
                int finalItemIndex1 = itemIndex1;
                int finalItemIndex2 = itemIndex2;
                if (exceptions.stream().noneMatch(e -> {
                    int itemSource = e[0];
                    int itemTarget = e[1];
                    int binSource = e[2];
                    int binTarget = e[3];
                    // On vérifie si on la transformation n'est pas parmi celles en exception
                    return finalItemIndex1 == itemSource &&
                            finalItemIndex2 == itemTarget &&
                            solution.getItems().get(finalItemIndex1).getBin().isPresent() &&
                            solution.getItems().get(finalItemIndex1).getBin().get() == solution.getBins().get(binSource) &&
                            solution.getItems().get(finalItemIndex2).getBin().isPresent() &&
                            solution.getItems().get(finalItemIndex2).getBin().get() == solution.getBins().get(binTarget);
                })) {
                    // On copie le binPacking pour lui appliquer la méthode relocate
                    neighbour = solution.clone();
                    Optional<BinPacking> binPackingOpt = NeighborhoodOperator.exchangeItems(neighbour, itemIndex1, itemIndex2, symmetricalElementaryTransformations);
                    binPackingOpt.ifPresent(neighbours::add);
                }
            }
        }
        return neighbours;
    }
}
