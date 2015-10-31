package chapter3;


import com.fpinjava.common.Function;

import java.util.List;

import static chapter3.CollectionUtilities.fold;
import static chapter3.CollectionUtilities.foldLeft;
import static chapter3.CollectionUtilities.list;

public class Fold {

    public static String addSI(String s, Integer i) {
        return "(" + s + " + " + i + ")";
    }

    public static void main(String... args) {
        List<Integer> l = list(1,2,3,4,5);
        int result = fold(l,0,x -> y -> x + y);
        System.out.println(result);

        Function<String, Function<Integer, String>> f = x -> y -> addSI(x,y);

        String r2 = foldLeft(l,"0",f);
        System.out.println(r2);
    }
}
