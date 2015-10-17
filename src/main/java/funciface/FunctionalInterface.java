package funciface;


public class FunctionalInterface {
    
    public static Function<Integer,Integer> compose(final Function<Integer,Integer> f1, final Function<Integer,Integer> f2) {
        return arg -> f1.apply(f2.apply(arg));
    }

    public static void main(String[] args) {
        Function<Integer, Integer> triple = x -> x * 3;

        Function<Integer,Integer> square = x -> x * x;

        System.out.println("Triple 3 is " + triple.apply(3));
        System.out.println("Square 3 is " + square.apply(3));

        Function tripleThenSquare = compose(square, triple);

        System.out.println("triple then square 2 is " + tripleThenSquare.apply(2));
    }
}
