import java.util.Optional;

/**
 * Classe qui représente un item
 */
public class Item implements Comparable<Item> {

    private int size;
    private boolean used;
    private Optional<Bin> bin;

    public Item(int size) {
        this.size = size;
        this.used = false;
        this.bin = Optional.empty();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public Optional<Bin> getBin() {
        return bin;
    }

    public void setBin(Optional<Bin> bin) {
        this.bin = bin;
    }

    public String toString() {
        return String.valueOf(size);
    }


    @Override
    public int compareTo(Item o) {
        return Integer.compare(size, o.size);
    }
}
