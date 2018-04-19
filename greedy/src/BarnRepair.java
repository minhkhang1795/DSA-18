import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class BarnRepair {
    public static int solve(int M, int[] occupied) {
        // TODO
        Arrays.sort(occupied);
        PriorityQueue<Integer> empty = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < occupied.length - 1; i++) {
            if (occupied[i + 1] - occupied[i] > 1) {
                empty.add(occupied[i + 1] - occupied[i] - 1);
            }
        }
        int sum = occupied[occupied.length - 1] - occupied[0] + 1;
        while (M > 1) {
            if (!empty.isEmpty())
                sum -= empty.poll();
            M--;
        }
        return sum;
    }
}