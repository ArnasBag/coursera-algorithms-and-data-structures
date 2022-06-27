import java.util.*;

class EditDistance {
  public static int EditDistance(String s, String t) {
    
    int[][] table = new int[s.length() + 1][t.length() + 1];

    for(int i = 0; i <= s.length(); i++){
      for(int j = 0; j <= t.length(); j++){
        
        //base case is empty string so in order to form string t we need the same amount of operations as t has characters
        if(i == 0){
          table[i][j] = j;
        }
        else if(j == 0){
          table[i][j] = i;
        }
        //If the characters match it means no operation must be done
        else if(s.charAt(i - 1) == t.charAt(j - 1)){
          table[i][j] = table[i - 1][j - 1];
        }
        //Otherwise we perform either deletion, insertion or substitution depending on which action would require the least amount of operations
        else{
          table[i][j] = minValue(table[i][j - 1], table[i - 1][j], table[i - 1][j - 1]) + 1;
        }
      }
    }
    return table[s.length()][t.length()];
  }
  public static int minValue(int a, int b, int c){
    if(a <= b && a <= c) return a;
    if(b <= a && b <= c) return b;
    else return c;
  }
  public static void main(String args[]) {
    Scanner scan = new Scanner(System.in);

    String s = scan.next();
    String t = scan.next();

    System.out.println(EditDistance(s, t));
  }

}
