public class MergeSort {

    /**
     * algorithmn found @ https://www.baeldung.com/java-merge-sort
     * @param a input array
     * @param n length of the input array
     */
    public static void mergeSort(int[] a, int n) {
        // minimum sub-array size is greater than 2
        if (n < 2) {
            return;
        }

        // get the middle index, and create two sub-arrays, l[] and r[]
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        // copy elements in to each sub-array
        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
    
        // The mergeSort function is then called recursively for both the sub-arrays:
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        // merge function which takes in the input and both the sub-arrays and the end indices of both the sub arrays
        merge(a, l, r, mid, n - mid);
    }

    /**
     * merge function compares the elements of both sub-arrays one by one 
     * and places the smaller element into the input array.
     * @param a input array
     * @param l left sub-array
     * @param r right sub-array
     * @param left ending index of left sub-array
     * @param right ending index of right sub-array
     */
    public static void merge(int[] a, int[] l, int[] r, int left, int right) {

        // i tracks left sub-array
        // j tracks right sub-array
        // k tracks original input array
        int i = 0, j = 0, k = 0;
        
        // when both arrays still have elements left, put the smaller value into
        // the original input array
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
            } else {
                a[k++] = r[j++];
            }
        }

        // "block copy" the rest if one of the sub-array is empty
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }
}