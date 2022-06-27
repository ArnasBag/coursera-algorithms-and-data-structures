import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class HashSubstring {

    private static FastScanner in;
    private static PrintWriter out;

    public static void main(String[] args) throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        printOccurrences(getOccurrences(readInput()));
        out.close();
    }

    private static Data readInput() throws IOException {
        String pattern = in.next();
        String text = in.next();
        return new Data(pattern, text);
    }

    private static void printOccurrences(List<Integer> ans) throws IOException {
        for (Integer cur : ans) {
            out.print(cur);
            out.print(" ");
        }
    }
    private static long polyHash(String substring, int prime, int multiplier){
        long hash = 0;
        for(int i = substring.length() - 1; i >= 0; i--){
            hash = (hash * multiplier + substring.charAt(i)) % prime;
        }
        return hash;
    }
    private static long[] precomputeHashes(Data input, int prime, int multiplier){
		int tLength = input.text.length();
		int pLength = input.pattern.length();

        long[] hashes = new long[tLength - pLength + 1];
        
        String lastSubstring = input.text.substring(tLength - pLength);
		hashes[tLength - pLength] = polyHash(lastSubstring, prime, multiplier);
		
        long y = 1;
        for(int i = 1; i < pLength + 1; i++){
			y = (y * multiplier) % prime;
			//System.out.println(y);
        }
        for(int i = tLength - pLength - 1; i >= 0; i--){
			hashes[i] = (((multiplier * hashes[i + 1] + input.text.charAt(i)) % prime) - ((y * input.text.charAt(i + pLength)) % prime) + prime) % prime;
        }
        return hashes;
    }
    private static Boolean areEqual(String s1, String s2){
        if(s1.length() != s2.length()) return false;
        for(int i = 0; i < s1.length(); i++){
            if(s1.charAt(i) != s2.charAt(i)) return false;
        }
        return true;
    }

    private static List<Integer> getOccurrences(Data input) {
		int tLength = input.text.length();
		int pLength = input.pattern.length();

        long[] precomputedHashes = precomputeHashes(input, 1000000007, 2);

        List<Integer> result = new ArrayList<Integer>();
		long pHash = polyHash(input.pattern, 1000000007, 2);
		
        for(int i = 0; i < tLength - pLength + 1; i++){
            if(pHash != precomputedHashes[i]) continue;
            if(areEqual(input.text.substring(i, i + pLength), input.pattern)) result.add(i);
        }
        return result;
    }

    static class Data {
        String pattern;
        String text;
        public Data(String pattern, String text) {
            this.pattern = pattern;
            this.text = text;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}