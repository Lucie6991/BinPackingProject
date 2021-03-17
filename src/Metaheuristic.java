import java.util.*;

public class Metaheuristic {

    private final List<NeighborhoodOperator> neighborhoodsOperators;

    public Metaheuristic(List<NeighborhoodOperator> neighborhoodsOperators) {
        this.neighborhoodsOperators = neighborhoodsOperators;
    }

    public void simulatedAnnealing(BinPacking initialSolution) {
        // TODO : RECUIT SIMULE
    }

    public void tabuSearch(BinPacking initialSolution) {
        // TODO : METHODE TABOU
        System.out.println(Arrays.toString(getNeighborhood(initialSolution).toArray()));
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
                Optional<BinPacking> binPackingOpt = binPackingOpt = NeighborhoodOperator.relocateItem(neighbour, itemIndex, binIndex);
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
