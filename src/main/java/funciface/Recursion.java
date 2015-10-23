package funciface;


public class Recursion {
    public static Function<Integer,Integer> factorial;
    static {
        factorial = n -> n <= 1 ? n : n * factorial.apply(n - 1);
    }

    public final Function<Integer,Integer> factorialNonStatic = n -> n <= 1 ? n : n * this.factorialNonStatic.apply(n - 1);

    public static void main(String[] args) {
        System.out.println("5! is " + factorial.apply(5));

        Recursion r = new Recursion();
        System.out.println("4! is " + r.factorialNonStatic.apply(4));
    }
}
