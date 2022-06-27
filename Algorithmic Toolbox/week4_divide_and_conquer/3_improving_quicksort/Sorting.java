import java.io.*;
import java.util.*;

public class Sorting {
    private static Random random = new Random();

    private static int[] partition3(int[] a, int l, int r) {
        int[] pivots = new int[2];
        
        int lt = l, i = l, gt = r;
        int x = a[l];

        while(i <= gt){
            if(a[i] < x){
                swap(a, i++, lt++);
            }
            else if(a[i] == x){
                i++;
            }
            else{
                swap(a, i, gt--);
            }
        }
       
        pivots[0] = lt;
        pivots[1] = gt;
        return pivots;
    }

    public static void swap(int[] array, int position1, int position2) {
        if (position1 != position2) {
            int temp = array[position1];
            array[position1] = array[position2];
            array[position2] = temp;
        }
    }

    private static int partition2(int[] a, int l, int r) {
        int x = a[l];
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (a[i] <= x) {
                j++;
                int t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }
        int t = a[l];
        a[l] = a[j];
        a[j] = t;
        return j;
    }

    private static void randomizedQuickSort(int[] a, int l, int r) {
        if (r <= l) {
            return;
        }
        int k = random.nextInt(r - l + 1) + l;
        int t = a[l];
        a[l] = a[k];
        a[k] = t;

        int[] pivots = partition3(a, l, r);

        randomizedQuickSort(a, l, pivots[0] - 1);
        randomizedQuickSort(a, pivots[1] + 1, r);
    }

    public static void main(String[] args) {
  
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        randomizedQuickSort(a, 0, n - 1);
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

