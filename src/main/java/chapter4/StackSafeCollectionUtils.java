package chapter4;

import com.fpinjava.common.Function;

import java.util.List;

import static chapter3.CollectionUtilities.head;
import static chapter3.CollectionUtilities.tail;
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
}

