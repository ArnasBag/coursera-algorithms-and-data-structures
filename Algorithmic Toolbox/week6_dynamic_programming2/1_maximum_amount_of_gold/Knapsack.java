import java.util.*;

public class Knapsack {
    static int optimalWeight(int W, int[] w) {
      int[][] table = new int[W + 1][w.length + 1];
        for(int i = 1; i < w.length + 1; i++){
          for(int j = 1; j <= W; j++){
            table[j][i] = table[j][i-1];        
            if(w[i - 1] <= j){
              int val = table[j - w[i - 1]][i - 1] + w[i - 1];
              if(val > table[j][i]) table[j][i] = val;
            }           
          }
        }
        return table[W][w.length];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W, n;
        W = scanner.nextInt();
        n = scanner.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        System.out.println(optimalWeight(W, w));
    }
}

