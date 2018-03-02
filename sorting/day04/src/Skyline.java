import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Skyline {

    static class Point {
        int x, y;

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Building {
        private int l, r, h;

        Building(int l, int r, int h) {
            this.l = l;
            this.r = r;
            this.h = h;
        }
    }

    // Given an array of buildings, return a list of points representing the skyline
    public static List<Point> skyline(Building[] B) {
        // TODO
        List<Building> buildings = skylineHelper(new ArrayList<>(Arrays.asList(B)));

        ArrayList<Point> result = new ArrayList<>();
        for (Building building : buildings)
            result.add(new Point(building.l, building.h));

        Building last = buildings.get(buildings.size() - 1);
        result.add(new Point(last.r, 0));
        return result;
    }

    public static List<Building> skylineHelper(List<Building> B) {
        if (B.size() < 2)
            return B;
        int mid = B.size() / 2;
        List<Building> left = skylineHelper(B.subList(0, mid));
        List<Building> right = skylineHelper(B.subList(mid, B.size()));
        return merge(left, right);
    }

    private static ArrayList<Building> merge(List<Building> A, List<Building> B) {
        ArrayList<Building> result = new ArrayList<>();
        int a_index = 0, b_index = 0;
        Building a = A.get(a_index);
        Building b = B.get(b_index);
        if (a.l < b.l) {
            result.add(a);
            a_index++;
        } else {
            result.add(b);
            b_index++;
        }
        while (a_index < A.size() && b_index < B.size()) {
            a = A.get(a_index);
            b = B.get(b_index);
            if (a.l < b.l) {
                // Overlay a or b with the last building in list
                ArrayList<Building> newBuildings = overlay(result.get(result.size() - 1), a);
                a_index++;
                result.remove(result.size() - 1);
                result.addAll(newBuildings);
            } else {
                ArrayList<Building> newBuildings = overlay(result.get(result.size() - 1), b);
                b_index++;
                result.remove(result.size() - 1);
                result.addAll(newBuildings);
            }
        }

        while (a_index < A.size()) {
            ArrayList<Building> newBuildings = overlay(result.get(result.size() - 1), A.get(a_index++));
            result.remove(result.size() - 1);
            result.addAll(newBuildings);
        }
        while (b_index < B.size()) {
            ArrayList<Building> newBuildings = overlay(result.get(result.size() - 1), B.get(b_index++));
            result.remove(result.size() - 1);
            result.addAll(newBuildings);
        }

        return result;
    }

    private static ArrayList<Building> overlay(Building a, Building b) {
        ArrayList<Building> result = new ArrayList<>();
        if (a.r <= b.l || b.r <= a.l) {
            if (b.r <= a.l)
                swap(a, b);

            if (a.h == b.h && a.r == b.l) {
                result.add(new Building(a.l, b.r, a.h));
            } else {
                result.add(a);
                if (a.r != b.l)
                    result.add(new Building(a.r, b.l, 0));
                result.add(b);
            }
        } else {
            if (a.l >= b.l)
                swap(a, b);

            // After swap, a.l < b.l (always)
            if (a.r <= b.r) {
                if (a.h == b.h) {
                    result.add(new Building(a.l, b.r, a.h));
                } else if (a.h < b.h) {
                    result.add(new Building(a.l, b.l, a.h));
                    result.add(new Building(b.l, b.r, b.h));
                } else {
                    result.add(new Building(a.l, a.r, a.h));
                    result.add(new Building(a.r, b.r, b.h));
                }
            } else {
                if (a.h == b.h) {
                    result.add(new Building(a.l, a.r, a.h));
                } else if (a.h < b.h) {
                    result.add(new Building(a.l, b.l, a.h));
                    result.add(new Building(b.l, b.r, b.h));
                    result.add(new Building(b.r, a.r, a.h));
                } else {
                    result.add(new Building(a.l, a.r, a.h));
                }
            }
        }

        for (int i = 0; i < result.size(); i++)
            if (result.get(i).r - result.get(i).l == 0)
                result.remove(i--);

        return result;
    }

    private static void swap(Building a, Building b) {
        Building c = a;
        a = b;
        b = c;
    }
}
