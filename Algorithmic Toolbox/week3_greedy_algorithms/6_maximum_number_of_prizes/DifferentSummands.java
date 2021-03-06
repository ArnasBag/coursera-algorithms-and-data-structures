import java.util.*;

public class DifferentSummands {
    private static List<Integer> optimalSummands(int n) {
        List<Integer> summands = new ArrayList<Integer>();

        int summand = 1;
        while((n - summand) >= 0){
            int temp = n - summand;
            if(temp <= summand) summands.add(n);
            else{
                summands.add(summand);
            }
            n = n - summand;
            summand++;           
        }
        return summands;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> summands = optimalSummands(n);
        System.out.println(summands.size());
        for (Integer summand : summands) {
            System.out.print(summand + " ");
        }
    }
}

