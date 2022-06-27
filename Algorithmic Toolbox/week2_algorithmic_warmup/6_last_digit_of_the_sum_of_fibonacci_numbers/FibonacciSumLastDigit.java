import java.util.*;

public class FibonacciSumLastDigit {
    private static long getFibonacciSumFast(long n) {
        if (n <= 1)
            return n;
            
        long previous = 0;
        long current  = 1;

        long remainder = n % getPisanoPeriod(10);

        for (long i = 0; i < remainder + 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
        }
        return (current - 1) % 10;
    }

    private static long getPisanoPeriod(long m){     
        long previous = 0;
        long current  = 1;

        for(long i = 0; i < m * m; i++){
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
        long s = getFibonacciSumFast(n);
        System.out.println(s);
    }
}

