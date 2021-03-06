import java.util.List;

/**
 * Classe qui permet de générer des solutions alétaoires de BinPacking
 */
public class SolutionGenerator {

    private static List<Item> items;
    private static List<Bin> bins;
    private static int binCapacity;

    /**
     * Méthode qui permet de générer la solution créant autant de bin
     * et mettant un item par bin
     *
     * @param binPacking le binPacking
     */
    public static void oneItemByBin(BinPacking binPacking) {
        setUpElements(binPacking);
        for (Item item : items) {
            Bin newBin = new Bin(binCapacity);
            newBin.addItem(item);
            bins.add(newBin);
        }
    }

    /**
     * Méthode qui initialise les informations relatives au binPacking
     *
     * @param binPacking le binPacking
     */
    private static void setUpElements(BinPacking binPacking) {
        items = binPacking.getItems();
        bins = binPacking.getBins();
        binCapacity = binPacking.getBinCapacity();
    }

    /**
     * Méthode qui permet de générer la solution firstFit avec un ordre aléatoire des items.
     *
     * @param binPacking le binPacking
     */
    public static void firstFitRandomly(BinPacking binPacking) {
        setUpElements(binPacking);
        // Ordonne les items aléatoirement
        binPacking.shuffleItems();

        // Insertion dans des bins avec la méthode FirstFit
        firstFit();
    }

    /**
     * Méthode qui permet de générer la solution firstFit avec les items
     * classés selon un ordre décroissant de leur taille.
     *
     * @param binPacking le binPacking
     */
    public static void firstFitDecreasing(BinPacking binPacking) {
        setUpElements(binPacking);

        // Ordonne les items dans l'ordre décroissant
        binPacking.sortItemsDecreasing();

        // Insertion dans des bins avec la méthode FirstFit
        firstFit();
    }

    /**
     * Méthode privée qui essaye d'insérer les items selon un ordre donné, dans les bins déjà présents du premier au dernier.
     * Elle ajoute un bin si l’item ne tient dans aucun bin existant.
     */
    private static void firstFit() {
        // Insertion des bins avec la méthode FirstFit
        Bin binCurrent = new Bin(binCapacity);
        for (Item item : items) {
            if (bins.size() == 0 || binCurrent.getFreeSize() < item.getSize()) {
                Bin bin = new Bin(binCapacity);
                bin.addItem(item);
                bins.add(bin);
                binCurrent = bin;
            } else if (binCurrent.getFreeSize() >= item.getSize()) {
                binCurrent.addItem(item);
            }
        }
    }
}
