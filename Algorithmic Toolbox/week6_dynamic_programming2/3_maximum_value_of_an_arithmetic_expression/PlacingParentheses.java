import java.util.Scanner;

public class PlacingParentheses {
    private static long getMaximValue(String exp){
        int numAmount = (exp.length() + 1) / 2;

        long[][] minValues = new long[numAmount][numAmount];
        long[][] maxValues = new long[numAmount][numAmount];

        for(int i = 0; i < numAmount; i++){
            minValues[i][i] = Long.parseLong(String.valueOf(exp.charAt(i * 2)));
            maxValues[i][i] = Long.parseLong(String.valueOf(exp.charAt(i * 2)));
        }


        for(int s = 1; s < numAmount; s++){
            for(int i = 0; i < numAmount - s; i++){
                int j = i + s;
                long min = Long.MAX_VALUE;
                long max = Long.MIN_VALUE;
                for(int k = i; k < j; k++){
                    long a = eval(minValues[i][k], minValues[k + 1][j], exp.charAt(2 * k + 1));
                    long b = eval(minValues[i][k], maxValues[k + 1][j], exp.charAt(2 * k + 1));
                    long c = eval(maxValues[i][k], minValues[k + 1][j], exp.charAt(2 * k + 1));
                    long d = eval(maxValues[i][k], maxValues[k + 1][j], exp.charAt(2 * k + 1));


                    min = Math.min(min, Math.min(a, Math.min(b, Math.min(c, d))));
                    max = Math.max(max, Math.max(a, Math.max(b, Math.max(c, d))));
                }
                minValues[i][j] = min;
                maxValues[i][j] = max;
            }
        }
        return maxValues[0][numAmount - 1];
    }

    private static long eval(long a, long b, char op) {
        if (op == '+') {
            return a + b;
        } else if (op == '-') {
            return a - b;
        } else if (op == '*') {
            return a * b;
        } else {
            assert false;
            return 0;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String exp = scanner.next();
        System.out.println(getMaximValue(exp));
    }
}

