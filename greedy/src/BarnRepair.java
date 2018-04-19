import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class BarnRepair {
    public static int solve(int M, int[] occupied) {
        // TODO
        Arrays.sort(occupied);
        PriorityQueue<Integer> empty = new PriorityQueue<>(Collections.reverseOrder());
        ArrayList<Integer> list = new ArrayList<>();
        int count = 1;
        for (int i = 0; i < occupied.length - 1; i++) {
            if (occupied[i] == occupied[i + 1] - 1) {
                count++;
            } else {
                list.add(count);
                list.add(occupied[i + 1] - occupied[i] - 1);
                count = 1;
            }
        }
        list.add(count);
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            if (i % 2 != 0) {
                empty.add(list.get(i));
            }
            sum += list.get(i);
        }
        while (M > 1) {
            if (!empty.isEmpty())
                sum -= empty.poll();
            M--;
        }
        return sum;
    }
}