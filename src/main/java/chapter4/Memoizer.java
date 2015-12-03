package chapter4;


import com.fpinjava.common.Function;
import com.sun.org.apache.xpath.internal.compiler.FunctionTable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Memoizer<T,U> {
    private final Map<T,U> cache = new ConcurrentHashMap<>();

    private Memoizer() {}

    public static <T,U>Function<T,U> memoize(Function<T,U> function) {
        return new Memoizer<T,U>().doMemoize(function);
    }

    private Function<T, U> doMemoize(Function<T, U> function) {
        return input -> cache.computeIfAbsent(input, function::apply);
    }

    private static Integer longCalculation(Integer x) {
        try {
            Thread.sleep(1_000);
        } catch(InterruptedException ignored) {

        }

        return x * 2;
    }

    private static Function<Integer, Integer> f = Memoizer::longCalculation;

    private static Function<Integer,Integer> g = Memoizer.memoize(f);

    public static void main(String... args) {
        long startTime = System.currentTimeMillis();
        Integer result1 = g.apply(1);
        long time1 = System.currentTimeMillis() - startTime;
        startTime = System.currentTimeMillis();
        Integer result2 = g.apply(1);
        long time2 = System.currentTimeMillis() - startTime;
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(time1);
        System.out.println(time2);
    }
}
