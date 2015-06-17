 
package rd.daniel.sort;

/**
 *
 * @author Daniel <tiedye1@hotmail.com>
 */
public class Sort {
    
    private Sort() {}
    
    /**
     * Sort array using the insertion sort algorithm.
     * @param a The array to be sorted 
     */
    public static void InsertionSort(int[] a){
        for(int i = 0; i < a.length; i++) {
            int t = a[i];
            int j;
            for (j = i; j > 0 && t < a[j-1]; j--) a[j] = a[j-1];
            a[j] = t;
        }
    }
    /**
     * Sort array using the insertion sort algorithm.
     * @param a The array to be sorted
     */
    public static void InsertionSort(double[] a){
        for(int i = 0; i < a.length; i++) {
            double t = a[i];
            int j;
            for (j = i; j > 0 && t < a[j-1]; j--) a[j] = a[j-1];
            a[j] = t;
        }
    }
    /**
     * Sort array using the insertion sort algorithm.
     * @param a The array to be sorted
     */
    public static void InsertionSort(Comparable[] a){
        for(int i = 0; i < a.length; i++) {
            Comparable t = a[i];
            int j;
            for (j = i; j > 0 && t.compareTo(a[j-1]) < 0; j--) a[j] = a[j-1];
            a[j] = t;
        }
    }
    /**
     * Sort array using the bubble sort algorithm.
     * @param a The array to be sorted
     */
    public static void BubbleSort(int[] a){
        int h = a.length;
        int nh;
        while(h > 0){
            nh = 0;
            for(int i = 1; i < h; i++){
                if(a[i] < a[i-1]) {
                    int t = a[i];
                    a[i] = a[i-1];
                    a[i-1] = t;
                    nh = i;
                }
            }
            h = nh;
        }
    }
    /**
     * Sort array using the bubble sort algorithm.
     * @param a The array to be sorted
     */
    public static void BubbleSort(double[] a){
        int h = a.length;
        int nh;
        while(h > 0){
            nh = 0;
            for(int i = 1; i < h; i++){
                if(a[i] < a[i-1]) {
                    double t = a[i];
                    a[i] = a[i-1];
                    a[i-1] = t;
                    nh = i;
                }
            }
            h = nh;
        }
    }
    /**
     * Sort array using the bubble sort algorithm.
     * @param a The array to be sorted
     */
    public static void BubbleSort(Comparable[] a){
        int h = a.length;
        int nh;
        int c = 0;
        while(h > 0){
            c++;
            if(c%100 == 0) System.out.println(h);
            nh = 0;
            for(int i = 1; i < h; i++){
                if(a[i].compareTo(a[i-1]) < 0) {
                    Comparable t = a[i];
                    a[i] = a[i-1];
                    a[i-1] = t;
                    nh = i;
                }
            }
            h = nh;
        }
    }
    /**
     * Sort array using the selection sort algorithm.
     * @param a The array to be sorted
     */
    public static void SelectionSort(int[] a){
        for (int i = 0; i < a.length; i++){
            int m = i;
            for (int j = i + 1; j < a.length; j++){
                if (a[j] < a[m]) m = j;
            }
            int t = a[m];
            a[m] = a[i];
            a[i] = t;
        }
    }
    /**
     * Sort array using the selection sort algorithm.
     * @param a The array to be sorted
     */
    public static void SelectionSort(double[] a){
        for (int i = 0; i < a.length; i++){
            int m = i;
            for (int j = i + 1; j < a.length; j++){
                if (a[j] < a[m]) m = j;
            }
            double t = a[m];
            a[m] = a[i];
            a[i] = t;
        }
    }
    /**
     * Sort array using the selection sort algorithm.
     * @param a The array to be sorted
     */
    public static void SelectionSort(Comparable[] a){
        for (int i = 0; i < a.length; i++){
            int m = i;
            for (int j = i + 1; j < a.length; j++){
                if (a[j].compareTo(a[m]) < 0) m = j;
            }
            Comparable t = a[m];
            a[m] = a[i];
            a[i] = t;
        }
    }
    
    /**
     * Sort array using the heap sort algorithm.
     * @param a The array to be sorted
     */
    public static void HeapSort(int[] a){
        // heapify step
        boolean needsIteration = true;
        // keep iterating over the array until the tree structure is established
        while (needsIteration) {
            needsIteration = false;
            for(int i = a.length; i > 1; i--) {
                if (a[i/2-1] < a[i-1]) {
                    int t = a[i-1];
                    a[i-1] = a[i/2-1];
                    a[i/2-1] = t;
                    // if we make a swap we may need to make another
                    needsIteration = true;
                }
            }
        }
        // sort
        for(int i = a.length; i > 0; i--){
            // pop the largest item off the tree and put it at the next spot in the array
            int t = a[i-1];
            a[i-1] = a[0];
            a[0] = t;
            for(int j = 1; 2 * j < i;){
                // reesablish the tree structure
                int j1 = j - 1;
                int j2 = 2 * j - 1;
                int j3 = j2 + 2 >= i ? j2 : j2 + 1;
                if (a[j2] < a[j3]) {
                    j2 = j3;
                }
                if (a[j1] < a[j2]) {
                    t = a[j1];
                    a[j1] = a[j2];
                    a[j2] = t;
                    j = j2 + 1;
                } else break;
            }
        }
    }
    /**
     * Sort array using the heap sort algorithm.
     * @param a The array to be sorted
     */
    public static void HeapSort(double[] a){
        // heapify step
        boolean needsIteration = true;
        while (needsIteration) {
            needsIteration = false;
            for(int i = a.length; i > 1; i--) {
                if (a[i/2-1] < a[i-1]) {
                    double t = a[i-1];
                    a[i-1] = a[i/2-1];
                    a[i/2-1] = t;
                    needsIteration = true;
                }
            }
        }
        // sort
        for(int i = a.length; i > 0; i--){
            double t = a[i-1];
            a[i-1] = a[0];
            a[0] = t;
            for(int j = 1; 2 * j < i;){
                int j1 = j - 1;
                int j2 = 2 * j - 1;
                int j3 = j2 + 2 >= i ? j2 : j2 + 1;
                if (a[j2] < a[j3]) {
                    j2 = j3;
                }
                if (a[j1] < a[j2]) {
                    t = a[j1];
                    a[j1] = a[j2];
                    a[j2] = t;
                    j = j2 + 1;
                } else break;
            }
        }
    }
    /**
     * Sort array using the heap sort algorithm.
     * @param a The array to be sorted
     */
    public static void HeapSort(Comparable[] a){
        // heapify step
        boolean needsIteration = true;
        while (needsIteration) {
            needsIteration = false;
            for(int i = a.length; i > 1; i--) {
                if (a[i/2-1].compareTo(a[i-1]) < 0) {
                    Comparable t = a[i-1];
                    a[i-1] = a[i/2-1];
                    a[i/2-1] = t;
                    needsIteration = true;
                }
            }
        }
        // sort
        for(int i = a.length; i > 0; i--){
            Comparable t = a[i-1];
            a[i-1] = a[0];
            a[0] = t;
            for(int j = 1; 2 * j < i;){
                int j1 = j - 1;
                int j2 = 2 * j - 1;
                int j3 = j2 + 2 >= i ? j2 : j2 + 1;
                if (a[j2].compareTo(a[j3]) < 0) {
                    j2 = j3;
                }
                if (a[j1].compareTo(a[j2]) < 0) {
                    t = a[j1];
                    a[j1] = a[j2];
                    a[j2] = t;
                    j = j2 + 1;
                } else break;
            }
        }
    }
}
