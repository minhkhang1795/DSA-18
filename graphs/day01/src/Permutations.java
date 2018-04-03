import java.util.*;

public class Permutations {

    public static List<List<Integer>> permutations(List<Integer> A) {
        // TODO
        List<List<Integer>> permutations = new LinkedList<>();
        Set<Integer> unused = new HashSet<>(A);
        permutations(new LinkedList<>(), unused, permutations);
        return permutations;
    }

    private static void permutations(LinkedList<Integer> current, Set<Integer> unused, List<List<Integer>> permutations) {
        if (unused.size() == 0) {
            permutations.add(new LinkedList<>(current));
            return;
        }
        for (Integer a : new LinkedList<>(unused)) {
            current.addLast(a);
            unused.remove(a);
            permutations(current, unused, permutations);
            unused.add(a);
            current.removeLast();
        }
    }
}
