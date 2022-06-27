import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class PointsAndSegments {
   
    //unfinished
    private static class Pair implements Comparable<Pair>{
        public Integer value;
        public char key;

        public Pair(Integer value, char key){
            this.value = value;
            this.key = key;
        }
        @Override
        public int compareTo(Pair pair){
            if(this.value < pair.value) return -1;
            if(this.value > pair.value) return 1;
            else{
                if(this.key < pair.key) return -1;
                if(this.key > pair.key) return 1;
                else return 0;
            }
        }
    }


    private static int[] fastCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];

        
        ArrayList<Pair> pairs = new ArrayList<Pair>();

        for(int i = 0; i < starts.length; i++){
            pairs.add(new Pair(starts[i], 'l'));
            pairs.add(new Pair(ends[i], 'r'));
        }
        for(int j = 0; j < points.length; j++){
            pairs.add(new Pair(points[j], 'p'));
        }

        Collections.sort(pairs);

        for(Pair pair : pairs){
            System.out.println(pair.key);
        }



        return cnt;
    }

    private static int[] naiveCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < starts.length; j++) {
                if (starts[j] <= points[i] && points[i] <= ends[j]) {
                    cnt[i]++;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, m;
        n = scanner.nextInt();
        m = scanner.nextInt();
        int[] starts = new int[n];
        int[] ends = new int[n];
        int[] points = new int[m];
        for (int i = 0; i < n; i++) {
            starts[i] = scanner.nextInt();
            ends[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }
        //use fastCountSegments
        int[] cnt = fastCountSegments(starts, ends, points);
        for (int x : cnt) {
            System.out.print(x + " ");
        }
    }
}

