package funciface;


public class FunctionalInterface {

    public static Function compose(final Function f1, final Function f2) {
        return new Function() {
            @Override
            public int apply(int arg) {
                return f1.apply(f2.apply(arg));
            }
        };
    }

    public static void main(String[] args) {
        Function triple = new Function() {
            @Override
            public int apply(int arg) {
                return arg * 3;
            }
        };

        Function square = new Function() {
            @Override
            public int apply(int arg) { return arg * arg;}
        };

        System.out.println("Triple 3 is " + triple.apply(3));
        System.out.println("Square 3 is " + square.apply(3));

        Function tripleThenSquare = compose(square, triple);

        System.out.println("triple then square 2 is " + tripleThenSquare.apply(2));
    }
}
