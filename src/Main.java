import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        BinPacking binPacking1 = DataConverter.convertFile("data/binpack1d_00.txt");
        BinPacking binPacking2 = DataConverter.convertFile("data/binpack1d_01.txt");
        BinPacking binPacking3 = DataConverter.convertFile("data/binpack1d_01.txt");

//        System.out.println(binPacking.toStringItems());
//        System.out.println("Capacity " + binCapacity);

        //System.out.println(binPacking1.lowerBound());

        /*System.out.println("----> First fit decreasing : ");
        SolutionGenerator.firstFitDecreasing(binPacking1);
        System.out.println(binPacking1.toStringBins());*/

//        System.out.println("----> One item by bin : ");
//        SolutionGenerator.oneItemByBin(binPacking2);
//        System.out.println(binPacking2.toStringBins());

        /*System.out.println("----> First fit randomly : ");
        SolutionGenerator.firstFitRandomly(binPacking3);
        System.out.println(binPacking3.toStringBins());*/

        LinearProgram linearProgram = new LinearProgram(binPacking1);
        linearProgram.run();
    }
}
