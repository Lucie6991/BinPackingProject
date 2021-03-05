import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Classe qui représente un BinPacking avec ses bins et ses items
 */
public class BinPacking {

    private List<Bin> bins;
    private List<Item> items;
    private int binCapacity;
    private int nbItem;
    private List<Boolean> usedBin;

    public BinPacking(int binCapacity, int nbItem, List<Integer> itemsSizes) {
        this.binCapacity = binCapacity;
        this.nbItem = nbItem;
        this.items = new ArrayList<>();
        this.bins = new ArrayList<>();
        this.usedBin = new ArrayList<>();
        for (int itemSize : itemsSizes) {
            items.add(new Item(itemSize));
        }
    }

    public String toStringItems() {
        return items.toString();
    }

    public String toStringBins() {
        return bins.toString();
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

    public List<Boolean> getUsedBin() {
        return usedBin;
    }

    public void setUsedBin(List<Boolean> usedBin) {
        this.usedBin = usedBin;
    }

    /**
     * Méthode qui permet de classer les items dans le sens décroissant de leur taille
     */
    public void sortItemsDecreasing() {
        items.sort(Comparator.reverseOrder());
    }

    /**
     * Méthode qui permet de classer les items selon un ordre aléatoire
     */
    public void shuffleItems() {
        Collections.shuffle(items);
    }
}
