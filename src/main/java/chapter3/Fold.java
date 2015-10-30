package chapter3;


import java.util.List;

import static chapter3.CollectionUtilities.fold;
import static chapter3.CollectionUtilities.list;

public class Fold {
    public static void main(String... args) {
        List<Integer> l = list(1,2,3,4,5);
        int result = fold(l,0,x -> y -> x + y);
        System.out.println(result);
    }
}
