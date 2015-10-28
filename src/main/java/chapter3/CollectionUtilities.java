package chapter3;


import com.fpinjava.common.Function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CollectionUtilities {
   public static  <T,U> List<U> map(List<T> l, Function<T,U> f) {
        List<U> newList = new ArrayList<>();
        for(T value: l) {
            newList.add(f.apply(value));
        }
        return newList;
    }

    public static <T> List<T> list() {
        return Arrays.asList();
    }

    public static <T> List<T> list(T t) {
        return Arrays.asList(t);
    }

    public static <T> List<T> list(List<T> l) {
        return new ArrayList<>(l);
    }

    @SafeVarargs
    public static <T> List<T> list(T... t) {
        return Arrays.asList(t);
    }
}
