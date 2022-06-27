import java.util.*;
import java.io.*;

public class MaxPairwiseProduct {
    static long getMaxPairwiseProductMain(int[] numbers) {
        int n = numbers.length;

        int index1 = 0;

        //find max number index
        for(int i = 1; i < n; i++){
            if(numbers[i] > numbers[index1]){
                index1 = i;
            }
        }
        int index2 = 0;

        //find other max number index
        for(int j = 0; j < n; j++){
            if(j != index1 && numbers[j] > numbers[index2]){
                index2 = j;
            }
        }
        return (long)(numbers[index1]) * numbers[index2];
    }
    static long getMaxPairwiseProductSlow(int[] numbers){
        int n = numbers.length;
        long max_product = 0;

        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++ ){
                if((long)(numbers[i]) * numbers[j] > max_product){
                    max_product = (long)(numbers[i]) * numbers[j];
                }
            }
        }
        return max_product;
    }

    static class StressTest{
        static void Begin(){
            
            while(true){
                Random rand = new Random();

                int n = rand.nextInt(200000) + 2;
                int[] arr = new int[n];
                for(int i = 0; i < n; i++){
                    arr[i] = rand.nextInt(200000);
                    //System.out.print(arr[i] + " ");
                }
                long result1 = getMaxPairwiseProductMain(arr);
                long result2 = getMaxPairwiseProductSlow(arr);

                if(result1 == result2) {
                    System.out.println("Ok");
                }
                else{
                    System.out.println("Wrong");
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {

        StressTest.Begin();

        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }
        System.out.println(getMaxPairwiseProductMain(numbers));
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new
                    InputStreamReader(stream));
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
