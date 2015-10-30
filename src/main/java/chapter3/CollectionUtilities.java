package chapter3;


import com.fpinjava.common.Function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CollectionUtilities {


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

    public static  <T,U> List<U> map(List<T> l, Function<T,U> f) {
        List<U> newList = new ArrayList<>();
        for(T value: l) {
            newList.add(f.apply(value));
        }
        return newList;
    }

    public static <T> T head(List<T> l) {
        if(l == null) {
            throw new IllegalStateException("head of null list");
        }

        if(l.size() == 0) {
            throw new IllegalStateException("head of empty list");
        }

        return l.get(0);
    }

    public static <T> List<T> copy(List<T> l) {
        return new ArrayList<>(l);
    }

    public static <T> List<T> tail(List<T> l) {
        if(l == null) {
            throw new IllegalStateException("tail of null list");
        }

        if(l.size() == 0) {
            throw new IllegalStateException("tail of empty list");
        }

        List<T> workList = copy(l);
        workList.remove(0);
        return workList;
    }

    public static <T> List<T> append(List<T> list, T item) {
        List<T> l2 = copy(list);
        l2.add(item);
        return l2;
    }

    public static Integer fold(List<Integer> l, Integer identity, Function<Integer, Function<Integer,Integer>> f) {
        int result = identity;
        for(Integer v: l) {
            result = f.apply(result).apply(v);
        }

        return result;
    }
}
