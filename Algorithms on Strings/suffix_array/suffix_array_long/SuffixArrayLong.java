import java.util.*;
import java.io.*;
import java.util.zip.CheckedInputStream;

public class SuffixArrayLong {
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

    public class Suffix implements Comparable {
        String suffix;
        int start;

        Suffix(String suffix, int start) {
            this.suffix = suffix;
            this.start = start;
        }

        @Override
        public int compareTo(Object o) {
            Suffix other = (Suffix) o;
            return suffix.compareTo(other.suffix);
        }
    }

    public int letterToIndex (char letter)
	{
		switch (letter)
		{
            case '$': return 0;
			case 'A': return 1;
			case 'C': return 2;
			case 'G': return 3;
			case 'T': return 4;
			default: assert (false); return -1;
		}
	}

    public int[] sortCharacters(String text){
        int[] order = new int[text.length()];
        int[] count = new int[5]; 

        for(int i = 0; i < text.length(); i++){
            int index = letterToIndex(text.charAt(i));

            //number of occurences of each character in the string
            count[index]++;
        }
        for(int j = 1; j < 5; j++){
            //position after the position of each element in sorted result
            count[j] += count[j - 1];
        }
        for(int i = text.length() - 1; i >= 0; i--){            
            char letter = text.charAt(i);
            int index = letterToIndex(letter);
            count[index]--;
            //save positions of letters in text in sorted order
            order[count[index]] = i;
        }
        return order;
    }

    public int[] computeCharClasses(String text, int[] order){
        int[] equClass = new int[text.length()];
        equClass[order[0]] = 0;
        for(int i = 1; i < text.length(); i++){
            if(text.charAt(order[i]) != text.charAt(order[i - 1])){
                equClass[order[i]] = equClass[order[i - 1]] + 1;
            }
            else{
                equClass[order[i]]  = equClass[order[i - 1]];
            }
        }
        return equClass;
    }

    public int[] sortDoubled(String text, int L, int[] order, int[] equClass){
        int[] count = new int[text.length()];
        int[] newOrder = new int[text.length()];

        for(int i = 0; i < text.length(); i++){
            count[equClass[i]]++;
        }
        for(int j = 1; j < text.length(); j++){
            count[j] += count[j - 1];
        }
        for(int i = text.length() - 1; i >= 0; i--){
            int start = (order[i] - L + text.length()) % text.length();
            int cl = equClass[start];
            count[cl]--;
            newOrder[count[cl]] = start;
        }
        return newOrder;
    }

    public int[] updateClasses(int[] newOrder, int[] equClass, int L){
        int n = newOrder.length;
        int[] newClass = new int[n];
        newClass[newOrder[0]] = 0;

        for(int i = 1; i < n; i++){
            int cur = newOrder[i], prev = newOrder[i - 1];
            int mid = (cur + L) % n, midPrev = (prev + L) %n;

            if(equClass[cur] != equClass[prev] || equClass[mid] != equClass[midPrev]){
                newClass[cur] = newClass[prev] + 1;
            }
            else{
                newClass[cur] = newClass[prev];
            }
        }
        return newClass;
    }

    // Build suffix array of the string text and
    // return an int[] result of the same length as the text
    // such that the value result[i] is the index (0-based)
    // in text where the i-th lexicographically smallest
    // suffix of text starts.
    public int[] computeSuffixArray(String text) {
        int[] order = sortCharacters(text);
        int[] equClass = computeCharClasses(text, order);
        int L = 1;

        while(L < text.length()){
            order = sortDoubled(text, L, order, equClass);
            equClass = updateClasses(order, equClass, L);
            L *= 2;
        }
        return order;
    }


    static public void main(String[] args) throws IOException {
        new SuffixArrayLong().run();
    }

    public void print(int[] x) {
        for (int a : x) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String text = scanner.next();
        int[] suffix_array = computeSuffixArray(text);
        print(suffix_array);
    }
}
