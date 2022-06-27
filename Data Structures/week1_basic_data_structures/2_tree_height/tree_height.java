import java.util.*;
import java.io.*;

public class tree_height {
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

	public class TreeHeight {
		int n;
		int[] parent;
		//Represents tree as list of list. Root is integer index, at root index in children array are children of main root, at main root children indexes are
		//children of those children and so on...
		/*For example:
		  Input is 4 -1 4 1 1 
		  then the root index is 1, because -1 represents the root and it's index is 1
		  then the children of root are array indexes 3 and 4 represented by numbers 1 and 1
		  and also child 4 is the root of child 0 and 2 because the numbers in array match the index 4
		  so [1]{3, 4} [3]{} [4]{0, 2} [0]{} [2]{}
		*/
		ArrayList<ArrayList<Integer>> children = new ArrayList<ArrayList<Integer>>();
		
		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			parent = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = in.nextInt();
			}
		}
		int compute(int root){
			int max = 0;
			//Starts from root location in array. It then recursively sets children of main root as root.
			for(int i = 0; i < children.get(root).size(); i++){
				max = Math.max(max, compute(children.get(root).get(i)));
			}
			return max + 1;
		}

		int computeHeight() {			
			int root = 0;
			//Initialize array with empty arrays
			for(int i = 0; i < n; i++){
				children.add(new ArrayList<Integer>());
			}
			
			//Finds the root index and adds children to their corresponding indexes.
			for(int i = 0; i < n; i++){
				int parent_index = parent[i];
				if(parent_index == -1) root = i;
				else children.get(parent_index).add(i);
			}
			return compute(root);			
		}
	}

	static public void main(String[] args) throws IOException {
            new Thread(null, new Runnable() {
                    public void run() {
                        try {
                            new tree_height().run();
                        } catch (IOException e) {
                        }
                    }
                }, "1", 1 << 26).start();
	}
	public void run() throws IOException {
		TreeHeight tree = new TreeHeight();
		tree.read();
		System.out.println(tree.computeHeight());
	}
}
