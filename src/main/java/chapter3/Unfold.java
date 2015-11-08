package chapter3;




import com.fpinjava.common.Function;

import java.util.List;

import static chapter3.Range.range;
import static chapter3.CollectionUtilities.unfold;

public class Unfold {
    public static void main(String... args) {
        List<Integer> r1 = range(1,5);
        System.out.println(r1);

        Function<Integer,Integer> next = x -> x + 1;
        Function<Integer,Boolean> keepGoing = x -> x == 6 ? false : true;
        List<Integer> r2 = unfold(1,next, keepGoing);
        System.out.println(r2);
    }
}
