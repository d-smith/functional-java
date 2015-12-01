package chapter4;


import com.fpinjava.common.Function;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AutoMemoization {
    private static Map<Integer,Integer> cache = new ConcurrentHashMap<>();

    public static Function<Integer, Integer> doubleValue =
            x -> cache.computeIfAbsent(x, y -> y * 2);

    public static void main(String... args) {
        System.out.println(doubleValue.apply(2));
        System.out.println(doubleValue.apply(2));
    }
}
