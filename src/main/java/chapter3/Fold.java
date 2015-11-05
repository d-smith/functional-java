package chapter3;


import com.fpinjava.common.Function;

import java.util.List;

import static chapter3.CollectionUtilities.*;

public class Fold {

    public static String addSI(String s, Integer i) {
        return "(" + s + " + " + i + ")";
    }

    public static String addIS(Integer i, String s) {
        return "(" + i + " + " + s + ")";
    }

    public static Integer doubleIt(Integer i) { return 2 * i; }

    public static void main(String... args) {
        List<Integer> l = list(1,2,3,4,5);
        int result = fold(l,0,x -> y -> x + y);
        System.out.println(result);

        Function<String, Function<Integer, String>> f = x -> y -> addSI(x,y);

        String flResult = foldLeft(l,"0",f);
        System.out.println(flResult);

        Function<Integer, Function<String,String>> g = x -> y -> addIS(x,y);
        String friResult = iterativeFoldRight(l,"0",g);
        System.out.println(friResult);

        String fiResult = iterativeFoldRight(l,"0",g);
        System.out.println(fiResult);

        List<Integer> rl = reverse(l);
        System.out.println(rl);
        
        List<Integer> doubled = mapViaFoldLeft(l, x -> doubleIt(x));
        System.out.println(doubled);

        doubled = mapViaFoldRight(l, x -> doubleIt(x));
        System.out.println(doubled);

    }
}
