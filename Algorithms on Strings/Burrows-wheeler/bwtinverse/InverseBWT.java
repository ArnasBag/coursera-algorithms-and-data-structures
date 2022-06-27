import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class InverseBWT {
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

    public class Pair implements Comparable {
        Character symbol;
        int index;

        Pair(Character symbol, int index) {
            this.symbol = symbol;
            this.index = index;
        }

        @Override
        public int compareTo(Object o) {
            Pair other = (Pair) o;
            return symbol.compareTo(other.symbol);
        }
    }

    String inverseBWT(String bwt) {
        StringBuilder result = new StringBuilder();

        //naive approach
        // String[] BWMatrix = new String[bwt.length()];

        // for(int i = 0; i < bwt.length(); i++){
        //     BWMatrix[i] = "";
        // }

        // for(int i = 0; i < bwt.length(); i++){
        //     for(int j = 0; j < bwt.length(); j++){
        //         BWMatrix[j] = bwt.charAt(j) + BWMatrix[j];
        //     }
        //     Arrays.sort(BWMatrix);
        // }
        // result.insert(0, BWMatrix[0]).reverse();
        

        List<Pair> pairs = new ArrayList<Pair>();

        for(int i = 0; i < bwt.length(); i++){
            Pair pair = new Pair(bwt.charAt(i), i);
            pairs.add(pair);
        }

        Collections.sort(pairs);

        Pair first = pairs.get(0);
        for(int i = 0; i < bwt.length(); i++){
            first = pairs.get(first.index);
            result.append(first.symbol);
        }


        return result.toString();
    }

    static public void main(String[] args) throws IOException {
        new InverseBWT().run();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String bwt = scanner.next();
        System.out.println(inverseBWT(bwt));
    }
}
