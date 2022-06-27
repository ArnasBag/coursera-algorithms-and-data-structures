import java.io.*;
import java.util.*;

class Node
{
	public static final int Letters =  4;
	public static final int NA      = -1;
	public int next [];
	public boolean end;

	Node ()
	{
		next = new int [Letters];
		Arrays.fill (next, NA);
		end = false;
	}
}

public class TrieMatchingExtended implements Runnable {
	int letterToIndex (char letter)
	{
		switch (letter)
		{
			case 'A': return 0;
			case 'C': return 1;
			case 'G': return 2;
			case 'T': return 3;
			default: assert (false); return Node.NA;
		}
	}

	List<Node> buildTrie(List<String> patterns){
		List<Node> trie = new ArrayList<Node>();
		trie.add(new Node());

		for(String pattern : patterns){
			int currentIndex = 0;
			for(int i = 0; i < pattern.length(); i++){
				Node currentNode = trie.get(currentIndex);
				Character currentSymbol = pattern.charAt(i);

				if(currentNode.next[letterToIndex(currentSymbol)] == -1){
					trie.add(new Node());
					currentNode.next[letterToIndex(currentSymbol)] = trie.size() - 1;
					currentIndex = trie.size() - 1;
				}
				else{
					currentIndex = currentNode.next[letterToIndex(currentSymbol)];
				}
			}
			trie.get(currentIndex).end = true;
		}
		return trie;
	}

	List <Integer> solve (String text, int n, List <String> patterns) {
		List <Integer> result = new ArrayList <Integer> ();
		List<Node> trie = buildTrie(patterns);

		for(int i = 0; i < text.length(); i++){
			int symbolIndex = i;
			int trieIndex = 0;
			while(true){
				int symbolAsInteger = letterToIndex(text.charAt(symbolIndex));

				if(trie.get(trieIndex).next[symbolAsInteger] != -1){
					trieIndex = trie.get(trieIndex).next[symbolAsInteger];
					if(trie.get(trieIndex).end){
						result.add(i);
						break;
					}
					symbolIndex++;
					if(symbolIndex >= text.length()){
						break;
					}
				}
				else{
					break;
				}

			}
		}

		return result;
	}

	public void run () {
		try {
			BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
			String text = in.readLine ();
		 	int n = Integer.parseInt (in.readLine ());
		 	List <String> patterns = new ArrayList <String> ();
			for (int i = 0; i < n; i++) {
				patterns.add (in.readLine ());
			}

			List <Integer> ans = solve (text, n, patterns);

			for (int j = 0; j < ans.size (); j++) {
				System.out.print ("" + ans.get (j));
				System.out.print (j + 1 < ans.size () ? " " : "\n");
			}
		}
		catch (Throwable e) {
			e.printStackTrace ();
			System.exit (1);
		}
	}

	public static void main (String [] args) {
		new Thread (new TrieMatchingExtended ()).start ();
	}
}
