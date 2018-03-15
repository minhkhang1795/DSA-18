package divide_and_conquer;

import java.util.ArrayList;
import java.util.List;

public class RadioTowers {
    public static class Tower {
        public double x;
        public double y;

        public Tower(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    private static double getDist(Tower t1, Tower t2) {
        double xDiff = t1.x - t2.x;
        double yDiff = t1.y - t2.y;
        return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
    }

    private static boolean isWithin(Tower t1, Tower t2, int dist) {
        return getDist(t1, t2) <= dist;
    }

    // Strip contains a list of Towers sorted by x-coordinate, whose y-coordinates all fall in a 2-mile strip
    // Return true if no two towers are within 1 mile
    public static boolean checkStrip(List<Tower> strip) {
        // TODO
        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < i + 8; j++) {
                if (j < strip.size() && isWithin(strip.get(i), strip.get(j), 1)) {
                    return false;
                }
            }
        }
        return true;
    }

    // Return true if no two towers are within distance 1 of each other
    public static boolean validTowers(List<Tower> Lx, List<Tower> Ly) {
        assert Lx.size() == Ly.size();
        // TODO
        if (Ly.get(Ly.size() - 1).y - Ly.get(0).y <= 2) {
            return checkStrip(Lx);
        }

        List<Tower> topLy = Ly.subList(0, Ly.size() / 2);
        List<Tower> botLy = Ly.subList(Ly.size() / 2, Ly.size());
        List<Tower> topLx = getLxFromLy(Lx, topLy.get(0).y, topLy.get(topLy.size()-1).y);
        List<Tower> botLx = getLxFromLy(Lx, botLy.get(0).y, botLy.get(botLy.size()-1).y);
        return validTowers(topLx, topLy) && validTowers(botLx, botLy);
    }

    private static List<Tower> getLxFromLy(List<Tower> Lx, double ymin, double ymax) {
        List<Tower> result = new ArrayList<>();
        for (Tower t : Lx) {
            if (t.y <= ymax && t.y >= ymin)
                result.add(t);
        }
        return result;
    }
}
