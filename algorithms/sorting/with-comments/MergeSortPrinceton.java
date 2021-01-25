/**
 * Original implementation of merge sort as shown on the slides from Princeton
 * Algorithm course
 * 
 * Added comments for better personal understanding
 */
public class MergeSortPrinceton {

    /**
     * Public facing method, sorts the input array
     * 
     * @param a input array that implements Comparable
     */
    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];

        // calls private sort method to recursively sort elements
        sort(a, aux, 0, a.length - 1);
    }

    /**
     * Recursive method that breaks array into sub-array to be sorted
     * 
     * @param a   original input array
     * @param aux auxiliary array as temp storage
     * @param lo  lower bound index
     * @param hi  upper bound index
     */
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        // if sub-array length is 1, bubble up the stack
        if (hi <= lo)
            return;

        // find mid point to divide array into left and right
        int mid = lo + (hi - lo) / 2;

        // recursively break down to the left and right
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);

        // merge compares values from each half of the array and sort them in order
        merge(a, aux, lo, mid, hi);
    }

    /**
     * Compares elements in two arrays and put sorted elements in original input
     * array
     * 
     * @param a   original input array
     * @param aux auxiliary array
     * @param lo  lower bound index
     * @param mid upper bound for left half, mid + 1 is lower bound for right half
     * @param hi  upper bound index
     */
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        // isSorted implementation not shown
        // checks if left half and right half are sorted
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid + 1, hi);

        // copy all elements from input array to aux array
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        // i starts on left half, j starts on right half
        int i = lo, j = mid + 1;

        // k loop through entire array
        for (int k = lo; k <= hi; k++) {

            // "block copy" if one half is "empty"
            if (i > mid)
                a[k] = aux[j++];
            else if (j > hi)
                a[k] = aux[i++];
            // compares left and right, copy smaller into input array
            else if (less(aux[j], aux[i]))
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }

        // checks if input array is sorted
        assert isSorted(a, lo, hi);
    }

}
