import java.util.ArrayList;
import java.util.List;

public class Bin {

    private int freeSize;
    private int size;
    private List<Item> items;

    public Bin(int size) {
        this.size = size;
        this.freeSize = size;
        this.items = new ArrayList<>();
    }

    public int getFreeSize() {
        return freeSize;
    }

    public void setFreeSize(int freeSize) {
        this.freeSize = freeSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
