import java.util.*;

public class Metaheuristic {

    private final List<NeighbourhoodOperator> neighbourhoodsOperators;

    public Metaheuristic(List<NeighbourhoodOperator> neighbourhoodsOperators) {
        this.neighbourhoodsOperators = neighbourhoodsOperators;
    }

    public void simulatedAnnealing(BinPacking initialSolution) {
        System.out.println(Arrays.toString(getNeighbourhoods(initialSolution).toArray()));
    }

    /**
     * Méthode qui permet de récupérer tous les solutions voisine à une solution donnée
     *
     * @param solution la solution
     * @return une liste de solutions voisines
     */
    private List<BinPacking> getNeighbourhoods(BinPacking solution) {
        BinPacking neighbour;
        List<BinPacking> neighbours = new ArrayList<>();
        for (int itemIndex = 0; itemIndex < solution.getItems().size(); itemIndex++) {
            for (int binIndex = 0; binIndex < solution.getBins().size(); binIndex++) {
                // On choisit un opérateur de voisinage au hasard
                Random random = new Random();
                NeighbourhoodOperator operator = neighbourhoodsOperators.get(random.nextInt(neighbourhoodsOperators.size()));
                // On copie le binPacking pour lui appliquer la méthode relocate
                neighbour = solution.clone();
                if(operator.equals(NeighbourhoodOperator.RELOCATE)) {
                    Optional<BinPacking> binPackingOpt = NeighbourhoodOperator.relocateItem(neighbour, itemIndex, binIndex);
                    binPackingOpt.ifPresent(neighbours::add);
                }
            }
        }
        return neighbours;
    }
}
