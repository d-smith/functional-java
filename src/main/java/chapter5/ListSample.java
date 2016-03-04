package chapter5;


import com.fpinjava.common.Function;

import static chapter5.List.list;

public class ListSample {

    public static void main(String... args) {
        List<Integer> ex1 = list();
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
        System.out.print(List.sum(list(1,2,3,4)));
    }
}
