package chapter7;


import chapter5.List;
import com.fpinjava.common.Function;

public class ListMax {
    static <A extends Comparable<A>> Function<List<A>, Either<String,A>> max() {
        return xs -> xs.isEmpty()
                ? Either.left("max called on empty list")
                : Either.right(xs.foldLeft(xs.head(),x -> y -> x.compareTo(y) > 0 ? x : y));
    }

    static Integer getDefault() { return 1000; }

    public static void main(String[] args) {
        Function<List<Integer>, Either<String,Integer>> max = ListMax.max();
        List<Integer> empty = List.list();
        System.out.println(String.format("List of empty max is %s",max.apply(empty)));
        List<Integer> aList = List.list(1,2,3,4,5);
        System.out.println(String.format("List of aList max is %s",max.apply(aList)));

        Function<Integer,Integer> timesTwo = x -> x * 2;

        System.out.println(max.apply(aList).map(timesTwo));
    }
}
