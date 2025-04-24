import java.util.Random;

// Tester class for performance comparison
class Tester {
    private SortingAlgorithm sa;
    private Random rand;

    // Constructor
    public Tester(SortingAlgorithm sa) {
        this.sa = sa;
        this.rand = new Random();
    }

    // Runs a single test with a predefined random array
    public double singleTest(int size, int[] baseArray) {
        int[] arr = baseArray.clone(); // Clone to ensure same input for fairness
        long startTime = System.nanoTime();
        sa.sorty(arr);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1e6; // Convert to milliseconds
    }

    // Runs multiple tests and prints average execution time
    public double test(int iterations, int size) {
        double totalTime = 0;
        int[] baseArray = generateRandomArray(size); // Generate base array once

        for (int i = 0; i < iterations; i++) {
            totalTime += singleTest(size, baseArray);
        }

        return totalTime / iterations;
    }

    // Generates a random array of given size
    private int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(10000); // Random numbers 0-9999
        }
        return arr;
    }
}

// Main class to run performance tests
class Main {
    public static void main(String[] args) {
        int[] sizes = {100, 500, 1000, 5000, 10000, 20000, 50000}; // Array sizes to test
        int iterations = 5; // Number of iterations per sorting algorithm

        SortingAlgorithm[] algorithms = {
                new BubbleSort(),
                new InsertionSort(),
                new SelectionSort(),
                new ShellSort(),
                new QuickSort(),
                new MergeSort()
        };

        System.out.println("Sorting Algorithm Performance Comparison:");
        System.out.println("---------------------------------------------------------");
        System.out.printf("%-15s %-10s %-15s\n", "Algorithm", "Size (N)", "Avg Time (ms)");
        System.out.println("---------------------------------------------------------");

        for (int size : sizes) {
            for (SortingAlgorithm sa : algorithms) {
                Tester tester = new Tester(sa);
                double avgTime = tester.test(iterations, size);
                System.out.printf("%-15s %-10d %-15.3f\n", sa.getClass().getSimpleName(), size, avgTime);
            }
            System.out.println("---------------------------------------------------------");
        }
    }
}
