import java.util.Optional;

/**
 * Classe qui représente les opérateurs de voisinage
 */
public enum NeighborhoodOperator {
    RELOCATE, EXCHANGE;

    /**
     * Méthode représentant l'opérateur de voisinage qui déplace un item d'un bin vers un autre
     *
     * @param binPacking le binPacking à modifier
     * @param itemIndex  l'index de l'item à déplacer
     * @param binIndex   l'index du bin de destination
     * @return un Optional du binPacking modifié, ou un Optional vide, s'il n'a pas été modifié
     */
    public static Optional<BinPacking> relocateItem(BinPacking binPacking, int itemIndex, int binIndex) {
        Item item = binPacking.getItems().get(itemIndex);
        Bin bin = binPacking.getBins().get(binIndex);
        Optional<Bin> binOpt = item.getBin();
        // Si l'item n'a pas de bin, qu'on n'essaie pas d'échanger avec son propre bin :
        if (binOpt.isEmpty() || !binOpt.get().equals(bin)) {
            // On déplace l'item si le bin a la place
            if (bin.getFreeSize() >= item.getSize()) {
                bin.addItem(item);
                binOpt.ifPresent(binItem -> {
                    binItem.removeItem(item);
                    // Si le bin n'a plus d'items, on le supprime de la liste
                    if (binItem.getFreeSize() == binItem.getSize()) {
                        binPacking.removeBin(binItem);
                    }
                });
                item.setBin(Optional.of(bin));
                return Optional.of(binPacking);
            }
        }
        // Il n'y a pas eu de voisinage créé
        return Optional.empty();
    }

    public static Optional<BinPacking> exchangeItems(BinPacking binPacking, int itemIndex1, int itemIndex2) {
        Item item1 = binPacking.getItems().get(itemIndex1);
        Item item2 = binPacking.getItems().get(itemIndex2);
        Optional<Bin> binOpt1 = item1.getBin();
        Optional<Bin> binOpt2 = item2.getBin();
        if (binOpt1.isPresent() && binOpt2.isPresent() && !binOpt1.get().equals(binOpt2.get())) {
            // On n'essaie pas d'échanger avec le même bin
            Bin bin1 = binOpt1.get();
            Bin bin2 = binOpt2.get();
            // On vérifie si le changement va être possible en terme de tailles disponibles
            if(bin1.getFreeSize() + item1.getSize() >= item2.getSize() && bin2.getFreeSize() + item2.getSize() >= item1.getSize()) {
                // On applique le changement
                bin1.removeItem(item1);
                bin2.addItem(item1);
                item1.setBin(Optional.of(bin2));
                bin2.removeItem(item2);
                bin1.addItem(item2);
                item2.setBin(Optional.of(bin1));
                return Optional.of(binPacking);
            }
        }
        // Il n'y a pas eu de voisinage créé
        return Optional.empty();
    }
}
