import java.util.*;
import java.io.*;

public class MajorityElement {
    // private static int getMajorityElement(int[] a, int left, int right) {
    //     if(left == right){
    //         return a[left];
    //     }
    //     int mid = ((right - left) / 2) + left;
    //     int element1 = getMajorityElement(a, left, mid);
    //     int element2 = getMajorityElement(a, mid + 1, right);

    //     if(element1 == element2) return element1;
        
    //     int leftCount = getFrequency(a, element1);
    //     int rightCount = getFrequency(a, element2);

    //     if(leftCount > a.length / 2) return element1;
    //     else if(rightCount > a.length / 2) return element2;
    //     else return -1;
        
    // }

    // private static int getFrequency(int[] a, int element) {
    //     int count = 0;
    //     for(int x : a){
    //         if(x == element) count ++;
    //     }
    //     return count;
    // }

    private static int getMajorityElementSort(int[] a, int left, int right){
        Arrays.sort(a);
        int count = 0;
        for(int i = 1; i < a.length; i++){
            if(a[i] == a[i - 1]){
                count++;
                if(i == a.length - 1) count++;
            }         
            else{
                count++;
                if(count > a.length / 2) return 1;
                else count = 0;
            }
            if(count > a.length / 2) return 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        if (getMajorityElementSort(a, 0, a.length - 1) != -1) {
            System.out.println(1);
        } else {
            System.out.println(0);
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

