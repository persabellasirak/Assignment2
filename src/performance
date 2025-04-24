import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

class Performance {
    private static final int[] SIZES = {100, 500, 1000, 2000, 5000, 10000, 20000, 75000, 150000};
    private static final int ITERATIONS = 20;

    public static void main(String[] args) {
        SortingAlgorithm[] algorithms = {
                new BubbleSort(),
                new InsertionSort(),
                new SelectionSort(),
                new ShellSort(),
                new QuickSort(),
                new MergeSort()
        };

        try (FileWriter writer = new FileWriter("performance_report.txt")) {
            System.out.println("Performance testing started...");

            for (SortingAlgorithm sa : algorithms) {
                writer.write("Sorting Algorithm – " + sa.getClass().getSimpleName() + "\n");
                System.out.println("\nTesting " + sa.getClass().getSimpleName() + "...");

                for (int size : SIZES) {
                    double avgTime = runPerformanceTest(sa, size);
                    String result = String.format("Sorted %d elements in %.3f ms (avg)\n", size, avgTime);

                    writer.write(result);
                    System.out.print(result);
                }

                writer.write("\n"); // Add spacing between algorithms
            }

            System.out.println("\nPerformance testing completed! Results saved to performance_report.txt");

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Runs multiple tests and returns the average execution time
    private static double runPerformanceTest(SortingAlgorithm sa, int size) {
        double totalTime = 0;
        Random rand = new Random();

        for (int i = 0; i < ITERATIONS; i++) {
            int[] baseArray = generateRandomArray(size, rand); // Generate base array

            int[] arr = baseArray.clone(); // Clone for fairness
            long startTime = System.nanoTime();
            sa.sorty(arr);
            long endTime = System.nanoTime();

            totalTime += (endTime - startTime) / 1e6; // Convert to milliseconds
        }

        return totalTime / ITERATIONS; // Return average time
    }

    // Generates a random array
    private static int[] generateRandomArray(int size, Random rand) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(10000); // Random numbers between 0-9999
        }
        return arr;
    }
}
