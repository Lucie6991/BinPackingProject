import java.util.*;
import java.util.stream.Collectors;

/**
 * Classe qui représente un BinPacking avec ses bins et ses items
 */
public class BinPacking implements Cloneable {

    private List<Bin> bins;
    private List<Item> items;
    private int binCapacity;
    private int nbItem;

    /**
     * La liste des transformations élémentaires utilisées, en tant qu'Optional
     */
    private Optional<List<Map<NeighborhoodOperator, Integer[]>>> elementaryTransformationListOpt;
    private int fitness;

    public BinPacking(int binCapacity, int nbItem, List<Integer> itemsSizes) {
        this.binCapacity = binCapacity;
        this.nbItem = nbItem;
        this.items = new ArrayList<>();
        this.bins = new ArrayList<>();
        for (int itemSize : itemsSizes) {
            items.add(new Item(itemSize));
        }
        this.elementaryTransformationListOpt = Optional.empty();
        setFitness();
    }

    public String toStringItems() {
        return items.toString();
    }

    /**
     * Affiche les bins en prenant en compte que ceux non vides
     *
     * @return l'affichage des bins non vides
     */
    public String toStringBins() {
        return getNotEmptyBins().toString();
    }

    public String toStringFitness() {
        return "Fitness : " + this.fitness;
    }

    public String toString() {
        return toStringBins() + "\n" + toStringFitness();

    }

    public List<Bin> getBins() {
        return bins;
    }

    public void setBins(List<Bin> bins) {
        this.bins = bins;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getBinCapacity() {
        return binCapacity;
    }

    public void setBinCapacity(int binCapacity) {
        this.binCapacity = binCapacity;
    }

    public int getNbItem() {
        return nbItem;
    }

    public void setNbItem(int nbItem) {
        this.nbItem = nbItem;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness() {
        this.fitness = getBins().stream()
                .mapToInt(b -> (int) Math.pow(binCapacity - b.getFreeSize(), 2))
                .sum();
    }

    /**
     * Permet de récupérer les bins non vides
     *
     * @return les bins non vides
     */
    private List<Bin> getNotEmptyBins() {
        return bins.stream().filter(b -> !b.getItems().isEmpty()).collect(Collectors.toList());
    }

    /**
     * Méthode qui permet de calculer une borne inférieure (nombre minimum) du nombre de bins
     *
     * @return le nombre de bin minimum
     */
    public int lowerBound() {
        double total = 0.0;
        for (Item item : items) {
            total += item.getSize();
        }
        return (int) Math.ceil(total / binCapacity);
    }

    /**
     * Méthode qui permet de classer les items dans le sens décroissant de leur taille
     */
    public void sortItemsDecreasing() {
        items.sort(Comparator.reverseOrder());
    }

    /**
     * Méthode qui permet de classer les items dans le sens croissant de leur taille
     */
    public void sortItemsIncreasing() {
        items.sort(Comparator.naturalOrder());
    }

    /**
     * Méthode qui permet de classer les items selon un ordre aléatoire
     */
    public void shuffleItems() {
        Collections.shuffle(items);
    }

    /**
     * Permet d'obtenir la solution c'est-à-dire le nombre de bins utilisés
     * @return le nombre de bins utilisés
     */
    public int getSolutionCount() {
        return getNotEmptyBins().size();
    }

    public int getUsedSpace() {
        return getBins().stream()
                .mapToInt(b -> binCapacity - b.getFreeSize())
                .sum();
    }

    public int getFreeSpace() {
        return binCapacity * getSolutionCount() - getUsedSpace();
    }

    public int getUsedPercent() {
        return (int) ((getUsedSpace() / (double) (binCapacity * getNotEmptyBins().size())) * 100);
    }

    @Override
    protected BinPacking clone() {
        BinPacking clone = null;
        try {
            clone = (BinPacking) super.clone();
            // On clone les items
            List<Item> i = new ArrayList<>();
            items.forEach(item -> i.add(new Item(item.getSize())));

            List<Bin> b = new ArrayList<>();
            for (Bin bin : bins) {
                Bin cloneBin = new Bin(bin.getSize());
                for (Item item : bin.getItems()) {
                    // Récupère l'index de l'item
                    int indexItem = items.indexOf(item);
                    // Récupère l'item dans la nouvelle liste des items
                    Item itemClone = i.get(indexItem);
                    // Ajoute cet item au nouveau bin
                    cloneBin.addItem(itemClone);
                    itemClone.setBin(Optional.of(cloneBin));
                }
                b.add(cloneBin);
            }

            clone.items = i;
            clone.bins = b;
            clone.binCapacity = binCapacity;
            clone.nbItem = nbItem;
            clone.elementaryTransformationListOpt = Optional.empty();

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }

    /**
     * Permet de récupérer la liste des transformations élémentaires
     *
     * @return la liste des transformations élémentaires en tant qu'Optional
     */
    public Optional<List<Map<NeighborhoodOperator, Integer[]>>> getElementaryTransformationListOpt() {
        return elementaryTransformationListOpt;
    }

    /**
     * Permet d'ajouter des transformations élémentaires à la liste de ses transformations,
     * ou de créer la liste en la remplissant si elle n'existe pas
     *
     * @param operator   l'opérateur de voisinage utilisé
     * @param parameters les paramètres utilisés pour ce voisinage
     */
    public void addElementaryTransformation(NeighborhoodOperator operator, Integer[] parameters) {
        List<Map<NeighborhoodOperator, Integer[]>> listOfMap = elementaryTransformationListOpt.isEmpty() ? new ArrayList<>() : elementaryTransformationListOpt.get();
        HashMap<NeighborhoodOperator, Integer[]> map = new HashMap<>();
        map.put(operator, parameters);
        listOfMap.add(map);
        this.elementaryTransformationListOpt = Optional.of(listOfMap);
    }
}
