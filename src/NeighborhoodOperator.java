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
    public static Optional<BinPacking> relocateItem(BinPacking binPacking, int itemIndex, int binIndex, boolean symmetricalElementaryTransformations) {
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
                    // Si le bin n'a plus d'items, on ne le supprime pas de la liste, mais sa taille restante est sa capacité max.
                    // On lui met sa transformation inverse de celle réalisée => Item dans son bin d'origine
                    binPacking.addElementaryTransformation(NeighborhoodOperator.RELOCATE, new Integer[] {itemIndex, binPacking.getBins().indexOf(binItem)});
                    if (symmetricalElementaryTransformations) {
                        // Si on veut les transformations symétriques, alors on empêche la transformation élémentaire que l'on vient de faire
                        binPacking.addElementaryTransformation(NeighborhoodOperator.RELOCATE, new Integer[] {itemIndex, binPacking.getBins().indexOf(bin)});
                    }
                });
                item.setBin(Optional.of(bin));

                //Calcul de la nouvelle fitness
                binPacking.setFitness();
                return Optional.of(binPacking);
            }
        // TODO : regarder si ajout d'un bin si pas la place dans la destination peut être intéressant

//            else {
//                // Si le bin n'a pas la place alors on en crée un nouveau
//                Bin newBin = new Bin(bin.getSize());
//                newBin.addItem(item);
//                binPacking.getBins().add(newBin);
//                binOpt.ifPresent(binItem -> {
//                    binItem.removeItem(item);
//                    // Si le bin n'a plus d'items, on ne le supprime pas de la liste, mais sa taille restante est sa capacité max.
//                    // On lui met sa transformation inverse de celle réalisée => Item dans son bin d'origine
//                    binPacking.addElementaryTransformation(NeighborhoodOperator.RELOCATE, new Integer[] {itemIndex, binPacking.getBins().indexOf(binItem)});
//                    if (symmetricalElementaryTransformations) {
//                        // Si on veut les transformations symétriques, alors on empêche la transformation élémentaire que l'on vient de faire
//                        binPacking.addElementaryTransformation(NeighborhoodOperator.RELOCATE, new Integer[] {itemIndex, binPacking.getBins().indexOf(newBin)});
//                    }
//                });
//                item.setBin(Optional.of(newBin));
//
//                //Calcul de la nouvelle fitness
//                binPacking.setFitness();
//                return Optional.of(binPacking);
//
//            }
        }
        // Il n'y a pas eu de voisinage créé
        return Optional.empty();
    }

    /**
     * Méthode représentant l'opérateur de voisinage qui échange deux items
     *
     * @param binPacking le binPacking à modifier
     * @param itemIndex1  l'index de l'item source à déplacer
     * @param itemIndex2  l'index de l'item 2 à déplacer
     * @return un Optional du binPacking modifié, ou un Optional vide, s'il n'a pas été modifié
     */
    public static Optional<BinPacking> exchangeItems(BinPacking binPacking, int itemIndex1, int itemIndex2, boolean symmetricalElementaryTransformations) {
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
                // On applique le changement en gardant les places
                int indexItem1 = bin1.getItems().indexOf(item1);
                int indexItem2 = bin2.getItems().indexOf(item2);
                bin1.removeItem(item1);
                bin2.addItem(item1, indexItem2);
                item1.setBin(Optional.of(bin2));
                bin2.removeItem(item2);
                bin1.addItem(item2, indexItem1);
                item2.setBin(Optional.of(bin1));

                //Calcul de la nouvelle fitness
                binPacking.setFitness();
                // On lui met sa transformation inverse de celle réalisée => Item1 avec l'item2 en partant du bin 2 au bin 1
                binPacking.addElementaryTransformation(NeighborhoodOperator.EXCHANGE, new Integer[] {itemIndex1, itemIndex2, binPacking.getBins().indexOf(bin2), binPacking.getBins().indexOf(bin1)});
                // TODO : a voir si on lui met aussi dans le meme ordre
                if (symmetricalElementaryTransformations) {
                    // Si on veut les transformations symétriques, alors on empêche la transformation élémentaire que l'on vient de faire
                    binPacking.addElementaryTransformation(NeighborhoodOperator.EXCHANGE, new Integer[] {itemIndex1, itemIndex2, binPacking.getBins().indexOf(bin1), binPacking.getBins().indexOf(bin2)});
                }
                return Optional.of(binPacking);
            }
        }
        // Il n'y a pas eu de voisinage créé
        return Optional.empty();
    }
}
