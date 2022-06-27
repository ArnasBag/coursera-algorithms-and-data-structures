import java.util.*;
import java.io.*;

public class StackWithMax {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }
        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public void solve() throws IOException {
        FastScanner scanner = new FastScanner();
        int queries = scanner.nextInt();
        Stack<Integer> stack = new Stack<Integer>();
        Stack<Integer> maxStack = new Stack<Integer>();

        for (int qi = 0; qi < queries; ++qi) {
            String operation = scanner.next();
            if ("push".equals(operation)) {
                int value = scanner.nextInt();
                if(stack.empty()){
                    stack.push(value);
                    maxStack.push(value);
                }
                else{
                    stack.push(value);                   
                    int maxVal = maxStack.peek();
                    //maxStack.push(value);
                    if(value >= maxVal){
                        maxStack.push(value);
                    }
                    // else{
                    //     maxStack.push(maxVal);
                    // }
                }
                
            } else if ("pop".equals(operation)) {
                int x = stack.pop();
                int y = maxStack.pop();

                if(x != y) maxStack.push(y);
            } else if ("max".equals(operation)) {
                System.out.println(maxStack.peek());
            }
        }
    }

    static public void main(String[] args) throws IOException {
        new StackWithMax().solve();
    }
}
