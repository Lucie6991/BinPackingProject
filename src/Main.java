import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        String filename = "binpack1d_001.txt";
        BufferedReader br = new BufferedReader(new FileReader(filename));
        br.readLine();
        String line = br.readLine();
        String[] parameters = line.split(" ");
        int binCapacity = Integer.parseInt(parameters[0]);
        int numberOfItem = Integer.parseInt(parameters[1]);

        List<Integer> itemsSizes = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            itemsSizes.add(Integer.parseInt(line));
        }

        BinPacking binPacking = new BinPacking(binCapacity, numberOfItem, itemsSizes);

        System.out.println(binPacking.toString());
        System.out.println("Capacity " + binCapacity);
    }
}
