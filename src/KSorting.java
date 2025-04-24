/* 
import java.util.Arrays;
import java.util.Random;

public class KSorting {
    public static void generateKSorted(int[] array, int k) {
        Arrays.sort(array);
        Random random = new Random();

        for (int i = 0; i < array.length; i++) {
            int j = Math.min(i + random.nextInt(k + 1), array.length - 1);
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    public static void main(String[] args) {
        int size = 20;
        int k = 5;
        int[] arr = new int[size];

        // Fill array with sorted values
        for (int i = 0; i < size; i++) {
            arr[i] = i + 1;
        }

        System.out.println("Original sorted array: " + Arrays.toString(arr));

        generateKSorted(arr, k);

        System.out.println("K-sorted array: " + Arrays.toString(arr));
    }

}
*/
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Arrays;

class KSortedPerformance {
    private static final int[] SIZES = {100, 500, 1000, 2000, 5000, 10000, 20000, 75000, 150000};
    private static final int ITERATIONS = 20;

    public static void main(String[] args) {
        SortingAlgorithm[] algorithms = {
                new BubbleSort(),
                new InsertionSort(),
                new SelectionSort(),
                new ShellSort(),
                new MergeSort(),
                new QuickSort()
        };

        try (FileWriter writer = new FileWriter("k_sorted_performance_report.txt")) {
            System.out.println("Testing sorting algorithms with 10-sorted data...");

            for (SortingAlgorithm sa : algorithms) {
                writer.write("Sorting Algorithm – " + sa.getClass().getSimpleName() + "\n");
                System.out.println("\nTesting " + sa.getClass().getSimpleName() + "...");

                for (int size : SIZES) {
                    double avgTime = runPerformanceTest(sa, size);
                    String result = String.format("Sorted %d elements in %.3f ms (avg)\n", size, avgTime);

                    writer.write(result);
                    System.out.print(result);
                }

                writer.write("\n");
            }

            System.out.println("\nPerformance testing completed! Results saved to k_sorted_performance_report.txt");

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private static double runPerformanceTest(SortingAlgorithm sa, int size) {
        double totalTime = 0;
        Random rand = new Random();

        for (int i = 0; i < ITERATIONS; i++) {
            int[] baseArray = generateKSorted(size, rand);
            int[] arr = baseArray.clone(); // Clone for fairness
            long startTime = System.nanoTime();
            sa.sorty(arr);
            long endTime = System.nanoTime();
            totalTime += (endTime - startTime) / 1_000_000.0; // Convert to milliseconds
        }

        return totalTime / ITERATIONS;
    }

    private static int[] generateKSorted(int size, Random rand) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i + 1;
        }

        int k = 10;
        for (int i = 0; i < size; i++) {
            int minPos = Math.max(0, i - k);
            int maxPos = Math.min(size - 1, i + k);
            int j = minPos + rand.nextInt(maxPos - minPos + 1);
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        return arr;
    }


}
