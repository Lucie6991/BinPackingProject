import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Classe qui représente un Bin
 */
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

    public List<Item> getItems() {
        return items;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : items)
            s.append(item.toString()).append(" ");
        return s.toString();
    }

    /**
     * Ajoute un item au bin, en mettant à jour sa capacité restante
     *
     * @param item l'item à ajouter
     */
    public void addItem(Item item) {
        items.add(item);
        setFreeSize(freeSize - item.getSize());
        item.setBin(Optional.of(this));
    }

    /**
     * Ajoute un item au bin à un index donné, en mettant à jour sa capacité restante
     *
     * @param item  l'item à ajouter
     * @param index l'index à être placé
     */
    public void addItem(Item item, int index) {
        items.add(index, item);
        item.setBin(Optional.of(this));
        setFreeSize(freeSize - item.getSize());
    }

    /**
     * Enlève un item au bin, en mettant à jour sa capacité restante.
     *
     * @param item l'item à enlever
     */
    public void removeItem(Item item) {
        items.remove(item);
        setFreeSize(freeSize + item.getSize());
        item.setBin(Optional.empty());
    }
}
