import java.util.Scanner;

public class Fibonacci {
  private static long calc_fib(int n) {
    if(n <= 1){
      return n;
    }

    long[] sequence = new long[n];
    sequence[0] = 1;
    sequence[1] = 1;

    for(int i = 2; i < n; i++){
      sequence[i] = sequence[i - 1] + sequence[i - 2];
    }
    
    return sequence[n-1];
  }

  public static void main(String args[]) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();

    System.out.println(calc_fib(n));
  }
}
