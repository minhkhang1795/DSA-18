import java.util.*;

public class FrequencyPrint {

    static String frequencyPrint(String s) {
        // TODO
        String[] arr = s.split(" ");
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (String word : arr)
            hashMap.merge(word, 1, Integer::sum);

        List<Map.Entry<String, Integer>> list = new ArrayList<>(hashMap.entrySet());
        list.sort(Comparator.comparingInt(o -> (int) o.getValue()));

        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Integer> aList : list)
            for (int j = 0; j < aList.getValue(); j++)
                result.append(aList.getKey()).append(" ");

        return result.toString();
    }

}
