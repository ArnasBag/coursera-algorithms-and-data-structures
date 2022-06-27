import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Trie {
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

    List<Map<Character, Integer>> buildTrie(String[] patterns) {
        List<Map<Character, Integer>> trie = new ArrayList<Map<Character, Integer>>();
        trie.add(new TreeMap<>());

        for(String pattern : patterns){
            int currentIndex = 0;
            for(int i = 0; i < pattern.length(); i++){
                Map<Character, Integer> currentNode = trie.get(currentIndex);
                Character currentSymbol = pattern.charAt(i);

                //If the character is unique in a tree path, either add a node at the end of a separate path, 
                //or at the end of a previous path depending on previous character.
                if(!currentNode.containsKey(currentSymbol)){                   
                    trie.add(new TreeMap<>());

                    //Separate path would mean putting a character in an already existing node.
                    //Previous path would mean putting a character at a different index in trie.
                    //currentIndex is the variable that determines the choice.
                    trie.get(currentIndex).put(currentSymbol, trie.size() - 1);                  
                    currentIndex = trie.size() - 1;
                }

                //If the character is repeating in a tree path, instead of adding a node, traverse to the next character in the path.
                else{
                    currentIndex = currentNode.get(currentSymbol);           
                }
            }
        }

        return trie;
    }

    static public void main(String[] args) throws IOException {
        new Trie().run();
    }

    public void print(List<Map<Character, Integer>> trie) {
        for (int i = 0; i < trie.size(); ++i) {
            Map<Character, Integer> node = trie.get(i);
            for (Map.Entry<Character, Integer> entry : node.entrySet()) {
                System.out.println(i + "->" + entry.getValue() + ":" + entry.getKey());
            }
        }
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        int patternsCount = scanner.nextInt();
        String[] patterns = new String[patternsCount];
        for (int i = 0; i < patternsCount; ++i) {
            patterns[i] = scanner.next();
        }
        List<Map<Character, Integer>> trie = buildTrie(patterns);
        print(trie);
    }
}
