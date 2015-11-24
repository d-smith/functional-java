package chapter4;


import com.fpinjava.common.Function;

import java.util.List;

import static chapter3.CollectionUtilities.map;
import static chapter3.Range.range;
import static chapter4.StackSafeCollectionUtils.foldRight;
import static com.fpinjava.common.Function.identity;

public class Composition {
    static <T> Function<T,T> composeAll(List<Function<T,T>> list) {
        return foldRight(list, identity(), x -> y -> x.compose(y));
    }

    public static void main(String... args) {
        Function<Integer,Integer> add = y -> y + 1;
        System.out.println(
                composeAll(map(range(0,500),x -> add)).apply(0)
        );
    }
}
