import java.util.*;

public class PrimitiveCalculator {
    private static List<Integer> optimal_sequence(int n) {
        List<Integer> sequence = new ArrayList<Integer>();
        
        int[] table = new int[n + 1];
        table[0] = 0;
        table[1] = 0;

        //Forms the DP table that stores information about the lowest possible number of operations for each value up to n
        for(int i = 2; i <= n; i++){
            table[i] = Integer.MAX_VALUE;
            if(i % 2 == 0){
                table[i] = Math.min(table[i / 2] + 1, table[i]);
            }
            if(i % 3 == 0){
                table[i] = Math.min(table[i / 3] + 1, table[i]);
            }
            table[i] = Math.min(table[i - 1] + 1, table[i]);
        }
        
        //Backtracking algorithm for forming sequence
        for(int i = n; i > 1; ) {
            sequence.add(i);
            //Check if adding one to previous dp table value would yield i-th value
            if (table[i - 1] == table[i] - 1)
                i = i - 1;
            //Otherwise check if multiplying dp table value by 2 or 3 would yield i-th value
            else if (i % 2 == 0 && (table[i / 2] == table[i] - 1))
                i = i / 2;
            else if (i % 3 == 0 && (table[i / 3] == table[i] - 1))
                i = i / 3;
        }
        //Finally add one
        sequence.add(1);

        Collections.reverse(sequence);
        return sequence;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> sequence = optimal_sequence(n);
        System.out.println(sequence.size() - 1);
        for (Integer x : sequence) {
            System.out.print(x + " ");
        }
    }
}

