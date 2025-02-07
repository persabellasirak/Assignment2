// SortingAlgorithm interface
public interface SortingAlgorithm {
    int[] sorty(int[] input);
}

// Bubble Sort implementation
class BubbleSort implements SortingAlgorithm {
    public int[] sorty(int[] input) {
        int n = input.length;
        int[] arr = input.clone(); // Preserve original array
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }
}

// Insertion Sort implementation
class InsertionSort implements SortingAlgorithm {
    public int[] sorty(int[] input) {
        int[] arr = input.clone();
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
        return arr;
    }
}

// Selection Sort implementation
class SelectionSort implements SortingAlgorithm {
    public int[] sorty(int[] input) {
        int[] arr = input.clone();
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }
}

// Shell Sort implementation
class ShellSort implements SortingAlgorithm {
    public int[] sorty(int[] input) {
        int[] arr = input.clone();
        int n = arr.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = temp;
            }
        }
        return arr;
    }
}

// Quick Sort implementation
class QuickSort implements SortingAlgorithm {
    public int[] sorty(int[] input) {
        int[] arr = input.clone();
        quickSort(arr, 0, arr.length - 1);
        return arr;
    }

    private void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
}

// Merge Sort implementation
class MergeSort implements SortingAlgorithm {
    public int[] sorty(int[] input) {
        int[] arr = input.clone();
        mergeSort(arr, 0, arr.length - 1);
        return arr;
    }

    private void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        System.arraycopy(arr, left, leftArr, 0, n1);
        System.arraycopy(arr, mid + 1, rightArr, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }
}

// Main class to test all sorting algorithms
public class SortingTest {
    public static void main(String[] args) {
        int[] numbers = {64, 25, 12, 22, 11, 90, 34};

        SortingAlgorithm[] algorithms = {
                new BubbleSort(),
                new InsertionSort(),
                new SelectionSort(),
                new ShellSort(),
                new QuickSort(),
                new MergeSort()
        };

        String[] names = {
                "Bubble Sort", "Insertion Sort", "Selection Sort",
                "Shell Sort", "Quick Sort", "Merge Sort"
        };

        for (int i = 0; i < algorithms.length; i++) {
            int[] sortedArray = algorithms[i].sorty(numbers);
            System.out.println(names[i] + ": " + java.util.Arrays.toString(sortedArray));
        }
    }
}
