import java.lang.reflect.Array;
import java.util.*;

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
        for (int itemSize : itemsSizes){
            items.add(new Item(itemSize));
        }
    }

    public void FirstFitDecreasing() {
        // Trie des items
        ArrayList<Item> itemsSorted = new ArrayList<>(items);
        Collections.sort(itemsSorted);

        //Placement
        Bin binCurrent = new Bin(binCapacity);
        for (Item item : itemsSorted) {
            if (bins.size() == 0 || binCurrent.getFreeSize() < item.getSize()) {
                Bin bin = new Bin(binCapacity);
                bin.getItems().add(item);
                bin.setFreeSize(binCapacity - item.getSize());
                bins.add(bin);
                binCurrent = bin;
            }
            else if (binCurrent.getFreeSize() >= item.getSize()) {
                binCurrent.getItems().add(item);
                binCurrent.setFreeSize(binCurrent.getFreeSize() - item.getSize());
            }
        }
    }

    public String toStringItems() {
        return items.toString();
    }

    public String toStringBins() {
        return bins.toString();
    }

}
