import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui permet de convertir un fichier data en BinPacking
 */
public class DataConverter {

    /**
     * Méthode qui permet de convertir un fichier sous forme de BinPacking
     * avec la capcité maximale des bins, le nombre d'items, et la taille des items initialisés.
     *
     * @param filename le nom du fichier
     * @return le BinPacking
     * @throws IOException s'il y a une erreur à l'ouverture et lecture du fichier
     */
    public static BinPacking convertFile(String filename) throws IOException {
        // Lecture du fichier
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line = br.readLine();
        String[] parameters = line.split(" ");

        // Initialisation des paramètres
        int binCapacity = Integer.parseInt(parameters[0]);
        int numberOfItem = Integer.parseInt(parameters[1]);

        // Création des items
        List<Integer> itemsSizes = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            itemsSizes.add(Integer.parseInt(line));
        }

        return new BinPacking(binCapacity, numberOfItem, itemsSizes);
    }
}
