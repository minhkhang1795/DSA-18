import java.util.ArrayList;
import java.util.List;

public class CoinsOnAClock {

    public static List<char[]> coinsOnAClock(int pennies, int nickels, int dimes, int hoursInDay) {
        // TODO
        List<char[]> result = new ArrayList<>();
        boolean[] used = new boolean[hoursInDay];
        coinsOnAClock(new char[hoursInDay], 0, used, pennies, nickels, dimes, hoursInDay, result);
        return result;
    }

    private static void coinsOnAClock(char[] solution, int position, boolean[] used, int pennies, int nickels, int dimes, int hoursInDay, List<char[]> result) {
        if (pennies + nickels + dimes == 0) {
            result.add(solution);
            return;
        }
        boolean[] newUsed = used.clone();
        newUsed[position] = true;
        if (pennies != 0 && !used[position]) {
            solution[position] = 'p';
            coinsOnAClock(solution.clone(), (position + 1) % hoursInDay, newUsed, pennies - 1, nickels, dimes, hoursInDay, result);
        }
        if (nickels != 0 && !used[position]) {
            solution[position] = 'n';
            coinsOnAClock(solution.clone(), (position + 5) % hoursInDay, newUsed, pennies, nickels - 1, dimes, hoursInDay, result);
        }
        if (dimes != 0 && !used[position]) {
            solution[position] = 'd';
            coinsOnAClock(solution.clone(), (position + 10) % hoursInDay, newUsed, pennies, nickels, dimes - 1, hoursInDay, result);
        }
    }
}


