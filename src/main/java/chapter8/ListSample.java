package chapter8;


import chapter7.Result;
import com.fpinjava.common.Function;

import static chapter8.List.flattenResult;
import static chapter8.List.list;

public class ListSample {

    public static void main(String... args) {
        List<Integer> ex1 = list();
        System.out.println("headOption empty list " + ex1.headOption());

        List<Integer> ex2 = list(1);
        List<Integer> ex3 = list(1, 2);

        System.out.println(ex3);

        List<Integer> ex4 = ex2.cons(0);
        System.out.println(ex4);

        System.out.println("set head on ex4 to -1");
        ex4 = ex4.setHead(-1);
        System.out.println(ex4);

        System.out.println("\nA list:");
        List<Integer> bl = list(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15);
        System.out.println(bl);
        System.out.println(bl.drop(1));
        System.out.println(bl.drop(5));
        System.out.println(bl.drop(100));

        Function<Integer,Boolean> lessThanTen = (i) -> i < 10;
        System.out.println(bl.dropWhile(lessThanTen));

        System.out.println(List.concat(bl, bl));
        System.out.println(bl.init());
        System.out.println(List.sum(list(1,2,3,4)));
        System.out.println(List.product(list(1.0,2.0,3.0,4.0)));
        System.out.println("length of big list: " + bl.length());
        System.out.println("memoized length: " + bl.lengthMemoized());

        System.out.println(List.triple(bl));

        List<Double> dl = list(1.0,2.0, 3.0);
        System.out.println(List.doubleToString(dl));

        System.out.println(bl.map(x -> x*3));

        System.out.println(bl.filter(x -> x % 2 == 0));
        System.out.println(bl.filterViaFlatmap(x -> x % 2 == 0));

        System.out.println(list(1,2,3).flatMap(x -> list(x,-x)));

        System.out.println(list(1,2,3,4,5).reverse());

        System.out.println(List.flatten(list(list(1,2,3),list(4,5,6))));

        System.out.println(bl.lastOption());

        List<Result<Integer>> rl = list(Result.success(1), Result.failure("dang it"), Result.empty());
        System.out.println(flattenResult(rl));

        System.out.println(List.zipWith(bl,bl, x -> y -> x + y));

        List<String> sl1 = list("a","b","c");
        List<String> sl2 = list("d","e","f");

        System.out.println(List.product(sl1, sl2, x -> y -> x + y));
    }
}
