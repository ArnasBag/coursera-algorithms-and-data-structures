import java.util.Locale;
import java.util.Scanner;

public class FractionalKnapsack {
    
    //safe move - pick the biggest fraction (best ratio of values and weights) and add to knapsack
    private static double getOptimalValue(int capacity, int[] values, int[] weights) {
        double value = 0;        
        int n = values.length;       

        for(int i = 0; i < n; i++){
            int max_index = 0;
            double max = 0;
            if(capacity == 0) return value;
            if(weights[i] > 0) max = ((double)values[0]) / weights[0];
            
            for(int j = 1; j < n; j++){
                double fraction = 0;
                if(weights[j] > 0) fraction = ((double)values[j]) / weights[j];
                if(fraction > max){
                    max = fraction;
                    max_index = j;
                }
            }
            int a = Math.min(capacity, weights[max_index]);
            value = value + a * max;
            capacity = capacity - a;
            weights[max_index] -= a;
        }
        return value;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        System.out.printf(String.format(Locale.ROOT, "%.4f", getOptimalValue(capacity, values, weights)));
    }
} 
