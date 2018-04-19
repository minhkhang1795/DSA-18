public class FirstFailingVersion {

    public static long firstBadVersion(long n, IsFailingVersion isBadVersion) {
        // TODO
        long left = 0;
        long right = n;
        while (left <= right) {
            long mid = (left + right) / 2;
            if (!isBadVersion.isFailingVersion((mid)) && isBadVersion.isFailingVersion((mid + 1)))
                return mid + 1;
            else if (!isBadVersion.isFailingVersion((mid)) && !isBadVersion.isFailingVersion(mid + 1)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
