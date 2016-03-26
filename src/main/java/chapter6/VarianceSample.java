package chapter6;


import chapter5.List;
import com.fpinjava.common.Function;

public class VarianceSample {
    static Function<List<Double>,Double> sum = l -> l.foldLeft(0.0, x -> y -> x + y);

    static Function<List<Double>,Option<Double>> mean =
            l -> l.isEmpty()
                ? Option.none()
                    : Option.some(sum.apply(l)/l.length());

    static Function<List<Double>, Option<Double>> variance =
            l -> mean.apply(l).flatMap(m -> mean.apply(l.map(x -> Math.pow(x - m, 2))));

    public static void main(String[] args) {
        List<Double> l = List.list(1.5,3.5,1.5,3.5,1.5,3.8);
        System.out.println(variance.apply(l));
    }
}
