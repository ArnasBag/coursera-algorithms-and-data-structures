import java.util.*;

public class Inversions {
    private static long mergeSort(int[] source, int left, int right){
        long inversionCount = 0;
        if(right > left){
            int mid = (left + right) / 2;
            //All inversions of left side
            inversionCount += mergeSort(source, left, mid);
            //All inversion of right side
            inversionCount += mergeSort(source, mid + 1, right);
            //All split inversions between two sides
            inversionCount += merge(source, left, mid, right);
        }
        return inversionCount;
    }

    private static long merge(int[] source, int left, int mid, int right){

        //Define temporary subarrays
        int[] leftArray = new int[mid - left + 1];
        int[] rightArray = new int[right - mid];

        //Copy values from source array to left and right subarrays depending on mid point
        for(int i = 0; i < leftArray.length; i++){
            leftArray[i] = source[left + i];
        }
        for(int i = 0; i < rightArray.length; i++){
            rightArray[i] = source[mid + i + 1];
        }

        // i, j - indexes for looping through left/right subarrays
        int count = 0;
        int i = 0, j = 0;

        //k - index for merged array
        int k = left;
        while(i < leftArray.length && j < rightArray.length){
            if(leftArray[i] <= rightArray[j]){
                source[k] = leftArray[i];
                i++;
            }
            else{
                source[k] = rightArray[j];
                j++;
                //If an element is copied from the right subarray, it means that there exist inversions
                //because left value is bigger than right. It also means that the rest of the values of left
                //subarray are bigger than the right value, so the amount of inversions are equal to the 
                //rest of left values.
                count += leftArray.length - i;
            }
            k++;
        }
        //Add the rest of values if one of the subarrays were bigger than the other
        while(i < leftArray.length){
            source[k] = leftArray[i];
            i++; k++;
        }
        while(j < rightArray.length){
            source[k] = rightArray[j];
            j++; k++;
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        System.out.println(mergeSort(a, 0, a.length - 1));
    }
}

