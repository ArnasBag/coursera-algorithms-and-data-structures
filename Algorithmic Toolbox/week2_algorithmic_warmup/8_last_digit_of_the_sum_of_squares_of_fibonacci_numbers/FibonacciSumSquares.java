import java.util.*;

public class FibonacciSumSquares {

    private static long getFibonacciSumSquaresNaive(long n){
        long previous = 0;
        long current  = 1;

        long sum = 0;

        for(long i = 0; i < n % 60; i++){
            long tmp_previous = previous;
            previous = current;
            current = (tmp_previous + current) % 10;
        }
        sum = (current * previous) % 10;
        if(sum < 0) sum += 10;
        return sum;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long s = getFibonacciSumSquaresNaive(n);
        System.out.println(s);
    }
}

