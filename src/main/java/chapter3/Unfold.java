package chapter3;




import com.fpinjava.common.Function;

import java.util.List;

import static chapter3.Range.range;
import static chapter3.CollectionUtilities.unfold;
import static chapter3.Range.rangeViaUnfold;
import static chapter3.Range.recursiveRange;

public class Unfold {
    public static void main(String... args) {
        List<Integer> r1 = range(1,5);
        System.out.println(r1);

        recursiveRange(1,5);
        System.out.println(r1);

        Function<Integer,Integer> next = x -> x + 1;
        Function<Integer,Boolean> keepGoing = x -> x == 6 ? false : true;
        List<Integer> r2 = unfold(1,next, keepGoing);
        System.out.println(r2);

        System.out.println(rangeViaUnfold(1,5));
    }
}
