package chapter4;


import com.fpinjava.common.Function;

import java.util.List;

import static chapter3.CollectionUtilities.list;
import static chapter3.CollectionUtilities.map;
import static chapter3.CollectionUtilities.reverse;
import static chapter3.Range.range;
import static chapter4.StackSafeCollectionUtils.foldLeft;
import static chapter4.StackSafeCollectionUtils.foldRight;
import static com.fpinjava.common.Function.identity;

public class Composition {
    static <T> Function<T,T> composeAll(List<Function<T,T>> list) {
        return foldRight(list, identity(), x -> y -> x.compose(y));
    }

    static <T> Function<T,T> composeAllViaFoldLeft(List<Function<T,T>> list) {
        return x -> foldLeft(reverse(list), x, a -> b -> b.apply(a));
    }

    public static void main(String... args) {
        Function<Integer,Integer> add = y -> y + 1;
        System.out.println(
                composeAll(map(range(0,500),x -> add)).apply(0)
        );

        Function<String,String> f1 = x -> "(a" + x + ")";
        Function<String,String> f2 = x -> "{b" + x + "}";
        Function<String,String> f3 = x -> "[c" + x + "]";
        System.out.println(composeAllViaFoldLeft(list(f1,f2,f3)).apply("x"));
    }
}
