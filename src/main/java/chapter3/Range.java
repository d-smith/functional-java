package chapter3;


import java.util.ArrayList;
import java.util.List;

import static chapter3.CollectionUtilities.*;

public class Range {
    public static List<Integer> range(int start, int end) {
        List<Integer> list = new ArrayList<>();
        int temp = start;
        while(temp <= end) {
            list = append(list, temp);
            temp = temp + 1;
        }

        return list;
    }

    public static List<Integer> recursiveRange(int start, int end) {
        return end <= start ?
                list() :
                prepend(start, recursiveRange(start + 1,end));
    }

    public static List<Integer> rangeViaUnfold(int start, int end) {
        return unfold(start, x -> x + 1, y -> end >= y);
    }
}
