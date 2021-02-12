import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public String toString() {
        return items.toString();
    }
}
