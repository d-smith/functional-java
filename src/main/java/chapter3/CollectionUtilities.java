package chapter3;


import com.fpinjava.common.Function;

import java.util.ArrayList;
import java.util.List;

public class CollectionUtilities {
   public static  <T,U> List<U> map(List<T> l, Function<T,U> f) {
        List<U> newList = new ArrayList<>();
        for(T value: l) {
            newList.add(f.apply(value));
        }
        return newList;
    }
}
