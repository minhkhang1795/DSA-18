import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class TripleSum {

    static int tripleSum(int arr[], int sum) {
        // TODO
        HashSet<Integer> set = new HashSet<>();
        for (int i : arr)
            set.add(i);

        int count = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int temp = sum - arr[i] - arr[j];
                if (temp == arr[i] || temp == arr[j])
                    continue;
                if (set.contains(temp))
                    count++;
            }
        }
        return count / 3;
    }
}
