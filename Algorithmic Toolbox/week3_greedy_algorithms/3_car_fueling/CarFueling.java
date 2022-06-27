import java.util.*;
import java.io.*;

public class CarFueling {
    //safe move - travel longest route possible before refueling
    static int computeMinRefills(int dist, int tank, int[] stops) {
        int refills = 0;
        int currentStop = 0;
        int n = stops.length;

        int[] reformedStops = new int[n + 2];
        reformedStops[0] = 0;

        for(int i = 1; i < n + 1; i++){
            reformedStops[i] = stops[i - 1];
        }
        reformedStops[n + 1] = dist;

        while(currentStop <= n){
            int lastStop = currentStop;
            while(currentStop <= n && (reformedStops[currentStop + 1] - reformedStops[lastStop]) <= tank){
                currentStop++;
            }
            if(currentStop == lastStop){
                return -1;
            }
            if(currentStop <= n){
                refills++;
            }
        }
        return refills;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dist = scanner.nextInt();
        int tank = scanner.nextInt();
        int n = scanner.nextInt();
        int stops[] = new int[n];
        for (int i = 0; i < n; i++) {
            stops[i] = scanner.nextInt();
        }

        System.out.println(computeMinRefills(dist, tank, stops));
    }
}
