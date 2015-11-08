package chapter3;


import com.fpinjava.common.Function;

import java.util.List;

import static chapter3.CollectionUtilities.*;

public class FuncOutput {
    public static void main(String... args) {
        Function<Double,Double> addTax = x -> x * 1.09;
        Function<Double, Double> addShipping = x -> x + 3.50;

        List<Double> prices = list(10.0, 23.45, 32.07, 9.23);
        List<Double>modifiedPrices = map(prices, addTax.andThen(addShipping));

        Effect<Double> printWith2Decimals = x -> {
            System.out.printf("%.2f",x);
            System.out.println();
        };

        forEach(modifiedPrices, printWith2Decimals);

        Function<Executable,Function<Executable,Executable>> compose = x -> y -> () -> {
            x.exec();
            y.exec();
        };

        Executable ez = () -> {};

        Executable program = foldLeft(modifiedPrices,ez, e -> d -> compose.apply(ez).apply(()->printWith2Decimals.apply(d)));

        program.exec();

    }
}
