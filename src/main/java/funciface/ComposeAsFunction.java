package funciface;


public class ComposeAsFunction {
    public static void main(String[] args) {
        Function<Function<Integer,Integer>,
                Function<Function<Integer,Integer>,
                        Function<Integer,Integer>>> compose = x -> y -> z -> x.apply(y.apply(z));

        Function<Integer,Integer> triple = x -> 3 * x;
        Function<Integer,Integer> square = x -> x * x;

        Function<Integer, Integer> tripleThenSquare = compose.apply(square).apply(triple);
        System.out.println(tripleThenSquare.apply(2));
    }
}
