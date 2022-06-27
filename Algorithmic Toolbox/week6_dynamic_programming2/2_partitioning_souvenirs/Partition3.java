import java.util.*;
import java.io.*;

public class Partition3 {
    // private static int partition3(int[] A) {
    //     int total = 0;
    //     for(int x : A){
    //         total += x;
    //     }
    //     if(total % 3 != 0) return 0;

    //     int optimal = optimalWeight(total / 3, A);
        

    //     return 0;
    // }
    // static int optimalWeight(int W, int[] w) {
    //     int[][] table = new int[W + 1][w.length + 1];
  
  
    //       for(int i = 1; i < w.length + 1; i++){
    //         for(int j = 1; j <= W; j++){
    //           table[j][i] = table[j][i-1];
              
    //           if(w[i - 1] <= j){
    //             int val = table[j - w[i - 1]][i - 1] + w[i - 1];
    //             if(val > table[j][i]) table[j][i] = val;
    //           }           
    //         }
    //       }
    //       List<Integer> solution = backtrace(table, W, w);
    //       for(int a : solution) System.out.println(a);
    //       return table[W][w.length];
    //   }
    //   static List<Integer> backtrace(int[][] table, int W, int[] w){
    //       List<Integer> solution = new ArrayList<Integer>();
    //       for(int i = w.length; i >= 1; i--){
    //           if(table[W][i] != table[W][i - 1]){
    //               solution.add(Math.max(table[W][i - 1], table[W - w[i - 1]][i - 1] + w[i - 1]));
    //           }
    //           else{
    //               solution.add(0);
    //           }
    //       }
    //       return solution;
       
    //   }
      static int findPartition (int arr[]){ 
        int total = 0;
        for(int x : arr){
            total += x;
        }
        if(total % 3 != 0) return 0;
        
        int[][][] dp = new int[arr.length + 1][total / 3 + 1][total / 3 + 1];
        for (int i = 1; i <= arr.length; i++){
            for (int j = 1; j <= total / 3; j++){
                for (int k = 1; k <= total / 3; k++){
                    //the item may not contain in both two knapsacks
                    //in this case it falls in the 3rd Knapsack
                    dp[i][j][k] = dp[i - 1][j][k];
                    //keeping the item in the first knapsack
                    if (j >= arr[i - 1])
                        dp[i][j][k] = Math.max(dp[i - 1][j - arr[i - 1]][k] + arr[i - 1], dp[i][j][k]);
                    //keeping the item in the second knapsack
                    if (k >= arr[i - 1])
                        dp[i][j][k] = Math.max(dp[i - 1][j][k - arr[i - 1]] + arr[i - 1], dp[i][j][k]);
                }
            }
        }
        return dp[arr.length][total / 3][total / 3] == 2 * total / 3 ? 1 : 0;
      } 

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = scanner.nextInt();
        }
        System.out.println(findPartition(A));
    }
}

