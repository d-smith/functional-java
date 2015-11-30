package chapter4;


import com.fpinjava.common.Function;
import com.fpinjava.common.Tuple;

import java.math.BigInteger;
import java.util.List;

import static chapter3.CollectionUtilities.*;
import static chapter4.TailCall.ret;
import static chapter4.TailCall.sus;
import static chapter4.StackSafeCollectionUtils.foldLeft;

public class Memoization {
    public static String fibo(int number) {
        List<BigInteger> list = fibo_(list(BigInteger.ZERO),BigInteger.ONE, BigInteger.ZERO, BigInteger.valueOf(number)).eval();
        return makeString(list, ", ");
    }

    private static TailCall<List<BigInteger>> fibo_(List<BigInteger> acc, BigInteger acc1, BigInteger acc2, BigInteger x) {
        return x.equals(BigInteger.ZERO)
                ? ret(append(acc, acc1.add(acc2)))
                : sus(()-> fibo_(append(acc, acc1.add(acc2)),
                    acc2, acc1.add(acc2), x.subtract(BigInteger.ONE)));
    }

    private static String makeString(List<BigInteger> list, String separator) {

        return list.isEmpty()
                ? ""
                : tail(list).isEmpty()
                    ? head(list).toString()
                    : head(list) + foldLeft(tail(list),"", x -> y -> x + separator + y);
    }

    public static String fiboCorecursive(int number) {
        Tuple<BigInteger,BigInteger> seed = new Tuple<>(BigInteger.ZERO, BigInteger.ONE);
        Function<Tuple<BigInteger,BigInteger>,Tuple<BigInteger,BigInteger>> f =
                x -> new Tuple<>(x._2,x._1.add(x._2));
        List<BigInteger> list = map(iterate(seed,f, number+2), x -> x._1);
        return makeString(list, ", ");
    }

    public static void main(String... args) {
        System.out.println(fibo(10));
        System.out.println(fiboCorecursive(10));
    }

}
