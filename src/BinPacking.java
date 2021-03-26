import java.util.*;

/**
 * Classe qui représente un BinPacking avec ses bins et ses items
 */
public class BinPacking implements Cloneable {

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

    public String toString() {
        return toStringBins();
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
     * Méthode qui permet de classer les items selon un ordre aléatoire
     */
    public void shuffleItems() {
        Collections.shuffle(items);
    }

    public void removeBin(Bin bin) {
        bins.remove(bin);
    }

    @Override
    protected BinPacking clone() {
        BinPacking clone = null;
        try {
            clone = (BinPacking) super.clone();
            // On clone les items
            List<Item> i = new ArrayList<>();

            // On clone les bins
            List<Bin> b = new ArrayList<>();
            for (Bin bin : bins) {
                Bin cloneBin = new Bin(bin.getSize());
                for (Item item : bin.getItems()) {
                    // On ajoute les bons items au bon bins, puis les ajoute à la liste des items clonés
                    Item cloneItem = new Item(item.getSize());
                    i.add(cloneItem);
                    cloneBin.addItem(cloneItem);
                    cloneItem.setBin(Optional.of(cloneBin));
                }
                b.add(cloneBin);
            }
            clone.items = i;
            clone.bins = b;
            clone.binCapacity = binCapacity;
            clone.nbItem = nbItem;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }

    /**
     * Compare deux bins selon les valeurs des items contenus dans chacun des bins, et non leur adresse
     *
     * @param binPacking le binPacking à comparer
     * @return true s'ils ont autant de bins et composés de la même manière, false sinon
     */
    public boolean isIdenticTo(BinPacking binPacking) {
        if (this.getBins().size() == binPacking.getBins().size()) {
            // Ils ont autant de bins: on les parcourt
            for (int i = 0; i < this.getBins().size(); i++) {
                Bin bin1 = this.getBins().get(i);
                Bin bin2 = binPacking.getBins().get(i);
                if (bin1.getFreeSize() == bin2.getFreeSize() && bin1.getItems().size() == bin2.getItems().size()) {
                    // Ils ont autant d'items et de taille libre
                    // On vérifie les valeurs des items
                    for (int j = 0; j < bin1.getItems().size(); j++) {
                        Item item1 = bin1.getItems().get(j);
                        Item item2 = bin2.getItems().get(j);
                        if (item1.getSize() == item2.getSize()) {
                            // Si c'est le tout dernier item du dernier bin, on retourne true
                            if (i == getBins().size() - 1 && j == bin1.getItems().size() - 1) {
                                return true;
                            }
                        } else {
                            return false;
                        }
                    }
                } else {
                    return false;
                }
            }
        }
        return false;
    }
}
