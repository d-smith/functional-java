package chapter7;


import com.fpinjava.common.Function;

public class Lift {
    public static void main(String[] args) {
        Function<Integer,Integer> times2 = x -> x * 2;
        System.out.println(times2.apply(2));
        Function<Result<Integer>,Result<Integer>> t2r = Result.lift(times2);
        System.out.println(t2r.apply(Result.success(2)));
    }
}
