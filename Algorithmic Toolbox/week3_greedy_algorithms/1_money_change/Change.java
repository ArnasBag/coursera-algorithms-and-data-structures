import java.util.Scanner;

public class Change {
    private static int getChange(int m) {
        int count = 0;
        //safe move - take the largest possible coin from the total 
        while(m > 0){
            if(m >= 10){
                m -= 10;
                count++;
            }
            else if(m >= 5){
                m -= 5;
                count++;
            }
            else if(m >= 1){
                m -= 1;
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));

    }
}

