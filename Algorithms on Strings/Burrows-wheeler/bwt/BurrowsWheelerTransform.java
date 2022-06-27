import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BurrowsWheelerTransform {
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

    String BWT(String text) {
        StringBuilder result = new StringBuilder();

        List<String> BWMatrix = new ArrayList<String>();
        String concat = text;
        BWMatrix.add(concat);

        for(int i = 0; i < text.length() - 1; i++){
            String firstHalf = concat.substring(0, text.length() - 1);
            String secondHalf = concat.substring(text.length() - 1, text.length());
            concat = secondHalf + firstHalf;
            BWMatrix.add(concat);
        }
        
        Collections.sort(BWMatrix);

        for(String substr : BWMatrix){
            result.append(substr.charAt(substr.length() - 1));
        }

        return result.toString();
    }

    static public void main(String[] args) throws IOException {
        new BurrowsWheelerTransform().run();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String text = scanner.next();
        System.out.println(BWT(text));
    }
}
