import java.util.Scanner;

public class ChangeDP {
    private static int getChange(int m) {
        int[] denominations = new int[]{1, 3, 4};

        int[] minimumNumOfCoins = new int[m + 1];
        minimumNumOfCoins[0] = 0;

        //bottom-up approach of finding the minimum number of ways to change values leading up and including the value of money
        for(int i = 1; i <= m; i++){
            //Setting the value to be the biggest possible for further comparison
            minimumNumOfCoins[i] = Integer.MAX_VALUE;
            //Testing all denominations
            for(int j = 0; j < denominations.length; j++){
                if(i >= denominations[j]){
                    //Subtracts denomination from money, looks at how many denominations it took to form result and adds one for one more denomination
                    int numOfCoins = minimumNumOfCoins[i - denominations[j]] + 1;
                    //Checks if the amount of denominations used is less then with previous denomination
                    if(numOfCoins < minimumNumOfCoins[i]){
                        minimumNumOfCoins[i] = numOfCoins;
                    }
                } 
            }
        }
        return minimumNumOfCoins[m];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));

    }
}

