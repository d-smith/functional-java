package funciface;


public class ClosureSample {
    public static void main(String[] args) {
        double taxRate = 0.09;

        Function<Double,Double> computeTax = x -> x * taxRate;
        Function<Double,Double> addTax = x -> x + computeTax.apply(x);

        System.out.println(addTax.apply(10.0));

        Function<Double, Function<Double,Double>> curriedAddTax = x -> y -> y + y * x;

        System.out.println(curriedAddTax.apply(taxRate).apply(10.0));
    }
}
