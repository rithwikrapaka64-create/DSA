import java.util.*;

public class TourPlanCO5 {

    // Bubble Sort
    static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    // Insertion Sort
    static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i-1;
            while (j >= 0 && arr[j] > key) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = key;
        }
    }

    // Quick Sort
    static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi-1);
            quickSort(arr, pi+1, high);
        }
    }

    static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low-1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;
        return i+1;
    }

    // Heap Sort
    static void heapSort(int[] arr) {
        int n = arr.length;
        for (int i = n/2 - 1; i >= 0; i--) heapify(arr, n, i);
        for (int i = n-1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
    }

    static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int l = 2*i + 1;
        int r = 2*i + 2;
        if (l < n && arr[l] > arr[largest]) largest = l;
        if (r < n && arr[r] > arr[largest]) largest = r;
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heapify(arr, n, largest);
        }
    }

    // Counting Sort (Hetix Sort)
    static void countingSort(int[] arr) {
        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().getAsInt();
        int range = max - min + 1;

        int[] count = new int[range];
        int[] output = new int[arr.length];

        for (int i = 0; i < arr.length; i++) count[arr[i] - min]++;
        for (int i = 1; i < range; i++) count[i] += count[i-1];
        for (int i = arr.length-1; i >= 0; i--) {
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;
        }
        System.arraycopy(output, 0, arr, 0, arr.length);
    }

    static void printArray(int[] arr) {
        for (int val : arr) System.out.print(val + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of destinations/bookings: ");
        int n = sc.nextInt();
        int[] arr = new int[n];

        System.out.println("Enter IDs:");
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        // Bubble Sort
        int[] bubbleArr = arr.clone();
        bubbleSort(bubbleArr);
        System.out.println("Sorted using Bubble Sort:");
        printArray(bubbleArr);

        // Insertion Sort
        int[] insertionArr = arr.clone();
        insertionSort(insertionArr);
        System.out.println("Sorted using Insertion Sort:");
        printArray(insertionArr);

        // Quick Sort
        int[] quickArr = arr.clone();
        quickSort(quickArr, 0, n-1);
        System.out.println("Sorted using Quick Sort:");
        printArray(quickArr);

        // Heap Sort
        int[] heapArr = arr.clone();
        heapSort(heapArr);
        System.out.println("Sorted using Heap Sort:");
        printArray(heapArr);

        // Counting Sort
        int[] countArr = arr.clone();
        countingSort(countArr);
        System.out.println("Sorted using Counting Sort:");
        printArray(countArr);

        sc.close();
    }
}
