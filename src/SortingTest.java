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
