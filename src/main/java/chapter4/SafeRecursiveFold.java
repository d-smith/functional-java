package chapter4;

import com.fpinjava.common.*;
import com.fpinjava.common.Function;

import java.util.List;

import static chapter3.CollectionUtilities.list;
import static chapter4.StackSafeCollectionUtils.foldLeft;

public class SafeRecursiveFold {
    public static String addSI(String s, Integer i) {
        return "(" + s + " + " + i + ")";
    }

    public static void main(String... args) {
        List<Integer> l = list(1,2,3,4,5);
        Function<String, com.fpinjava.common.Function<Integer, String>> f = x -> y -> addSI(x,y);
        String flResult = foldLeft(l,"0",f);
        System.out.println(flResult);
    }
}
