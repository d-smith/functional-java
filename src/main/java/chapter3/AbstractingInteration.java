package chapter3;


import com.fpinjava.common.Function;

import java.util.ArrayList;
import java.util.List;

import static chapter3.CollectionUtilities.map;

public class AbstractingInteration {
    public static void main(String... args) {
        Function<Integer, Double> addTwentyPercent = x -> x * 1.2;

        List<Integer> aList = new ArrayList<>();
        aList.add(1);
        aList.add(10);
        aList.add(100);

        List<Double> plus20pct = map(aList, addTwentyPercent);

        for(Double v: plus20pct) {
            System.out.println(v);
        }

    }
}
