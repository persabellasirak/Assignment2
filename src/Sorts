import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

// Performance class for sorting comparison
class Runtimes {
    public static void main(String[] args) {
        int[] sizes = {100, 500, 1000, 2000, 5000, 10000, 20000, 75000, 150000};
        int iterations = 20;

        SortingAlgorithm[] algorithms = {
                new BubbleSort(),
                new InsertionSort(),
                new SelectionSort(),
                new ShellSort(),
                new QuickSort(),
                new MergeSort()
        };

        try (FileWriter writer = new FileWriter("sorting_performance_report.txt")) {
            for (SortingAlgorithm sa : algorithms) {
                writer.write("Sorting algorithm – " + sa.getClass().getSimpleName() + "\n");
                System.out.println("Testing " + sa.getClass().getSimpleName() + "...");

                for (int size : sizes) {
                    double totalTime = 0;

                    for (int i = 0; i < iterations; i++) {
                        // Generate random data (not timed)
                        int[] arr = generateRandomArray(size);

                        // Measure sorting time
                        long startTime = System.nanoTime();
                        sa.sorty(arr);
                        long endTime = System.nanoTime();

                        // Convert nanoseconds to milliseconds
                        totalTime += (endTime - startTime) / 1e6;
                    }

                    double avgTime = totalTime / iterations;
                    String result = "Sorted " + size + " elements in " + avgTime + " ms (avg)\n";
                    writer.write(result);
                    System.out.print(result);
                }
                writer.write("\n");
            }
            System.out.println("Performance report generated: sorting_performance_report.txt");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    // Generates a random array of given size
    private static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        Random rand = new Random();
        for (int j = 0; j < size; j++) {
            arr[j] = rand.nextInt(10000); // Random numbers between 0-9999
        }
        return arr;
    }
}
