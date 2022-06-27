import java.io.SequenceInputStream;
import java.util.*;

public class FibonacciHuge {
    private static long getFibonacciHugeFast(long n, int m){
        if(n <= 1){
            return n;
        }

        long remainder = n % getPisanoPeriod(m);
        if(remainder == 0) return 0;

        long previous = 0;
        long current = 1;

        for(int i = 0; i < remainder - 1; i++){
            long tmp_previous = previous;
            previous = current;
            current = (tmp_previous + current) % m;
        }
        return current;
    }
    private static int getPisanoPeriod(int m){     
        long previous = 0;
        long current  = 1;

        for(int i = 0; i < m * m; i++){
            long tmp_previous = previous;
            previous = current;
            current = (tmp_previous + current) % m;

            if(previous == 0 && current == 1){
                return i + 1;
            }
        }
        return -1;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        int m = scanner.nextInt();
        System.out.println(getFibonacciHugeFast(n, m));
    }
}

