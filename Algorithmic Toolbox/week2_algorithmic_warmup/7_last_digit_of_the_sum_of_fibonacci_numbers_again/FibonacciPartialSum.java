import java.util.*;

public class FibonacciPartialSum {

    private static long getFibonacciPartialSumFast(long from, long to){

        long sum = getFibonacciSum(to) - getFibonacciSum(from - 1);
        if(sum < 0) sum = sum + 10;
        return sum;
    }

    private static long getFibonacciSum(long n){
        long previous = 0;
        long current  = 1;

        for(long i = 0; i <= n % 60; i++){
            long tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
        }
        return (current - 1) % 10;
    }
      
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long from = scanner.nextLong();
        long to = scanner.nextLong();
        System.out.println(getFibonacciPartialSumFast(from, to));
    }
}

