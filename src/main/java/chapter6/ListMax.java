package chapter6;

import chapter5.List;
import com.fpinjava.common.Function;

import java.lang.Comparable;

public class ListMax {
    static <A extends Comparable<A>> Function<List<A>, Option<A>> max() {
        return xs -> xs.isEmpty()
                ? Option.none()
                : Option.some(xs.foldLeft(xs.head(),x -> y -> x.compareTo(y) > 0 ? x : y));
    }

    public static void main(String[] args) {
        Function<List<Integer>, Option<Integer>> max = ListMax.max();
        List<Integer> empty = List.list();
        System.out.println(String.format("List of empty is %s",max.apply(empty)));
        List<Integer> aList = List.list(1,2,3,4,5);
        System.out.println(String.format("List of aList is %s",max.apply(aList)));
    }

}

