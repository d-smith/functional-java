package chapter6;


import com.fpinjava.common.Function;

public class LiftSample {
    public static void main(String[] args) {
        Function<Option<Double>, Option<Double>> abs0 = Option.lift(Math::abs);

        Option<Double> absNone = abs0.apply(Option.none());
        Option<Double> absMinusSeven = abs0.apply(Option.some(-7.0));
        System.out.println(absNone);
        System.out.println(absMinusSeven);
    }
 }

