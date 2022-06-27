import java.util.*;

public class FibonacciLastDigit {
    private static int getFibonacciLastDigitFast(int n) {
        if (n <= 1)
            return n;

        int[] fibLastDigits = new int[n];
        fibLastDigits[0] = 0;
        fibLastDigits[1] = 1;

        for(int i = 2; i < n; i++){
            //instead of storing whole fibonacci number, store only the last digit: same algorithm works
            fibLastDigits[i] = (fibLastDigits[i - 1] + fibLastDigits[i - 2]) % 10;
        }
        return fibLastDigits[n - 1];
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int c = getFibonacciLastDigitFast(n);
        System.out.println(c);
    }
}
