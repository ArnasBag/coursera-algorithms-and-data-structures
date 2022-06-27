import java.util.*;

public class LargestNumber {
    private static String largestNumber(String[] a) {
        Arrays.sort(a, new Comparator<String>(){
            @Override
            public int compare(String left, String right){
                String a = left + right;
                String b = right + left;
                if(a.compareTo(b) > 0) return -1;
                else if(a.compareTo(b) < 0) return 1;
                else{
                    return 0;
                }
            }
        });
        String result = "";
        for(int i = 0; i < a.length; i++){
            result += a[i];
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] a = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.next();
        }
        System.out.println(largestNumber(a));
    }
}

