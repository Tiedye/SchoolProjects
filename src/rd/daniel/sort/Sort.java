 
package rd.daniel.sort;

/**
 *
 * @author Daniel <tiedye1@hotmail.com>
 */
public class Sort {
    
    private Sort() {}
    
    public static void InsertionSort(int[] a){
        for(int i = 0; i < a.length; i++) {
            int t = a[i];
            int j;
            for (j = i; j > 0 && a[j] < a[j-1]; j--) a[j] = a[j-1];
            a[j] = t;
        }
    }
    public static void InsertionSort(double[] a){
        for(int i = 0; i < a.length; i++) {
            double t = a[i];
            int j;
            for (j = i; j > 0 && a[j] < a[j-1]; j--) a[j] = a[j-1];
            a[j] = t;
        }
    }
    public static void InsertionSort(Comparable[] a){
        for(int i = 0; i < a.length; i++) {
            Comparable t = a[i];
            int j;
            for (j = i; j > 0 && a[j].compareTo(a[j-1]) < 0; j--) a[j] = a[j-1];
            a[j] = t;
        }
    }
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
    public static void BubbleSort(Comparable[] a){
        int h = a.length;
        int nh;
        int c = 0;
        while(h > 0){
            c++;
            if(c%10 == 0) System.out.println(h);
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
    
    public static void HeapSort(int[] a){
        // heapify step
        boolean needsIteration = true;
        while (needsIteration) {
            needsIteration = false;
            for(int i = a.length; i < 0; i++) {
                if (a[i/2-1] < a[i-1]) {
                    int t = a[i-1];
                    a[i-1] = a[i/2-1];
                    a[i/2-1] = t;
                    needsIteration = true;
                }
            }
        }
        // sort
        
    }
}
