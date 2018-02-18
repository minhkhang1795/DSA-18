import java.util.HashMap;

public class LargestSubArray {
    static int[] largestSubarray(int[] nums) {
        // TODO
        int[] result = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        int value = 0;
        for (int i = 0; i < nums.length; i++) {
            value += nums[i] == 0 ? -1 : 1;
            map.put(value, i);
        }

        int maxLength = -1;
        if (map.containsKey(0)) {
            maxLength = map.get(0);
            result[0] = 0;
            result[1] = maxLength;
        }
        value = 0;

        for (int i = 0; i < nums.length; i++) {
            value += nums[i] == 0 ? -1 : 1;
            if (map.containsKey(value)) {
                int tempIndex = map.get(value);
                int tempLength = tempIndex - i;
                if (maxLength < tempLength) {
                    maxLength = tempLength;
                    result[0] = i + 1;
                    result[1] = tempIndex;
                }
            }
        }

        return result;
    }
}
