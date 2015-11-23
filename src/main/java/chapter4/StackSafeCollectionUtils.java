package chapter4;

import com.fpinjava.common.Function;

import java.util.List;

import static chapter3.CollectionUtilities.*;
import static chapter4.TailCall.ret;
import static chapter4.TailCall.sus;

public class StackSafeCollectionUtils {

    public static <T,U> U foldLeft(List<T> ts, U identity, Function<U,Function<T,U>> f) {
        return foldLeft_(ts, identity, f).eval();
    }

    public static <T,U> TailCall<U> foldLeft_(List<T> ts, U identity, Function<U,Function<T,U>> f) {
        return ts.isEmpty()
                ? ret(identity)
                : sus(() -> foldLeft_(tail(ts), f.apply(identity).apply(head(ts)), f));
    }

    public static List<Integer> recursiveRange(int start, int end) {
        return recursiveRange_(list(), start,end).eval();
    }

    public static TailCall<List<Integer>> recursiveRange_(List<Integer> acc, int start, int end) {
        return end <= start ?
                ret(acc) :
                sus(() -> recursiveRange_(append(acc, start), start + 1, end));
    }

    public static <T,U> U foldRight(List<T> l, U identity, Function<T, Function<U,U>> f) {
        return foldRight_(identity, reverse(l), f).eval();
    }

    private static <T,U> TailCall<U> foldRight_(U acc, List<T> l, Function<T, Function<U,U>> f) {
        return l.isEmpty()
                ? ret(acc)
                : sus(() -> foldRight_(f.apply(head(l)).apply(acc), tail(l),f));
    }

    public static void main(String... args) {
        List<Integer> r1 = recursiveRange(1,5);
        System.out.println(r1);
    }
}

