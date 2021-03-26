import java.util.List;

/**
 * Classe qui permet de générer des solutions alétoires de BinPacking
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
        //Calcul de la fitness associé à ce BinPacking
        binPacking.setFitness();
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

        //Calcul de la fitness associé à ce BinPacking
        binPacking.setFitness();
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

        //Calcul de la fitness associé à ce BinPacking
        binPacking.setFitness();
    }

    /**
     * Méthode privée qui essaye d'insérer les items selon un ordre donné, dans les bins déjà présents du premier au dernier.
     * Elle ajoute un bin si l’item ne tient dans aucun bin existant.
     */
    private static void firstFit() {
        // Insertion des bins avec la méthode FirstFit
        for (Item item : items) {
            boolean done = false;
            for (Bin bin : bins) {
                if (bin.getFreeSize() >= item.getSize()) {
                    bin.addItem(item);
                    done = true;
                    break;
                }
            }
            if (!done) {
                Bin binNew = new Bin(binCapacity);
                binNew.addItem(item);
                bins.add(binNew);
            }
        }
    }
}
