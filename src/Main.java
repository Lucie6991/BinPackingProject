import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        String filename = "data/binpack1d_01.txt";
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line = br.readLine();
        String[] parameters = line.split(" ");
        int binCapacity = Integer.parseInt(parameters[0]);
        int numberOfItem = Integer.parseInt(parameters[1]);

        List<Integer> itemsSizes = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            itemsSizes.add(Integer.parseInt(line));
        }

        BinPacking binPacking1 = new BinPacking(binCapacity, numberOfItem, itemsSizes);
        BinPacking binPacking2 = new BinPacking(binCapacity, numberOfItem, itemsSizes);
        BinPacking binPacking3 = new BinPacking(binCapacity, numberOfItem, itemsSizes);

//        System.out.println(binPacking.toStringItems());
//        System.out.println("Capacity " + binCapacity);
        System.out.println("----> First fit decreasing : ");
        SolutionGenerator.firstFitDecreasing(binPacking1);
        System.out.println(binPacking1.toStringBins());

        System.out.println("----> One item by bin : ");
        SolutionGenerator.oneItemByBin(binPacking2);
        System.out.println(binPacking2.toStringBins());

        System.out.println("----> First fit randomly : ");
        SolutionGenerator.firstFitRandomly(binPacking3);
        System.out.println(binPacking3.toStringBins());
    }
}
