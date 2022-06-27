import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

class Bracket {
    Bracket(char type, int position) {
        this.type = type;
        this.position = position;
    }

    boolean Match(char c) {
        if (this.type == '[' && c == ']')
            return true;
        if (this.type == '{' && c == '}')
            return true;
        if (this.type == '(' && c == ')')
            return true;
        return false;
    }

    char type;
    int position;
}

class check_brackets {
    public static void main(String[] args) throws IOException {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        String text = reader.readLine();
        Bracket badBracket = new Bracket('a', 1);
        boolean success = false;

        Stack<Bracket> opening_brackets_stack = new Stack<Bracket>();
        for (int position = 0; position < text.length(); ++position) {
            char next = text.charAt(position);
            if (next == '(' || next == '[' || next == '{') {
                Bracket bracket = new Bracket(next, position);
                opening_brackets_stack.push(bracket);
            }           
            if (next == ')' || next == ']' || next == '}') {
                if(opening_brackets_stack.empty()){
                    success = false;
                    badBracket.position = position + 1;
                    break;
                }
                Bracket popped = opening_brackets_stack.pop();
                if(!popped.Match(next)){
                    badBracket.position = position + 1;
                    success = false;
                    break;
                }
                else success = true;                    
            }
            if(position == text.length() - 1 && !opening_brackets_stack.empty()){
                badBracket.position = opening_brackets_stack.pop().position + 1;
                success = false;
            }
            
        }
        if(success && opening_brackets_stack.empty()) System.out.println("Success");
        else System.out.println(badBracket.position);     
    }
}
