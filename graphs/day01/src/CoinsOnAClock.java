import java.util.ArrayList;
import java.util.List;

public class CoinsOnAClock {

    public static List<char[]> coinsOnAClock(int pennies, int nickels, int dimes, int hoursInDay) {
        // TODO
        List<char[]> result = new ArrayList<>();
        coinsOnAClock(new char[hoursInDay], 0, pennies, nickels, dimes, hoursInDay, result);
        return result;
    }

    private static void coinsOnAClock(char[] solution, int position, int pennies, int nickels, int dimes, int hoursInDay, List<char[]> result) {
        if (pennies + nickels + dimes == 0) {
            result.add(solution.clone());
            return;
        }
        if (pennies != 0 && solution[position] == '\0') {
            solution[position] = 'p';
            coinsOnAClock(solution, (position + 1) % hoursInDay, pennies - 1, nickels, dimes, hoursInDay, result);
            solution[position] = '\0';
        }
        if (nickels != 0 && solution[position] == '\0') {
            solution[position] = 'n';
            coinsOnAClock(solution, (position + 5) % hoursInDay, pennies, nickels - 1, dimes, hoursInDay, result);
            solution[position] = '\0';
        }
        if (dimes != 0 && solution[position] == '\0') {
            solution[position] = 'd';
            coinsOnAClock(solution, (position + 10) % hoursInDay, pennies, nickels, dimes - 1, hoursInDay, result);
            solution[position] = '\0';
        }
    }
}


