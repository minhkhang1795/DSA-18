public class PeakFinding {

    // Return -1 if left is higher, 1 if right is higher, 0 if peak
    private static int peakOneD(int i, int[] nums) {
        if (i > 0 && nums[i] < nums[i - 1])
            return -1;
        if (i < nums.length - 1 && nums[i] < nums[i + 1])
            return 1;
        return 0;
    }

    // Return -1 if left is higher, 1 if right is higher, 0 if peak
    private static int peakX(int x, int y, int[][] nums) {
        if (x > 0 && nums[y][x] < nums[y][x - 1])
            return -1;
        if (x < nums[0].length - 1 && nums[y][x] < nums[y][x + 1])
            return 1;
        return 0;
    }

    // Return -1 if up is higher, 1 if down is higher, 0 if peak
    private static int peakY(int x, int y, int[][] nums) {
        if (y > 0 && nums[y][x] < nums[y - 1][x])
            return -1;
        if (y < nums.length - 1 && nums[y][x] < nums[y + 1][x])
            return 1;
        return 0;
    }

    // These two functions return the index of the highest value along the X or Y axis, with the given
    // value for the other axis. Searches between hi (exclusive) and lo (inclusive)
    private static int maxXIndex(int y, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int x = lo; x < hi; x++) {
            if (maxIndex == -1 || nums[y][x] > nums[y][maxIndex])
                maxIndex = x;
        }
        return maxIndex;
    }

    private static int maxYIndex(int x, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int y = lo; y < hi; y++) {
            if (maxIndex == -1 || nums[y][x] > nums[maxIndex][x])
                maxIndex = y;
        }
        return maxIndex;
    }

    /**
     * @param nums 1D array of int
     * @return return any peak in the array
     */
    public static int findOneDPeak(int[] nums) {
        // TODO
        return findOneDPeakHelper(nums, 0, nums.length);
    }

    /**
     * Helper function for findOneDPeak. Recursively searching for a peak from low to hi, then return any peak in nums
     *
     * @param nums 1D array of int
     * @param lo   starting index to search
     * @param hi   ending index to search
     * @return return any peak in the array
     */
    private static int findOneDPeakHelper(int[] nums, int lo, int hi) {
        int mid = (lo + hi) / 2;
        int peakCheck = peakOneD(mid, nums);
        if (peakCheck == 0)
            return mid;
        else if (peakCheck == 1)
            return findOneDPeakHelper(nums, mid, hi);
        else
            return findOneDPeakHelper(nums, lo, mid);
    }

    /**
     * @param nums 2D array of int
     * @return return any peak in the array
     */
    public static int[] findTwoDPeak(int[][] nums) {
        // TODO
        return findTwoDPeakHelper_2(nums, 0, nums.length, 0, nums[0].length);
    }

    /**
     * Helper function for findTwoDPeak. Recursively searching for a peak between row up - row down and
     * column left and column right.
     * @param nums 2D array of int
     * @param ru index of row up/above
     * @param rd index of row down/below
     * @param cl index of column left
     * @param cr index of column right
     * @return return the peak's indices in the array
     */
    private static int[] findTwoDPeakHelper_2(int[][] nums, int ru, int rd, int cl, int cr) {
        int rmid = (ru + rd) / 2;
        int cmid = (cl + cr) / 2;
        int peakY = peakY(cmid, rmid, nums);
        int peakX = peakX(cmid, rmid, nums);

        if (peakX == 0 && peakY == 0)
            return new int[]{rmid, cmid};
        else {
            if (peakY == -1)
                rd = rmid;
            else if (peakY == 1)
                ru = rmid;

            if (peakX == -1)
                cr = cmid;
            else if (peakX == 1)
                cl = cmid;
        }
        return findTwoDPeakHelper_2(nums, ru, rd, cl, cr);
    }

    /**
     * Old Helper function for findTwoDPeak. Recursively searching for a peak between row up - row down and
     * column left and column right.
     * @param nums 2D array of int
     * @param r index of current row
     * @param c index of curren column
     * @return return the peak's indices in the array
     */
    private static int[] findTwoDPeakHelper(int[][] nums, int r, int c) {
        int peakY = 0;
        if (!(r == 0 || r == nums.length - 1))
            peakY = peakY(c, r, nums);
        int peakX = 0;
        if (!(c == 0 || c == nums[0].length - 1))
            peakX = peakX(c, r, nums);

        if (peakX == 0 && peakY == 0)
            return new int[]{r, c};
        else {
            if (peakY == -1)
                r /= 2;
            else if (peakY == 1)
                r = (nums.length - r) / 2 + r;

            if (peakX == -1)
                c /= 2;
            else if (peakX == 1)
                c = (nums[0].length - c) / 2 + c;
        }
        return findTwoDPeakHelper(nums, r, c);
    }

}
