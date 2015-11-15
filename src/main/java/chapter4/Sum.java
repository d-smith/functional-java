package chapter4;


import java.util.List;

import static chapter3.CollectionUtilities.head;
import static chapter3.CollectionUtilities.tail;
import static chapter3.Range.range;

public class Sum {
    static Integer sum(List<Integer> list) {
        return sum_(list,0);
    }

    static Integer sum_(List<Integer> list, int acc) {
        return list.isEmpty()
                ? acc
                : sum_(tail(list), acc + head(list));
    }

    public static void main(String... args) {
        List<Integer> l = range(1,1000);
        System.out.println(sum(l));
    }
}
