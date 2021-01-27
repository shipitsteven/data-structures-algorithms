/**
 * Selection sort implementation from Princeton course
 * 
 * Worst case time complexity: O(n^2)
 * 
 * Space complexity: O(1)
 * 
 * Stable: No
 */
public class SelectionSortPrinceton {

    /**
     * Selection is always quadratic time complexity It runs through the array and
     * finds the smallest value then swap it with the current index. No matter how
     * sorted the array is, we still have to run through the entire array
     * quadratically for every element
     * 
     * @param a input array to be sorted
     */
    public static void selectionSort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            exchange(a, i, min);
        }
    }

    /**
     * is item v less than w?
     */
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /**
     * Swap item in array a[] at index i with the one at index j
     * 
     * @param a input array
     * @param i the index position to be swapped
     * @param j new value's index
     */
    private static void exchange(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
}