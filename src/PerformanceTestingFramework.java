import java.util.Arrays;
import java.util.Random;

class Tester {
    private static int array;
    private SortingAlgorithm sortingAlgorithm;

    // Constructor to initialize with the sorting algorithm
    public Tester(SortingAlgorithm sa) {
        this.sortingAlgorithm = sa;
    }
// --------------

    private static int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt();
        }
        return array;
    }

    private static int[] generateKSorted(int size, int k) {
        int[] sortedArray = generateRandomArray(size);
        Arrays.sort(sortedArray);

        int[] kSortedArray = new int[size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            int minPos = Math.max(0, i - k);
            int maxPos = Math.min(size - 1, i + k);
            int newPos = minPos + random.nextInt(maxPos - minPos + 1);


            int count = 0;
            for (int j = 0; j < size; j++){
                if (kSortedArray[j] == sortedArray[i]){
                    count++;
                }
            }

            int insertPos = newPos;
            if (count > 0){
                for(int j = minPos; j <= maxPos; j++){
                    boolean found = false;
                    for(int l = 0; l < size; l++){
                        if (kSortedArray[l] == sortedArray[i]){
                            found = true;
                            break;
                        }
                    }
                    if (!found){
                        insertPos = j;
                        break;
                    }
                }
            }
            kSortedArray[insertPos] = sortedArray[i];
        }

        return kSortedArray;
    }


    // Method to perform a single test and return the time taken to sort an array
    public double singleTest(int size, boolean isKSorted, int k) {
        // Generate an array of random integers of the given size
        int[] array = generateRandomArray(size);
        //Random rand = new Random();
        //for (int i = 0; i < size; i++) {
          //  array[i] = rand.nextInt(100000); // Random integers between 0 and 99999
        //}
        if (isKSorted) {
            array = generateKSorted(size, k);
        } else {
            array = generateRandomArray(size);
        }
        // Record the start time
        long startTime = System.nanoTime();

        // Sort the array using the sorting algorithm
        sortingAlgorithm.sorty(array);

        // Record the end time
        long endTime = System.nanoTime();

        // Calculate the time taken in milliseconds
        return (endTime - startTime) / 1_000_000.0; // Convert from nanoseconds to milliseconds
    }

    // Method to run the test multiple times and print the average time taken
    public double test(int iterations, int size, boolean isKsorted, int k) {
        double totalTime = 0.0;

        // Run the singleTest method multiple times
        for (int i = 0; i < iterations; i++) {
            totalTime += singleTest(size, isKsorted, k);
        }

        // Calculate and print the average time taken to sort the array
        double averageTime = totalTime / iterations;
        System.out.printf("Average time to sort an array of size %d: %.4f ms\n", size, averageTime);
        return averageTime;
    }

    // Main method to run tests with various sorting algorithms and sizes
    public static void main(String[] args) {
        // Example: Testing QuickSort
        SortingAlgorithm quickSort = new QuickSort();
        Tester tester = new Tester(quickSort);


        // Test with 5 iterations and array size 1000
        tester.test(5, 1000, false, 0);

        // Test with 5 iterations and array size 5000
        tester.test(5, 5000, false, 0);

        // Test with 5 iterations and array size 10000
        tester.test(5, 10000, false, 0);

        int size = 20;  // Example array size
        int k = 5;      // Example k value

        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i + 1; // Fill with sorted values
        }

        KSorting.generateKSorted(arr, k);

        System.out.println("K-sorted array: " + Arrays.toString(arr));
    }
}
